package Ventanas;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import BaseDeDatos.DataBase;
import util.SocketManager;
import util.UtilidadesGUI;

public class PantallaMenu extends javax.swing.JPanel implements ActionListener {

	public static String nombrePaciente = null;
	public static boolean gpsActivado;
	
	public PantallaMenu(String nomPaciente) {
		nombrePaciente = nomPaciente;
		initComponents();
    }

    private void initComponents() {

    	gpsActivado = false;
    	
    	jLabel3 = new javax.swing.JLabel();
    	jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        fondo = new javax.swing.JLabel();
        t_nomPac = new javax.swing.JTextField();
        ruta_Foto = new javax.swing.JTextField();
        b_act_GPS = new javax.swing.JButton();
        b_des_GPS = new javax.swing.JButton();
        b_examinar = new javax.swing.JButton();
        b_enviar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        b_menu_sensores = new javax.swing.JButton();
        b_salir = new javax.swing.JButton();
        b_obt_loc = new javax.swing.JButton();

        setLayout(null);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 24));
        jLabel5.setForeground(new java.awt.Color(255, 0, 0));
        jLabel5.setText("MENU");
        add(jLabel5);
        jLabel5.setBounds(180, 10, 80, 30);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel4.setForeground(new java.awt.Color(0,250,154));
        jLabel4.setText("Nombre Paciente:");
        add(jLabel4);
        jLabel4.setBounds(10, 80, 170, 30);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel6.setForeground(new java.awt.Color(0,250,154));
        jLabel6.setText("Sensores:");
        add(jLabel6);
        jLabel6.setBounds(10, 250, 90, 20);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel7.setForeground(new java.awt.Color(0,250,154));
        jLabel7.setText("Foto:");
        add(jLabel7);
        jLabel7.setBounds(10, 140, 90, 20);
        add(t_nomPac);
        t_nomPac.setFont(new java.awt.Font("Tahoma", 1, 12));
        t_nomPac.setText(nombrePaciente);
        t_nomPac.setBackground(new java.awt.Color(238,44,44));
        t_nomPac.setBounds(180, 83, 230, 27);
        add(ruta_Foto);
        ruta_Foto.setFont(new java.awt.Font("Tahoma", 1, 12));
        ruta_Foto.setBackground(new java.awt.Color(238,44,44));
        ruta_Foto.setBounds(57, 138, 253, 27);
        
        add(b_obt_loc);
        b_obt_loc.setBounds(155, 287, 155, 24);
        b_obt_loc.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Imagenes//boton_Obt_Loc.jpg")));
        b_obt_loc.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent arg0) {
				
			}
			
			public void mousePressed(MouseEvent arg0) {
				
			}
			
			public void mouseExited(MouseEvent arg0) {
				b_obt_loc.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Imagenes//boton_Obt_Loc.jpg")));
			}
			
			public void mouseEntered(MouseEvent arg0) {
				b_obt_loc.setRolloverIcon(new ImageIcon(getClass().getClassLoader().getResource("Imagenes//b_obtenerLocalizacion.gif")));
			}
			
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});
        
        add(b_act_GPS);
        b_act_GPS.setBounds(185, 207, 87, 24);
        b_act_GPS.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Imagenes//boton_Act_GPS.jpg")));
        b_act_GPS.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent arg0) {
				
			}
			
			public void mousePressed(MouseEvent arg0) {
				
			}
			
			public void mouseExited(MouseEvent arg0) {
				b_act_GPS.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Imagenes//boton_Act_GPS.jpg")));
			}
			
			public void mouseEntered(MouseEvent arg0) {
				b_act_GPS.setRolloverIcon(new ImageIcon(getClass().getClassLoader().getResource("Imagenes//b_Act_Gps.gif")));
			}
			
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});
        
        add(b_des_GPS);
        b_des_GPS.setBounds(280, 207, 87, 24);
        b_des_GPS.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Imagenes//boton_Des_GPS.jpg")));
        b_des_GPS.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent arg0) {
				
			}
			
			public void mousePressed(MouseEvent arg0) {
				
			}
			
			public void mouseExited(MouseEvent arg0) {
				b_des_GPS.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Imagenes//boton_Des_GPS.jpg")));
			}
			
			public void mouseEntered(MouseEvent arg0) {
				b_des_GPS.setRolloverIcon(new ImageIcon(getClass().getClassLoader().getResource("Imagenes//b_Des_Gps.gif")));
			}
			
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});

        add(b_examinar);
        b_examinar.setBounds(318, 140, 85, 23);
        b_examinar.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Imagenes//b_examinar.jpg")));
        b_examinar.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent arg0) {
				
			}
			
			public void mousePressed(MouseEvent arg0) {
				
			}
			
			public void mouseExited(MouseEvent arg0) {
				b_examinar.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Imagenes//b_examinar.jpg")));
			}
			
			public void mouseEntered(MouseEvent arg0) {
				b_examinar.setRolloverIcon(new ImageIcon(getClass().getClassLoader().getResource("Imagenes//b_examinar.gif")));
			}
			
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});

        add(b_enviar);
        b_enviar.setBounds(318, 170, 63, 23);
        b_enviar.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Imagenes//b_enviar.jpg")));
        b_enviar.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent arg0) {
				
			}
			
			public void mousePressed(MouseEvent arg0) {
				
			}
			
			public void mouseExited(MouseEvent arg0) {
				b_enviar.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Imagenes//b_enviar.jpg")));
			}
			
			public void mouseEntered(MouseEvent arg0) {
				b_enviar.setRolloverIcon(new ImageIcon(getClass().getClassLoader().getResource("Imagenes//b_enviar.gif")));
			}
			
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel8.setForeground(new java.awt.Color(0,250,154));
        jLabel8.setText("Activar/Desactivar GPS:");
        add(jLabel8);
        jLabel8.setBounds(10, 210, 165, 20);
        
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel3.setForeground(new java.awt.Color(0,250,154));
        jLabel3.setText("Obtener localizacion:");
        add(jLabel3);
        jLabel3.setBounds(10, 287, 165, 20);
        

        add(b_menu_sensores);
        b_menu_sensores.setBounds(115, 250, 150, 23);
        b_menu_sensores.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Imagenes//b_IrAMenuSensores.jpg")));
        b_menu_sensores.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent arg0) {
				
			}
			
			public void mousePressed(MouseEvent arg0) {
				
			}
			
			public void mouseExited(MouseEvent arg0) {
				b_menu_sensores.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Imagenes//b_IrAMenuSensores.jpg")));
			}
			
			public void mouseEntered(MouseEvent arg0) {
				b_menu_sensores.setRolloverIcon(new ImageIcon(getClass().getClassLoader().getResource("Imagenes//b_menuSensores.gif")));
			}
			
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});

        add(b_salir);
        b_salir.setBounds(170, 340, 110, 40);
        b_salir.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Imagenes//b_salir.jpg")));
        b_salir.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent arg0) {
				
			}
			
			public void mousePressed(MouseEvent arg0) {
				
			}
			
			public void mouseExited(MouseEvent arg0) {
				b_salir.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Imagenes//b_salir.jpg")));
			}
			
			public void mouseEntered(MouseEvent arg0) {
				b_salir.setRolloverIcon(new ImageIcon(getClass().getClassLoader().getResource("Imagenes//b_salir.gif")));
			}
			
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});
        
        fondo.setIcon(new javax.swing.ImageIcon("src\\Imagenes\\fondo.jpg"));
        add(fondo);
        fondo.setBounds(0, 0, 445, 472);
        
        //Añadir funcionalidad a los botones.
        b_act_GPS.addActionListener(this);
        b_des_GPS.addActionListener(this);
        b_examinar.addActionListener(this);
        b_menu_sensores.addActionListener(this);
        b_salir.addActionListener(this);
        b_obt_loc.addActionListener(this);
        
        //Para que se habra con este tamaño como mínimo.
        this.setMinimumSize(new Dimension(445, 472));
        
        //Para que se vea el contenido de la ventana.
        this.setVisible(true);
    }
    
    private javax.swing.JButton b_enviar;
    private javax.swing.JButton b_act_GPS;
    private javax.swing.JButton b_des_GPS;
    private javax.swing.JButton b_examinar;
    private javax.swing.JButton b_menu_sensores;
    private javax.swing.JButton b_obt_loc;
    private javax.swing.JButton b_salir;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel fondo;
    private javax.swing.JTextField ruta_Foto;
    private javax.swing.JTextField t_nomPac;
    private javax.swing.JFrame actual;
    private JPanel estePanel = this;
    public static long cont = 0;
    
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==b_salir)
		{
			try {
				PantallaInicio.sm.Escribir("SALIR\n");
				System.out.println("Desde el cliente: SALIR");
				String respuesta = PantallaInicio.sm.Leer();
				System.out.println("Desde el servidor: "+respuesta);
				PantallaInicio.sm.Escribir("fin");
				PantallaInicio.cerrarSocket();
				Pantalla.cerrar();
			} catch (IOException e1) {
			
			}
		}
		else if(e.getSource()==b_act_GPS)
		{
			try {
				PantallaInicio.sm.Escribir("ONGPS\n");
				System.out.println("Desde el cliente: ONGPS");
				String respuesta = PantallaInicio.sm.Leer();
				System.out.println("Desde el servidor: "+respuesta);
				if(respuesta.equals("529 ERR GPS en estado ON."))
				{
					JOptionPane.showMessageDialog(this, "El GPS ya esta activado", "GPS activo", JOptionPane.ERROR_MESSAGE);
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			gpsActivado = true;
		}
		else if(e.getSource() == b_des_GPS)
		{
			try {
				PantallaInicio.sm.Escribir("OFFGPS\n");
				System.out.println("Desde el cliente: OFFGPS");
				String respuesta = PantallaInicio.sm.Leer();
				System.out.println("Desde el servidor: "+respuesta);
				if(respuesta.equals("530 ERR GPS en estado OFF."))
				{
					JOptionPane.showMessageDialog(this, "El GPS ya esta desactivado", "GPS desactivado", JOptionPane.ERROR_MESSAGE);
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			gpsActivado = false;
		}
		else if(e.getSource() == b_menu_sensores)
		{
			actual = (JFrame) UtilidadesGUI.getContenedorPrincipal(estePanel);
			actual.getContentPane().remove(0);
			actual.getContentPane().add(new Sensores(nombrePaciente));
			actual.setPreferredSize(new Dimension(445, 472));
			actual.pack();
			actual.repaint();
			actual.setLocationRelativeTo(null);
		}
		else if(e.getSource() == b_obt_loc)
		{
			try {
				PantallaInicio.sm.Escribir("GET_LOC\n");
				System.out.println("Desde el cliente: GET_LOC");
				String respuesta = PantallaInicio.sm.Leer();
				System.out.println("Desde el servidor: "+respuesta);
				if(respuesta.equals("Se ha cerrado la conexión con el socket y hemos creado otra nueva"))
				{
					PantallaInicio.sm.Escribir("fin");
					PantallaInicio.cerrarSocket();
					JOptionPane.showMessageDialog(this, "Se ha cerrado la conexión con el socket y hemos creado otra nueva", "Socket desactivado", JOptionPane.INFORMATION_MESSAGE);
					Pantalla2 p = new Pantalla2();
					p.setVisible(true);
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}