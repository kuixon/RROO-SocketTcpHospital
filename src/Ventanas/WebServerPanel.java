package Ventanas;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import util.SocketManager;
import util.UtilidadesGUI;

public class WebServerPanel extends javax.swing.JPanel implements ActionListener, MouseListener {

    public static List <SocketManager> lista = new ArrayList<SocketManager>();
	
    public WebServerPanel() {
        initComponents();
    }

    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        fondo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jLabel6 = new javax.swing.JLabel();
        t_maxConexiones = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        b_Aceptar = new javax.swing.JButton();
        b_desactivar = new javax.swing.JButton();
        b_gestionUsuarios = new javax.swing.JButton();
        
        /*Inicializamos el DefaultTableModel pasándole el arrayBidimensional
        (que obtenemos  cuando le volcamos la lista) y un array de objetos
        de una posición (Nuestra única columna de la tabla).
        */
        modTabla = new DefaultTableModel(volcarListaAArray(), new Object [] {"Conexiones"});
        
        //Inicializamos nuestra tabla pasando por parámetro el DefaultListModel anterior.
        tabla = new javax.swing.JTable(modTabla);
        
        setLayout(null);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 24));
        jLabel5.setText("ADMINISTRADOR");
        jLabel5.setForeground(new java.awt.Color(255, 0, 0));
        add(jLabel5);
        jLabel5.setBounds(240, 10, 220, 30);

        tabla.setBackground(new java.awt.Color(238,44,44));
        tabla.setForeground(new java.awt.Color(0,0,0));
        tabla.setModel(modTabla);
        
        /*Hacemos que nuestra tabla implemente el MouseListener. Dentro del MouseListener
         * vamos a crear un MouseAdapter que nos servira para saber que fila hemos
         * clickado. Una vez que conocemos la fila, solo tenemos que almacenar en una
         * variable el valor del SocketManager que tenemos almacenado en esa fila (Más 
         * adelante utilizaremos esa variable).
         */
        tabla.addMouseListener(this);
        tabla.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent e)
        	{
        		int fila = tabla.rowAtPoint(e.getPoint());
        		texto_socket = (String) tabla.getValueAt(fila, 0);
        	}
		});
        jScrollPane1.setViewportView(tabla);

        add(jScrollPane1);
        jScrollPane1.setBounds(50, 190, 580, 170);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel6.setText("Desactivar conexiones:");
        jLabel6.setForeground(new java.awt.Color(238,44,44));
        add(jLabel6);
        jLabel6.setBounds(30, 90, 220, 20);

        t_maxConexiones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_maxConexionesActionPerformed(evt);
            }
        });
        add(t_maxConexiones);
        t_maxConexiones.setFont(new java.awt.Font("Tahoma", 1, 14));
        t_maxConexiones.setForeground(new java.awt.Color(0,0,0));
        t_maxConexiones.setBackground(new java.awt.Color(238,44,44));
        t_maxConexiones.setBounds(590, 87, 59, 30);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel7.setForeground(new java.awt.Color(238,44,44));
        jLabel7.setText("Nº máximo de conexiones:");
        add(jLabel7);
        jLabel7.setBounds(330, 90, 250, 20);

        add(b_Aceptar);
        b_Aceptar.setBounds(420, 120, 90, 23);
        b_Aceptar.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Imagenes//b_aceptarServidor.jpg")));
        b_Aceptar.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent arg0) {
				
			}
			
			public void mousePressed(MouseEvent arg0) {
				
			}
			
			public void mouseExited(MouseEvent arg0) {
				b_Aceptar.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Imagenes//b_aceptarServidor.jpg")));
			}
			
			public void mouseEntered(MouseEvent arg0) {
				b_Aceptar.setRolloverIcon(new ImageIcon(getClass().getClassLoader().getResource("Imagenes//b_aceptarServidor.gif")));
			}
			
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});

        add(b_desactivar);
        b_desactivar.setBounds(80, 120, 110, 20);
        b_desactivar.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Imagenes//b_desactivarServidor.jpg")));
        b_desactivar.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent arg0) {
				
			}
			
			public void mousePressed(MouseEvent arg0) {
				
			}
			
			public void mouseExited(MouseEvent arg0) {
				b_desactivar.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Imagenes//b_desactivarServidor.jpg")));
			}
			
			public void mouseEntered(MouseEvent arg0) {
				b_desactivar.setRolloverIcon(new ImageIcon(getClass().getClassLoader().getResource("Imagenes//b_desactivarServidor.gif")));
			}
			
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});
        
        add(b_gestionUsuarios);
        b_gestionUsuarios.setBounds(20, 20, 155, 20);
        b_gestionUsuarios.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Imagenes//b_gestionarUsuarios.jpg")));
        b_gestionUsuarios.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent arg0) {
				
			}
			
			public void mousePressed(MouseEvent arg0) {
				
			}
			
			public void mouseExited(MouseEvent arg0) {
				b_gestionUsuarios.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Imagenes//b_gestionarUsuarios.jpg")));
			}
			
			public void mouseEntered(MouseEvent arg0) {
				b_gestionUsuarios.setRolloverIcon(new ImageIcon(getClass().getClassLoader().getResource("Imagenes//b_gestionarUsuarios.gif")));
			}
			
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});
        
        fondo.setIcon(new javax.swing.ImageIcon("src\\Imagenes\\fondo2.jpg"));
        add(fondo);
        fondo.setBounds(0, 0, 693, 430);
        
        //Para que se habra con este tamaño como mínimo.
        this.setMinimumSize(new Dimension(693, 430));
        
        //Para que se vea el contenido de la ventana.
        this.setVisible(true);
        
        //Añadir funcionalidad a los botones.
        b_desactivar.addActionListener(this);
        b_Aceptar.addActionListener(this);
        b_gestionUsuarios.addActionListener(this);
        
        //Ayuda en los botones.
        t_maxConexiones.setToolTipText("Por defecto, el numero maximo de conexiones son 2.");
        
    }

    public void borrar(String s)
    {
    	boolean enc = false;
    	int pos = 0;
    	while(!enc&&pos < lista.size())
    	{
    		if(lista.get(pos).toString().equals(s))
    		{
    			enc = true;
    		}
    		else
    		{
    			pos++;
    		}
    	}
    	if(enc)
    	{
    		try {
				lista.get(pos).CerrarSocket();
			} catch (IOException e) {
				e.printStackTrace();
			}
    		lista.remove(pos);
    	}    	
    }
    
    public static String[][] volcarListaAArray()
    {
    	String [][] s = new String[lista.size()][1];
    	for(int i = 0; i < lista.size(); i++)
    	{
    		s[i][0] = String.valueOf(lista.get(i).toString());
    	}
    	return s;
    }
    
    public static void cerrar()
    {
    	System.exit(-1);
    }
    
    private void t_maxConexionesActionPerformed(java.awt.event.ActionEvent evt) {
    	
    }

    private javax.swing.JButton b_Aceptar;
    private javax.swing.JButton b_desactivar;
    private javax.swing.JButton b_gestionUsuarios;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel fondo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField t_maxConexiones;
    private DefaultTableModel modTabla;
    public static String[] s;
    public static String texto_socket;
    private javax.swing.JFrame actual;
    private JPanel estePanel = this;
    public static int max_con = 2;

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == b_desactivar)
		{
			borrar(texto_socket);
			max_con++;
			JOptionPane.showMessageDialog(this, "Al borrar esta conexion, puede registrase un usuario mas (COMO MINIMO).", "Hueco libre creado", JOptionPane.INFORMATION_MESSAGE);
			actual = (JFrame) UtilidadesGUI.getContenedorPrincipal(estePanel);
			actual.getContentPane().remove(0);
			actual.getContentPane().add(new WebServerPanel());
			actual.setPreferredSize(new Dimension(693, 430));
			actual.pack();
			actual.repaint();
			actual.setLocationRelativeTo(null);
		}
		else if(e.getSource() == b_Aceptar)
		{
			max_con = Integer.parseInt(t_maxConexiones.getText());
			JOptionPane.showMessageDialog(this, "Acabas de modificar el numero maximo de conexiones", "Numero maximo de conexiones modificado", JOptionPane.INFORMATION_MESSAGE);
		}
		else if(e.getSource() == b_gestionUsuarios)
		{
			actual = (JFrame) UtilidadesGUI.getContenedorPrincipal(estePanel);
			actual.getContentPane().remove(0);
			actual.getContentPane().add(new GestionUsuarios());
			actual.setPreferredSize(new Dimension(693, 430));
			actual.pack();
			actual.repaint();
			actual.setLocationRelativeTo(null);
		}
	}


	public void mouseClicked(MouseEvent arg0) {
		
	}

	
	public void mouseEntered(MouseEvent arg0) {
		
	}

	
	public void mouseExited(MouseEvent arg0) {
		
	}

	
	public void mousePressed(MouseEvent arg0) {
		
	}

	
	public void mouseReleased(MouseEvent arg0) {
		
	}
}
