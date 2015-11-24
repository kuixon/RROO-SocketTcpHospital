package tcpServer;

import java.io.*;
import java.net.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import BaseDeDatos.DataBase;
import Ventanas.Pantalla;
import Ventanas.Pantalla2;
import Ventanas.PantallaInicio;
import Ventanas.PantallaMenu;
import util.*;

final class HttpRequest implements Runnable {

  final static String CRLF = "\r\n";
  SocketManager sockManager;
	public static boolean [] permisos = new boolean[12];
	public static String nombreUs = null;
	public static String idenPaciente = null;
	public static String contraseñaUs = null;
	public static String [] descS = null;
	public static String [] idS = null;
	public static String [] estadoS = null;

  
	public static void inicializarPermisos(String frase)
	{
		String [] s = {"USUARIO", "CLAVE", "LISTSENSOR","HISTORICO","ON","OFF",
				"ONGPS","OFFGPS","GET_VALACT","GET_FOTO","GET_LOC","SALIR"};
		for(int i = 0; i < permisos.length; i++)
		{
			permisos [i] = frase.contains(s[i]);
		}
	}
	
  public HttpRequest(SocketManager sockMan) throws Exception {
    sockManager = sockMan;
  }

  public void run() {
    try {
      processRequest();
    }
    catch (Exception e) {
      System.out.println(e);
    }
  }

