package Ventanas;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import BaseDeDatos.DataBase;
import util.UtilidadesGUI;

public class Sensores extends javax.swing.JPanel implements ActionListener {

    public static Vector <String> vSensores = new Vector<String>();
	public static String [] aS;
    public static String [] idSensores;
    public static String [] descSensores;
    public static String [] estSensores;
    public static String nomPaciente;
    public static String sensorElegido;
	
    public Sensores(String nombrePaciente) {
        nomPaciente = nombrePaciente;
    	initComponents();
    }
                          
    private void initComponents() {
    	aS = null;
    	idSensores = null;
    	descSensores = null;
    	estSensores = null;
    	try {
			PantallaInicio.sm.Escribir("LISTSENSOR\n");
			System.out.println("Desde el cliente: LISTSENSOR");
			String respuesta = PantallaInicio.sm.Leer("322 OK Lista finalizada.");
			System.out.println("222 OK Lista de sensores.");
			aS = respuesta.split("&");
			for(int i = 1; i < aS.length; i++)
			{
				System.out.println(aS[i]);
				vSensores.add(i-1, aS[i]);
			}
			idSensores = new String [vSensores.size()];
			descSensores = new String [vSensores.size()];
			estSensores = new String [vSensores.size()];
			for(int i = 0; i < vSensores.size(); i++)
			{
				idSensores[i] = vSensores.get(i).split(";")[0];
				descSensores[i] = vSensores.get(i).split(";")[1];
				estSensores[i] = vSensores.get(i).split(";")[2];
			}
			System.out.println("322 OK Lista finalizada.");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    	
    	jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        fondo = new javax.swing.JLabel();
        t_idSensor = new javax.swing.JTextField();
        descripcion = new javax.swing.JComboBox();
        b_activar = new javax.swing.JButton();
        b_desactivar = new javax.swing.JButton();
        b_historicoM = new javax.swing.JButton();
        b_retroceso = new javax.swing.JButton();
        b_obtenerEstSensor = new javax.swing.JButton(); 

        setLayout(null);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 24));
        jLabel5.setForeground(new java.awt.Color(255, 0, 0));
        jLabel5.setText("SENSORES");
        add(jLabel5);
        jLabel5.setBounds(150, 10, 130, 30);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel4.setForeground(new java.awt.Color(0,250,154));
        jLabel4.setText("Activar/Desactivar sensor:");
        add(jLabel4);
        jLabel4.setBounds(20, 260, 182, 30);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel6.setForeground(new java.awt.Color(0,250,154));
        jLabel6.setText(" ID Sensor:");
        add(jLabel6);
        jLabel6.setBounds(20, 100, 110, 30);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel7.setForeground(new java.awt.Color(0,250,154));
        jLabel7.setText("Descripción:");
        add(jLabel7);
        jLabel7.setBounds(20, 180, 120, 30);

