package Ventanas;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import util.UtilidadesGUI;

public class HistoricoMedidas extends javax.swing.JPanel implements ActionListener {
	public static String idSensor;
	public static Vector <String> vMedidas = new Vector<String>();
	public static String [] aM;
    public static String [] fechaMedidas;
    public static String [] horaMedidas;
    public static String [] coordenadasMedidas;
    public static String [] valorMedidas;

    public HistoricoMedidas(String id) {
        idSensor = id;
    	initComponents();
    }
                          
    private void initComponents() {
    	
    	fechaMedidas = null;
    	horaMedidas = null;
    	coordenadasMedidas = null;
    	valorMedidas = null;
    	
    	try {
    		PantallaInicio.sm.Escribir("HISTORICO "+idSensor+'\n');
    		if((idSensor.equals("1"))||(idSensor.equals("2"))||
    		(idSensor.equals("3"))||(idSensor.equals("4")))
    		{
    			System.out.println("Desde el cliente: HISTORICO "+idSensor);
    			String respuesta = PantallaInicio.sm.Leer("322 OK Lista finalizada.");
    			System.out.println("223 OK Lista de medidas.");
    			aM = respuesta.split("&");
    			for(int i = 1; i < aM.length; i++)
    			{
    				System.out.println(aM[i]);
    				vMedidas.add(i-1, aM[i]);
    			}
    			fechaMedidas = new String [vMedidas.size()];
    			horaMedidas = new String [vMedidas.size()];
    			coordenadasMedidas= new String [vMedidas.size()];
    			valorMedidas= new String [vMedidas.size()];
    			for(int i = 0; i < vMedidas.size(); i++)
    			{
    				fechaMedidas[i] = vMedidas.get(i).split(";")[0];
    				horaMedidas[i] = vMedidas.get(i).split(";")[1];
    				coordenadasMedidas[i] = vMedidas.get(i).split(";")[2];
    				valorMedidas[i] = vMedidas.get(i).split(";")[3];
    			}
    			System.out.println("322 OK Lista finalizada.");
    		}
    		else if(((!idSensor.equals("1"))&&(!idSensor.equals("2"))&&(!idSensor.equals("3"))
    		&&(!idSensor.equals("4")))||(idSensor.equals("")))
    		{
    			System.out.println("Desde el cliente: HISTORICO "+idSensor);
    			String respuesta = PantallaInicio.sm.Leer();
    			while(respuesta.equals("223 OK Lista de medidas."))
    			{
    				respuesta = PantallaInicio.sm.Leer();
    			}
    			System.out.println("Desde el servidor: "+respuesta);
    			if(respuesta.equals("524 ERR Sensor desconocido."))
    			{
    				JOptionPane.showMessageDialog(this, "No existe un sensor con ese identificativo", "Sensor inexistente", JOptionPane.ERROR_MESSAGE);
    			}
    			else
    			{
    				JOptionPane.showMessageDialog(this, "El campo de id del sensor esta vacio", "Campo id sensor vacio", JOptionPane.ERROR_MESSAGE);
    			}
    		}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    	
    	jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        fondo = new javax.swing.JLabel();
        t_valor = new javax.swing.JTextField();
        t_fecha = new javax.swing.JTextField();
        t_hora = new javax.swing.JTextField();
        t_coordenadas = new javax.swing.JTextField();
        b_salir = new javax.swing.JButton();

        setLayout(null);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 24));
        jLabel5.setForeground(new java.awt.Color(255, 0, 0));
        jLabel5.setText("HISTORICO DE MEDIDAS");
        add(jLabel5);
        jLabel5.setBounds(80, 30, 310, 30);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel6.setForeground(new java.awt.Color(0,250,154));
        jLabel6.setText("Valor:");
        add(jLabel6);
        jLabel6.setBounds(40, 300, 60, 30);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel7.setForeground(new java.awt.Color(0,250,154));
        jLabel7.setText("Fecha:");
        add(jLabel7);
        jLabel7.setBounds(40, 150, 70, 30);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel8.setForeground(new java.awt.Color(0,250,154));
        jLabel8.setText("Hora:");
        add(jLabel8);
        jLabel8.setBounds(40, 200, 60, 30);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel9.setForeground(new java.awt.Color(0,250,154));
        jLabel9.setText("Coordenadas:");
        add(jLabel9);
        jLabel9.setBounds(40, 250, 130, 30);