  private void processRequest() throws Exception {
      String clientSentence="";
      String serverSentence="";
      try{
          System.out.println("Esto funciona...");
          while((!clientSentence.equals("fin")))
          {
	            System.out.println("Conexion establecida");
	            clientSentence = sockManager.Leer();
	            inicializarPermisos(clientSentence);
	            if(permisos[0])
	            {
	            	//Si la frase del cliente contiene la palabra USUARIO.
	            	if(clientSentence.length() > 8)
	            	{
	            		boolean enc = false;
	            		nombreUs = clientSentence.substring(8);
	            		ResultSet rs = DataBase.getInstance().consult("SELECT * FROM PACIENTE WHERE username = '"+nombreUs+"'");
	            		while((rs.next())&&(!enc))
	            		{
	            			if(rs.getString("username").equals(nombreUs))
	            			{
	            				enc = true;
	            			}	            		
	            		}
	            		if(enc)
	            		{
	            			serverSentence = "301 OK Bienvenido "+nombreUs+".";
		            		sockManager.Escribir(serverSentence + '\n');
	            		}
	            		else
	            		{
	            			serverSentence = "ERR El usuario no esta registrado en el sistema";
	            			sockManager.Escribir(serverSentence + '\n');
	            		}
	            	}
	            	else
	            	{
	            		serverSentence = "501 ERR Falta el nombre de usuario.";
	            		sockManager.Escribir(serverSentence + '\n');
	            	}
	            }
	            else if(permisos[1])
	            {
	            	//Si la frase del cliente contiene la palabra CLAVE.
	            	if(clientSentence.length() > 6)
	            	{
	            		contraseñaUs = clientSentence.substring(6);
	            		ResultSet rs = DataBase.getInstance().consult("SELECT username FROM PACIENTE WHERE username = '"+nombreUs+"'");
	            		if(rs.next())
	            		{
	            			/*Si el usuario ya esta en nuestra base de datos, tenemos
	            			 * que verificar que la contraseña introducida le corresponde
	            			 */
	            			rs = DataBase.getInstance().consult("SELECT password FROM PACIENTE WHERE username = '"+nombreUs+"'");
	            			if(!contraseñaUs.equals(rs.getString("password")))
	            			{
	            				//Si la contraseña NO coincide con la del usuario registrado
	            				serverSentence = "502 ERR La clave es incorrecta.";
	            				sockManager.Escribir(serverSentence + '\n');
	            			}
	            			else
	            			{
	            				//Si la contraseña SI coincide con la del usuario registrado
	            				serverSentence = "302 OK Bienvenido al sistema";
	            				sockManager.Escribir(serverSentence + '\n');
	            			}
	            		}	            		
	            	}
	            	else
          		{
	            		serverSentence = "503 ERR Falta la clave.";
	            		sockManager.Escribir(serverSentence + '\n');
          		}
	            }
	            else if(permisos[2])
	            {
	            	//Si la frase del cliente contiene la palabra LISTSENSOR.
	            	serverSentence = "222 OK Lista de sensores.";
	            	sockManager.Escribir(serverSentence + '\n');
	            	ResultSet rs = DataBase.getInstance().consult("SELECT * FROM SENSOR");
	            	try {
	        			while(rs.next())
	        			{
	        				serverSentence =  rs.getInt("id")+";"+rs.getString("desc")+";"+rs.getString("estado");
	        				sockManager.Escribir(serverSentence + '\n');
	        			}
	        		} catch (SQLException e) {
	        			e.printStackTrace();
	        		}
	            	serverSentence = "322 OK Lista finalizada.";
	            	sockManager.Escribir(serverSentence + '\n');
	            }
	            else if(permisos[3])
	            {
	            	//Si la frase del cliente contiene la palabra HISTORICO.
	            	if(clientSentence.length() > 10)
	            	{
	            		serverSentence = "223 OK Lista de medidas.";
	            		sockManager.Escribir(serverSentence + '\n');
	            		String cadena = clientSentence.substring(10);
	            		if((cadena.equals("1"))||(cadena.equals("2"))||(cadena.equals("3"))||cadena.equals("4"))
	            		{
	            			ResultSet rs = DataBase.getInstance().consult("SELECT * FROM MEDIDA");
			            	while(rs.next())
			            	{
			            		//Si EXISTE el sensor.
			            		serverSentence = rs.getString("fecha")+";"+rs.getString("hora")+";"+rs.getString("coordenadas")+";"+rs.getString("valor");
			            		sockManager.Escribir(serverSentence + '\n');
			            	}
			            	serverSentence = "322 OK Lista finalizada.";
			            	sockManager.Escribir(serverSentence + '\n');
	            		}
	            		else
	            		{
	            			//Si NO EXISTE el sensor.
		            		serverSentence = "524 ERR Sensor desconocido.";
		            		sockManager.Escribir(serverSentence + '\n');
	            		}
	            	}
	            	else
	            	{	
	            		serverSentence = "525 ERR Falta parámetro id_sensor.";
	            		sockManager.Escribir(serverSentence + '\n');
	            	}
	            	
	            }
	            else if((permisos[4])&&(!clientSentence.contains("GPS")))
	            {
	            	//Si la frase del cliente contiene la palabra ON.
	            	String cadena = clientSentence.substring(3);
	            	if(cadena.equals("1")||cadena.equals("2")||cadena.equals("3")||
	            			cadena.equals("4"))
	            	{
	            		ResultSet rs = DataBase.getInstance().consult("SELECT estado FROM SENSOR WHERE id='"+cadena+"'");
	            		if(rs.next())
	            		{
	            			if(rs.getString("estado").equals("ON"))
	            			{
	            				serverSentence = "528 ERR Sensor en estado ON.";
	            				sockManager.Escribir(serverSentence + '\n');
	            			}
	            			else
	            			{
	            				DataBase.getInstance().update("UPDATE SENSOR SET estado='ON' WHERE id='"+cadena+"'");
	            				serverSentence = "313 OK Sensor activo.";
	            				sockManager.Escribir(serverSentence + '\n');
	            			}
	            		}
	            	}
	            	else
	            	{
	            		serverSentence = "527 ERR Sensor no existe.";
	            		sockManager.Escribir(serverSentence + '\n');
	            	}
	            }
	            else if((permisos[5])&&(!clientSentence.contains("GPS")))
	            {
	            	//Si la frase del cliente contiene la palabra OFF.
	            	String cadena = clientSentence.substring(4);
	            	if(cadena.equals("1")||cadena.equals("2")||cadena.equals("3")||
	            			cadena.equals("4"))
	            	{
	            		ResultSet rs = DataBase.getInstance().consult("SELECT estado FROM SENSOR WHERE id='"+cadena+"'");
	            		if(rs.next())
	            		{
	            			if(rs.getString("estado").equals("OFF"))
	            			{
	            				serverSentence = "529 ERR Sensor en estado OFF.";
	            				sockManager.Escribir(serverSentence + '\n');
	            			}
	            			else
	            			{
	            				DataBase.getInstance().update("UPDATE SENSOR SET estado='OFF' WHERE id='"+cadena+"'");
	            				serverSentence = "314 OK Sensor desactivado.";
	            				sockManager.Escribir(serverSentence + '\n');
	            			}
	            		}
	            	}
	            	else
	            	{
	            		serverSentence = "527 ERR Sensor no existe.";
	            		sockManager.Escribir(serverSentence + '\n');
	            	}
	            }
	            else if(permisos[6])
	            {
	            	//Si la frase del cliente contiene la palabra ONGPS.
	            	if(PantallaMenu.gpsActivado)
	            	{
	            		serverSentence = "529 ERR GPS en estado ON.";
	            		sockManager.Escribir(serverSentence + '\n');
	            		PantallaMenu.gpsActivado = true;
	            	}
	            	else
	            	{ 
	            		serverSentence = "315 OK GPS activado.";
	            		sockManager.Escribir(serverSentence + '\n');
	            		PantallaMenu.gpsActivado = true;
	            	}
	            }
	            else if(permisos[7])
	            {
	            	//Si la frase del cliente contiene la palabra OFFGPS.
	            	if(PantallaMenu.gpsActivado)
	            	{
	            		serverSentence = "316 OK GPS desactivado.";
	            		sockManager.Escribir(serverSentence + '\n');
	            		PantallaMenu.gpsActivado = false;
	            	}
	            	else
	            	{ 
	            		serverSentence = "530 ERR GPS en estado OFF.";
	            		sockManager.Escribir(serverSentence + '\n');
	            		PantallaMenu.gpsActivado = false;
	            	}
	            }
	            else if(permisos[8])
	            {
	            	//Si la frase del cliente contiene la palabra GET_VALACT.
	            	String cadena = clientSentence.substring(11);
	            	if(clientSentence.length() > 11)
	            	{
	            		if((cadena.equals("1"))||(cadena.equals("2"))||(cadena.equals("3"))||cadena.equals("4"))
	            		{
	            			ResultSet rs = DataBase.getInstance().consult("SELECT estado FROM SENSOR WHERE id = '"+cadena+"'");
	            			if(rs.next())
	            			{
	            				if(rs.getString("estado").equals("OFF"))
	            				{
	            					//Error, sensor apagado.
	            					serverSentence = "526 ERR Sensor en OFF.";
	            					sockManager.Escribir(serverSentence + '\n');
	            				}
	            				else
	            				{
	            					rs = DataBase.getInstance().consult("SELECT * FROM MEDIDA WHERE id = '"+cadena+"'");
	            					if(rs.next())
	            					{
	            						serverSentence = rs.getString("fecha")+";"+rs.getString("hora")+";"+rs.getString("coordenadas")+";"+rs.getString("valor");
		            					sockManager.Escribir(serverSentence + '\n');
	            					}
	            				}
	            			}
	            		}
	            		else
	            		{
	            			//El id del sensor no existe
	            			serverSentence = "524 ERR Sensor desconocido.";
	            			sockManager.Escribir(serverSentence + '\n');
	            		}
	            	}
	            	else
	            	{
	            		//La cadena esta vacia
	            		serverSentence = "525 ERR Falta parámetro id_sensor.";
            			sockManager.Escribir(serverSentence + '\n');
	            	}
	            }
	            else if(permisos[9])
	            {
	            	//Si la frase del cliente contiene la palabra GET_FOTO.
	            	String cadena = clientSentence.substring(9);
	            }
	            else if(permisos[10])
	            {
	            	//Si la frase del cliente contiene la palabra GET_LOC.
	            	if(PantallaMenu.gpsActivado)
	            	{
	            		Random rand = new Random();
	            		int num = rand.nextInt(100);
	            		int num2 = rand.nextInt(100);
	            		int num3 = rand.nextInt(100);
	            		int num4 = rand.nextInt(100);
	            		int num5 = rand.nextInt(100);
	            		int num6 = rand.nextInt(100);
	            		int num7 = rand.nextInt(100);
	            		int num8 = rand.nextInt(100);
	            		
	            		serverSentence= "224 OK "+num+"°"+num2+"'"+num3+"."+num4+"-"+num5+"°"+num6+"'"+num7+"."+num8;
	            		sockManager.Escribir(serverSentence + '\n');
	            	}
	            	else
	            	{
	            		serverSentence = "Se ha cerrado la conexión con el socket y hemos creado otra nueva";
	            		sockManager.Escribir(serverSentence + '\n');
	            	}
	            }
	            else if(permisos[11])
	            {
	            	//Si la frase del cliente contiene la palabra SALIR.
	            	serverSentence = "318 OK Adiós.";
	            	sockManager.Escribir(serverSentence + '\n');
	            }
	            else if(!clientSentence.contains("USUARIO")&&!clientSentence.contains("CLAVE")&&
	            		!clientSentence.contains("LISTSENSOR")&&!clientSentence.contains("HISTORICO")&&
	            		!clientSentence.contains("ON")&&!clientSentence.contains("OFF")&&
	            		!clientSentence.contains("ONGPS")&&!clientSentence.contains("OFFGPS")&&
	            		!clientSentence.contains("GET_VALACT")&&!clientSentence.contains("GET_FOTO")&&
	            		!clientSentence.contains("GET_LOC")&&!clientSentence.contains("SALIR")&&
	            		!clientSentence.contains("fin")
	            		)
	            {
	            	serverSentence = "Comando no existente";
	            }
          }
      }catch(Exception e){
          System.err.println("main: " + e);
          e.printStackTrace();
      }
  }

  private void sendBytes(FileInputStream fis) throws Exception {
    byte[] buffer = new byte[1024];
    int bytes = 0;

    while ( (bytes = fis.read(buffer)) != -1) {
      sockManager.Escribir(buffer, bytes);
    }
  }

  private static String contentType(String fileName) {
    if (fileName.endsWith(".htm") || fileName.endsWith(".html")) {
      return "text/html";
    }
    if (fileName.endsWith(".ram") || fileName.endsWith(".ra")) {
      return "audio/x-pn-realaudio";
    }
    return "application/octet-stream";
  }
}