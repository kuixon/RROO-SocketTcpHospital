package Ventanas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import util.SocketManager;
import util.UtilidadesGUI;

public class PantallaInicio extends javax.swing.JPanel implements ActionListener {

	public static int cont = 0;
	public static SocketManager sm;
	public static String modifiedSentence = null;
	
	public PantallaInicio() {
        initComponents();
    }

    private void initComponents() {

    	jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        fondo = new javax.swing.JLabel();
        t_Contraseña = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        t_IP1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        t_Usuario1 = new javax.swing.JTextField();
        b_aceptar = new javax.swing.JButton();

        setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24));
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("REGISTRO");
        add(jLabel1);
        jLabel1.setBounds(150, 70, 130, 40);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel2.setForeground(new java.awt.Color(0,250,154));
        jLabel2.setText("Contraseña:");
        add(jLabel2);
        jLabel2.setBounds(60, 270, 120, 22);
        add(t_Contraseña);
        t_Contraseña.setFont(new java.awt.Font("Tahoma", 1, 12));
        t_Contraseña.setBackground(new java.awt.Color(238,44,44));
        t_Contraseña.setBounds(180, 267, 210, 27);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel4.setForeground(new java.awt.Color(0,250,154));
        jLabel4.setText("IP:");
        add(jLabel4);
        jLabel4.setBounds(60, 170, 30, 20);
        add(t_IP1);
        t_IP1.setFont(new java.awt.Font("Tahoma", 1, 12));
        t_IP1.setBackground(new java.awt.Color(238,44,44));
        t_IP1.setBounds(100, 167, 290, 27);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel3.setForeground(new java.awt.Color(0,250,154));
        jLabel3.setText("Usuario:");
        add(jLabel3);
        jLabel3.setBounds(60, 220, 80, 22);
        add(t_Usuario1);
        t_Usuario1.setFont(new java.awt.Font("Tahoma", 1, 12));
        t_Usuario1.setBackground(new java.awt.Color(238,44,44));
        t_Usuario1.setBounds(150, 217, 240, 27);

        b_aceptar.setFont(new java.awt.Font("Tahoma", 1, 11));
        b_aceptar.setText("Validar IP");
        b_aceptar.setBackground(new java.awt.Color(255, 0, 0));
        add(b_aceptar);
        b_aceptar.setBounds(160, 330, 120, 30);
        
        fondo.setIcon(new javax.swing.ImageIcon("src\\Imagenes\\fondo.jpg"));
        add(fondo);
        fondo.setBounds(0, 0, 445, 472);
        
        //Ayuda en los botones.
        t_IP1.setToolTipText("Puedes introducir localhost para la validación de la IP.");
        
        //Para que se habra con este tamaño como mínimo.
        this.setMinimumSize(new Dimension(445, 472));
        
        //Para que se vea el contenido de la ventana.
        this.setVisible(true);
        
        //Añadir funcionalidad a los botones.
        b_aceptar.addActionListener(this);
    }
    
    public boolean validarIP()
    {
    	String cadena = t_IP1.getText();
    	if((cadena.length() == 9)||(cadena.equals("localhost")))
    	{
    		if(cadena.equals("localhost"))
    		{
    			return true;
    		}
    		else
    		{
    			char car = cadena.charAt(3);
    			char car2 = cadena.charAt(5);
    			char car3 = cadena.charAt(7);
    			char punto = '.';
    			System.out.println(car);
    			if((car == punto)&&(car2 == punto)&&(car3 == punto))
    			{
    				return true;
    			}
    			else
    			{
    				return false;
    			}
    		}
    	}
		return false;
    }
    
    private javax.swing.JButton b_aceptar;
    private javax.swing.JLabel fondo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPasswordField t_Contraseña;
    private javax.swing.JTextField t_IP1;
    private javax.swing.JTextField t_Usuario1;
    private javax.swing.JFrame actual;
    private JPanel estePanel = this;
	
    public static void cerrarSocket()
    {
    	try {
			sm.CerrarSocket();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
    public void actionPerformed(ActionEvent e) {
		if ((e.getSource() == b_aceptar)&&(b_aceptar.getText().equals("Validar IP")))
		{
			
			if(validarIP())
			{
				b_aceptar.setText("Validar Usuario");
				t_IP1.setEnabled(false);
			}
			else
			{
				JOptionPane.showMessageDialog(this, "La IP no es correcta", "Error de introducción de datos", JOptionPane.ERROR_MESSAGE);
			}
		}
		else
		{
			if((e.getSource() == b_aceptar)&&(b_aceptar.getText().equals("Validar Usuario")))
			{
				try {
					if(cont==0)
					{
						sm = new SocketManager("127.0.0.1", 6968);
						cont++;
					}
					String sentence = "USUARIO "+t_Usuario1.getText();
					System.out.print("Desde el cliente: "+sentence);
	                sm.Escribir(sentence + '\n');
	                modifiedSentence = sm.Leer();
	                System.out.println();
	                System.out.println("Desde el servidor: "+modifiedSentence);
	                if(modifiedSentence.equals("301 OK Bienvenido "+t_Usuario1.getText()+"."))
	                {
	                	b_aceptar.setText("Conectar");
	    				t_Usuario1.setEnabled(false);
	                }
	                else
	                {
	                	if(modifiedSentence.equals("ERR El usuario no esta registrado en el sistema"))
	                	{
	                		JOptionPane.showMessageDialog(this, "Este usuario NO esta registrado en el sistema", "Usuario NO registrado", JOptionPane.ERROR_MESSAGE);
	                	}
	                	else
	                	{
	                		JOptionPane.showMessageDialog(this, "Falta el nombre de usuario", "Campo Usuario vacio", JOptionPane.ERROR_MESSAGE);
	                	}
	                }
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			else
			{
				if((e.getSource() == b_aceptar)&&(b_aceptar.getText().equals("Conectar")))
				{
					try {
						String sentence = "CLAVE "+t_Contraseña.getText();
						System.out.print("Desde el cliente: "+sentence);
		                sm.Escribir(sentence + '\n');
		                modifiedSentence = sm.Leer();
		                System.out.println();
		                System.out.println("Desde el servidor: "+modifiedSentence);
		                if(modifiedSentence.equals("302 OK Bienvenido al sistema"))
		                {
		                	actual = (JFrame) UtilidadesGUI.getContenedorPrincipal(estePanel);
							actual.getContentPane().remove(0);
							actual.getContentPane().add(new PantallaMenu(t_Usuario1.getText()));
							actual.setPreferredSize(new Dimension(445, 472));
							actual.pack();
							actual.repaint();
							actual.setLocationRelativeTo(null);
		                }
		                else if(modifiedSentence.equals("503 ERR Falta la clave."))
		                {
		                	JOptionPane.showMessageDialog(this, "Falta la contraseña", "Campo contraseña vacio", JOptionPane.ERROR_MESSAGE);
		                }
		                else if(modifiedSentence.equals("502 ERR La clave es incorrecta."))
		                {
		                	JOptionPane.showMessageDialog(this, "La contraseña introducida no se corresponde con el usuario introducido (USUARIO PREVIAMENTE REGISTRADO)", "Clave incorrecta", JOptionPane.ERROR_MESSAGE);
		                }
					} catch (IOException e1) {
						e1.printStackTrace();
					}					
				}
			}
		}
	}
}