        t_idSensor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_idSensorActionPerformed(evt);
            }
        });
        add(t_idSensor);
        t_idSensor.setFont(new java.awt.Font("Tahoma", 1, 12));
        t_idSensor.setBackground(new java.awt.Color(238,44,44));
        t_idSensor.setText(idSensores[0]);
        t_idSensor.setBounds(130, 103, 230, 27);
        
        descripcion.setBackground(new java.awt.Color(255, 0, 0));
        descripcion.setModel(new javax.swing.DefaultComboBoxModel(descSensores));
        descripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descripcionActionPerformed(evt);
            }
        });
        
        add(descripcion);
        descripcion.setBounds(145, 187, 210, 20);
        
        add(b_activar);
        b_activar.setBounds(220, 265, 87, 24);
        b_activar.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Imagenes//boton_Act_GPS.jpg")));
        b_activar.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent arg0) {
				
			}
			
			public void mousePressed(MouseEvent arg0) {
				
			}
			
			public void mouseExited(MouseEvent arg0) {
				b_activar.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Imagenes//boton_Act_GPS.jpg")));
			}
			
			public void mouseEntered(MouseEvent arg0) {
				b_activar.setRolloverIcon(new ImageIcon(getClass().getClassLoader().getResource("Imagenes//b_Act_Gps.gif")));
			}
			
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});
        
        add(b_desactivar);
        b_desactivar.setBounds(310, 265, 87, 24);
        b_desactivar.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Imagenes//boton_Des_GPS.jpg")));
        b_desactivar.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent arg0) {
				
			}
			
			public void mousePressed(MouseEvent arg0) {
				
			}
			
			public void mouseExited(MouseEvent arg0) {
				b_desactivar.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Imagenes//boton_Des_GPS.jpg")));
			}
			
			public void mouseEntered(MouseEvent arg0) {
				b_desactivar.setRolloverIcon(new ImageIcon(getClass().getClassLoader().getResource("Imagenes//b_Des_Gps.gif")));
			}
			
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});

        add(b_historicoM);
        b_historicoM.setBounds(35, 340, 152, 30);
        b_historicoM.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Imagenes//b_historicoMedidas.jpg")));
        b_historicoM.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent arg0) {
				
			}
			
			public void mousePressed(MouseEvent arg0) {
				
			}
			
			public void mouseExited(MouseEvent arg0) {
				b_historicoM.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Imagenes//b_historicoMedidas.jpg")));
			}
			
			public void mouseEntered(MouseEvent arg0) {
				b_historicoM.setRolloverIcon(new ImageIcon(getClass().getClassLoader().getResource("Imagenes//b_historicoMedidas.gif")));
			}
			
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});
        
        add(b_retroceso);
        b_retroceso.setIcon(new javax.swing.ImageIcon("src//Imagenes//retroceso.png"));
        b_retroceso.setBounds(10, 10, 40, 40);
        
        add(b_obtenerEstSensor);
        b_obtenerEstSensor.setBounds(220, 340, 185, 30);
        b_obtenerEstSensor.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Imagenes//b_obtenerEstadoDelSensor.jpg")));
        b_obtenerEstSensor.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent arg0) {
				
			}
			
			public void mousePressed(MouseEvent arg0) {
				
			}
			
			public void mouseExited(MouseEvent arg0) {
				b_obtenerEstSensor.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Imagenes//b_obtenerEstadoDelSensor.jpg")));
			}
			
			public void mouseEntered(MouseEvent arg0) {
				b_obtenerEstSensor.setRolloverIcon(new ImageIcon(getClass().getClassLoader().getResource("Imagenes//b_est_sensor.gif")));
			}
			
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});
        
        fondo.setIcon(new javax.swing.ImageIcon("src\\Imagenes\\fondo.jpg"));
        add(fondo);
        fondo.setBounds(0, 0, 445, 472);
        
        //Añadir funcionalidad a los botones.
        b_activar.addActionListener(this);
        b_desactivar.addActionListener(this);
        b_historicoM.addActionListener(this);
        b_retroceso.addActionListener(this);
        descripcion.addActionListener(this);
        b_obtenerEstSensor.addActionListener(this);
        
        }
    
    private void t_idSensorActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void descripcionActionPerformed(java.awt.event.ActionEvent evt) {

    }

    public static javax.swing.JButton b_activar;
    public static javax.swing.JButton b_desactivar;
    private javax.swing.JButton b_historicoM;
    private javax.swing.JButton b_retroceso;
    private javax.swing.JButton b_obtenerEstSensor;
    public static javax.swing.JComboBox descripcion;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel fondo;
    public static javax.swing.JTextField t_idSensor;
    private javax.swing.JFrame actual;
    private JPanel estePanel = this;
    public static boolean activado;
    public static boolean imagenOnActivada;

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == b_activar)
		{			
			try {
				PantallaInicio.sm.Escribir("ON "+t_idSensor.getText()+"\n");
				System.out.println("Desde el cliente: ON "+t_idSensor.getText());
				String respuesta = PantallaInicio.sm.Leer();
				System.out.println("Desde el servidor: "+respuesta);
				if(respuesta.equals("528 ERR Sensor en estado ON."))
				{
					JOptionPane.showMessageDialog(this, "Este sensor ya esta activado", "Sensor activo", JOptionPane.ERROR_MESSAGE);
				}
				else if(respuesta.equals("527 ERR Sensor no existe."))
				{
					JOptionPane.showMessageDialog(this, "No existe un sensor con ese identificativo", "Sensor inexistente", JOptionPane.ERROR_MESSAGE);
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		else if(e.getSource() == b_desactivar)
		{
			try {
				PantallaInicio.sm.Escribir("OFF "+t_idSensor.getText()+"\n");
				System.out.println("Desde el cliente: OFF "+t_idSensor.getText());
				String respuesta = PantallaInicio.sm.Leer();
				System.out.println("Desde el servidor: "+respuesta);
				if(respuesta.equals("529 ERR Sensor en estado OFF."))
				{
					JOptionPane.showMessageDialog(this, "Este sensor ya esta desactivado", "Sensor desactivado", JOptionPane.ERROR_MESSAGE);
				}
				else if(respuesta.equals("527 ERR Sensor no existe."))
				{
					JOptionPane.showMessageDialog(this, "No existe un sensor con ese identificativo", "Sensor inexistente", JOptionPane.ERROR_MESSAGE);
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		else if(e.getSource() == b_obtenerEstSensor)
		{
			try {
				PantallaInicio.sm.Escribir("GET_VALACT "+t_idSensor.getText()+'\n');
				System.out.println("Desde el cliente: GET_VALACT "+t_idSensor.getText());
				String respuesta = PantallaInicio.sm.Leer();
				System.out.println("Desde el servidor: 224 OK "+respuesta);
				if(respuesta.equals("524 ERR Sensor desconocido."))
				{
					JOptionPane.showMessageDialog(this, "No existe un sensor con ese identificativo", "Sensor inexistente", JOptionPane.ERROR_MESSAGE);
				}
				else if(respuesta.equals("525 ERR Falta parámetro id_sensor."))
				{
					JOptionPane.showMessageDialog(this, "El campo id sensor esta vacio", "Identificativo vacio", JOptionPane.ERROR_MESSAGE);
				}
				else if(respuesta.equals("526 ERR Sensor en OFF."))
				{
					JOptionPane.showMessageDialog(this, "El sensor esta desactivado (activalo para poder consultar sus valores)", "Sensor desactivado", JOptionPane.ERROR_MESSAGE);
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}			
		}
		else if(e.getSource() == b_retroceso)
		{
			actual = (JFrame) UtilidadesGUI.getContenedorPrincipal(estePanel);
			actual.getContentPane().remove(0);
			actual.getContentPane().add(new PantallaMenu(nomPaciente));
			actual.setPreferredSize(new Dimension(445, 472));
			actual.pack();
			actual.repaint();
			actual.setLocationRelativeTo(null);
		}
		else if(e.getSource() == b_historicoM)
		{
			actual = (JFrame) UtilidadesGUI.getContenedorPrincipal(estePanel);
			actual.getContentPane().remove(0);
			actual.getContentPane().add(new HistoricoMedidas(t_idSensor.getText()));
			actual.setPreferredSize(new Dimension(445, 472));
			actual.pack();
			actual.repaint();
			actual.setLocationRelativeTo(null);
		}
		else if(e.getSource() == descripcion)
		{
			sensorElegido = (String) descripcion.getSelectedItem();
			switch(sensorElegido)
			{
				case "Ritmo cardiaco": 
					t_idSensor.setText(idSensores[0]);
				break;
				
				case "Ritmo respiratorio": 
					t_idSensor.setText(idSensores[1]);
				break;
				
				case "Temperatura": 
					t_idSensor.setText(idSensores[2]);
				break;
				
				case "Nivel de glucosa": 
					t_idSensor.setText(idSensores[3]);
				break;
			}
		}
	}
}