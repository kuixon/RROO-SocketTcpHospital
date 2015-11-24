package Ventanas;

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

public class PantallaInicio2 extends javax.swing.JPanel implements ActionListener {

	public static int cont = 0;
	public static SocketManager sm2;
	public static String modifiedSentence = null;
	
	public PantallaInicio2() {
        initComponents();
    }

    private void initComponents() {

    	jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        fondo = new javax.swing.JLabel();
        t_Contraseña = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        t_Usuario1 = new javax.swing.JTextField();
        b_aceptar = new javax.swing.JButton();

        setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("REGISTRO PARA LA NUEVA CONEXION");
        add(jLabel1);
        jLabel1.setBounds(40, 70, 400, 40);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel2.setForeground(new java.awt.Color(0,250,154));
        jLabel2.setText("Contraseña:");
        add(jLabel2);
        jLabel2.setBounds(60, 220, 120, 22);
        add(t_Contraseña);
        t_Contraseña.setFont(new java.awt.Font("Tahoma", 1, 12));
        t_Contraseña.setBackground(new java.awt.Color(238,44,44));
        t_Contraseña.setBounds(180, 217, 210, 27);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel3.setForeground(new java.awt.Color(0,250,154));
        jLabel3.setText("Usuario:");
        add(jLabel3);
        jLabel3.setBounds(60, 170, 80, 22);
        add(t_Usuario1);
        t_Usuario1.setFont(new java.awt.Font("Tahoma", 1, 12));
        t_Usuario1.setBackground(new java.awt.Color(238,44,44));
        t_Usuario1.setBounds(150, 167, 240, 27);

        b_aceptar.setFont(new java.awt.Font("Tahoma", 1, 11));
        b_aceptar.setText("Validar Usuario");
        b_aceptar.setBackground(new java.awt.Color(255, 0, 0));
        add(b_aceptar);
        b_aceptar.setBounds(160, 280, 120, 30);
        
        fondo.setIcon(new javax.swing.ImageIcon("src\\Imagenes\\fondo.jpg"));
        add(fondo);
        fondo.setBounds(0, 0, 445, 472);
        
        //Para que se habra con este tamaño como mínimo.
        this.setMinimumSize(new Dimension(445, 472));
        
        //Para que se vea el contenido de la ventana.
        this.setVisible(true);
        
        //Añadir funcionalidad a los botones.
        b_aceptar.addActionListener(this);
    }
    
    public static void cerrarSocket()
    {
    	try {
			sm2.CerrarSocket();
		} catch (IOException e) {
			System.out.println("CASCA AL CERRARLO");
			e.printStackTrace();
		}
    }
    
    private javax.swing.JButton b_aceptar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel fondo;
    private javax.swing.JPasswordField t_Contraseña;
    private javax.swing.JTextField t_Usuario1;
    private javax.swing.JFrame actual;
    private JPanel estePanel = this;

	public void actionPerformed(ActionEvent e) {
		
		if((e.getSource() == b_aceptar)&&(b_aceptar.getText().equals("Validar Usuario")))
		{
			try {
				if(cont==0)
				{
					sm2 = new SocketManager("127.0.0.1", 2600);
					cont++;
				}
				String sentence = "USER "+t_Usuario1.getText();
				System.out.print("Desde el cliente: "+sentence);
                sm2.Escribir(sentence + '\n');
                modifiedSentence = sm2.Leer();
                System.out.println();
                System.out.println("Desde el servidor: "+modifiedSentence);
                if(modifiedSentence.equals("311 OK Bienvenido "+t_Usuario1.getText()+"."))
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
				System.out.println("No carga bien el nuevo socket.");
			}
		}
		else
		{
			if((e.getSource() == b_aceptar)&&(b_aceptar.getText().equals("Conectar")))
			{
				try {
					String sentence = "PASS "+t_Contraseña.getText();
					System.out.print("Desde el cliente: "+sentence);
	                sm2.Escribir(sentence + '\n');
	                modifiedSentence = sm2.Leer();
	                System.out.println();
	                System.out.println("Desde el servidor: "+modifiedSentence);
	                if(modifiedSentence.equals("312 OK Bienvenido al sistema"))
	                {
	                	actual = (JFrame) UtilidadesGUI.getContenedorPrincipal(estePanel);
						actual.getContentPane().remove(0);
						actual.getContentPane().add(new PantallaCoordenadas());
						actual.setPreferredSize(new Dimension(445, 472));
						actual.pack();
						actual.repaint();
						actual.setLocationRelativeTo(null);
	                }
	                else if(modifiedSentence.equals("513 ERR Falta la clave."))
	                {
	                	JOptionPane.showMessageDialog(this, "Falta la contraseña", "Campo contraseña vacio", JOptionPane.ERROR_MESSAGE);
	                }
	                else if(modifiedSentence.equals("512 ERR La clave es incorrecta."))
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