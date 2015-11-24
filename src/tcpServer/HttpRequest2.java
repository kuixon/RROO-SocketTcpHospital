package tcpServer;

import java.io.*;
import java.net.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import BaseDeDatos.DataBase2;
import BaseDeDatos.DataBase2;
import Ventanas.PantallaMenu;
import util.*;

final class HttpRequest2 implements Runnable {

  final static String CRLF = "\r\n";
  SocketManager sockManager;
	public static boolean [] permisos = new boolean[4];
	public static String nombreUs = null;
	public static String idenPaciente = null;
	public static String contraseñaUs = null;
	  
	public static void inicializarPermisos(String frase)
	{
		String [] s = {"USER", "PASS", "GETCOOR","SALIR"};
		for(int i = 0; i < permisos.length; i++)
		{
			permisos [i] = frase.contains(s[i]);
		}
	}
	
  public HttpRequest2(SocketManager sockMan) throws Exception {
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
	            	//Si la frase del cliente contiene la palabra USER.
	            	if(clientSentence.length() > 5)
	            	{
	            		boolean enc = false;
	            		nombreUs = clientSentence.substring(5);
	            		ResultSet rs = DataBase2.getInstance().consult("SELECT * FROM USUARIO WHERE username = '"+nombreUs+"'");
	            		while((rs.next())&&(!enc))
	            		{
	            			if(rs.getString("username").equals(nombreUs))
	            			{
	            				enc = true;
	            			}
	            		}
	            		if(enc)
	            		{
	            			serverSentence = "311 OK Bienvenido "+nombreUs+".";
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
	            		serverSentence = "511 ERR Falta el nombre de usuario.";
	            		sockManager.Escribir(serverSentence + '\n');
	            	}
	            }
	            else if(permisos[1])
	            {
	            	//Si la frase del cliente contiene la palabra PASS.
	            	if(clientSentence.length() > 5)
	            	{
	            		contraseñaUs = clientSentence.substring(5);
	            		ResultSet rs = DataBase2.getInstance().consult("SELECT username FROM USUARIO WHERE username = '"+nombreUs+"'");
	            		if(rs.next())
	            		{
	            			/*Si el usuario ya esta en nuestra base de datos, tenemos
	            			 * que verificar que la contraseña introducida le corresponde
	            			 */
	            			rs = DataBase2.getInstance().consult("SELECT password FROM USUARIO WHERE username = '"+nombreUs+"'");
	            			if(!contraseñaUs.equals(rs.getString("password")))
	            			{
	            				//Si la contraseña NO coincide con la del usuario registrado
	            				serverSentence = "512 ERR La clave es incorrecta.";
	            				sockManager.Escribir(serverSentence + '\n');
	            			}
	            			else
	            			{
	            				//Si la contraseña SI coincide con la del usuario registrado
	            				serverSentence = "312 OK Bienvenido al sistema";
	            				sockManager.Escribir(serverSentence + '\n');
	            			}
	            		}	            		
	            	}
	            	else
	            	{
	            		serverSentence = "513 ERR Falta la clave.";
	            		sockManager.Escribir(serverSentence + '\n');
	            	}
	            }
	            else if(permisos[2])
	            {
	            	//Si la frase del cliente contiene la palabra GETCOOR.
	            	if(clientSentence.length() > 8)
	            	{
	            		String id = clientSentence.substring(8);
	            		ResultSet rs = DataBase2.getInstance().consult("SELECT coordenada FROM COORDENADAS WHERE id = '"+id+"'");
	            		if(rs.next())
	            		{
	            			serverSentence = "224 OK "+rs.getString("coordenada");
	            			sockManager.Escribir(serverSentence + '\n');
	            		}
	            		else
	            		{
	            			serverSentence = "527 ERR Celda desconocida.";
	            			sockManager.Escribir(serverSentence + '\n');
	            		}
	            	}
	            	else
	            	{
	            		serverSentence = "528 ERR Falta parámetro cell_id.";
            			sockManager.Escribir(serverSentence + '\n');
	            	}
	            }
	            else if(permisos[3])
	            {
	            	//Si la frase del cliente contiene la palabra SALIR.
	            	serverSentence = "318 OK Adiós.";
	            	sockManager.Escribir(serverSentence + '\n');
	            }
	            else if(!clientSentence.contains("USUARIO")&&!clientSentence.contains("CLAVE")&&
	            		!clientSentence.contains("GETCOOR")&&!clientSentence.contains("SALIR")&&
	            		!clientSentence.contains("fin")
	            		)
	            {
	            	serverSentence = "Comando no existente";
	            	sockManager.Escribir(serverSentence + '\n');
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