        t_valor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_valorActionPerformed(evt);
            }
        });
        add(t_valor);
        t_valor.setFont(new java.awt.Font("Tahoma", 1, 12));
        t_valor.setBackground(new java.awt.Color(238,44,44));
        t_valor.setBounds(100, 303, 270, 27);

        t_fecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_fechaActionPerformed(evt);
            }
        });
        add(t_fecha);
        t_fecha.setFont(new java.awt.Font("Tahoma", 1, 12));
        t_fecha.setBackground(new java.awt.Color(238,44,44));
        t_fecha.setBounds(110, 153, 260, 27);

        t_hora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_horaActionPerformed(evt);
            }
        });
        add(t_hora);
        t_hora.setFont(new java.awt.Font("Tahoma", 1, 12));
        t_hora.setBackground(new java.awt.Color(238,44,44));
        t_hora.setBounds(100, 203, 270, 27);

        t_coordenadas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_coordenadasActionPerformed(evt);
            }
        });
        add(t_coordenadas);
        t_coordenadas.setFont(new java.awt.Font("Tahoma", 1, 12));
        t_coordenadas.setBackground(new java.awt.Color(238,44,44));
        t_coordenadas.setBounds(170, 253, 200, 27);
        
        add(b_salir);
        b_salir.setIcon(new javax.swing.ImageIcon("src//Imagenes//cerrar.jpg"));
        b_salir.setBounds(10, 10, 40, 40);
        
        inicializarCampos();
        
        fondo.setIcon(new javax.swing.ImageIcon("src\\Imagenes\\fondo.jpg"));
        add(fondo);
        fondo.setBounds(0, 0, 445, 472);
        
        //Añadir funcionalidad a los botones.
        b_salir.addActionListener(this);
        
    }

    public static void inicializarCampos()
    {
    	switch(idSensor)
    	{
    	case "1":
    		t_fecha.setText(fechaMedidas[0]);
    		t_hora.setText(horaMedidas[0]);
    		t_coordenadas.setText(coordenadasMedidas[0]);
    		t_valor.setText(valorMedidas[0]);
    		break;
    		
    	case "2":
    		t_fecha.setText(fechaMedidas[1]);
    		t_hora.setText(horaMedidas[1]);
    		t_coordenadas.setText(coordenadasMedidas[1]);
    		t_valor.setText(valorMedidas[1]);
    		break;
    		
    	case "3":
    		t_fecha.setText(fechaMedidas[2]);
    		t_hora.setText(horaMedidas[2]);
    		t_coordenadas.setText(coordenadasMedidas[2]);
    		t_valor.setText(valorMedidas[2]);
    		break;
    		
    	case "4":
    		t_fecha.setText(fechaMedidas[3]);
    		t_hora.setText(horaMedidas[3]);
    		t_coordenadas.setText(coordenadasMedidas[3]);
    		t_valor.setText(valorMedidas[3]);
    		break;
    	}
    }
    
    private void t_valorActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void t_fechaActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void t_horaActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void t_coordenadasActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private javax.swing.JButton b_salir;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel fondo;
    public static javax.swing.JTextField t_coordenadas;
    public static javax.swing.JTextField t_fecha;
    public static javax.swing.JTextField t_hora;
    public static javax.swing.JTextField t_valor;
    private javax.swing.JFrame actual;
    private JPanel estePanel = this;
                   
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == b_salir)
		{
			try {
				PantallaInicio.sm.Escribir("fin");
				PantallaInicio.cerrarSocket();
				Pantalla.cerrar();
			} catch (IOException e1) {
				System.out.println("Excepcion al salir");
				e1.printStackTrace();
			}
		}
	}
}