package Ventanas;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import util.UtilidadesGUI;
import BaseDeDatos.DataBase;

public class GestionUsuarios extends javax.swing.JPanel implements ActionListener, MouseListener {

	public static int cont = 0;
	public static String[][] array = null;
	public static String id = null;
	public static List <String> lID = new ArrayList<String>(); 
	public static List <String> lUsername = new ArrayList<String>();
	public static List <String> lPassword = new ArrayList<String>();

    public GestionUsuarios() {
        initComponents();
    }

    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        fondo = new javax.swing.JLabel();
        b_insertar = new javax.swing.JButton();
        b_borrar = new javax.swing.JButton();
        b_modificar = new javax.swing.JButton();

        setLayout(null);
        
        if(cont == 0)
        {
        	cargarArrays();
        	cont++;
        }
        array = volcarListasAArray();
        
        modelo = new DefaultTableModel(array, new Object [] {"ID","Username","Password"});

        jTable1.setBackground(new java.awt.Color(238,44,44));
        jTable1.setForeground(new java.awt.Color(0,0,0));
        jTable1.setModel(modelo);
        jTable1.addMouseListener(this);
        jTable1.addMouseListener(new MouseAdapter() {
        	
        	public void mouseClicked(MouseEvent e) {
        		int fila = jTable1.rowAtPoint(e.getPoint());
        		id = (String) jTable1.getValueAt(fila, 0);
        	}
		});
        jScrollPane1.setViewportView(jTable1);

        add(jScrollPane1);
        jScrollPane1.setBounds(32, 165, 620, 220);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 24));
        jLabel5.setForeground(new java.awt.Color(255, 0, 0));
        jLabel5.setText("LISTA DE USUARIOS REGISTRADOS");
        add(jLabel5);
        jLabel5.setBounds(130, 10, 440, 30);

        add(b_insertar);
        b_insertar.setBounds(490, 110, 140, 23);
        b_insertar.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Imagenes//b_insertarUsuario.jpg")));
        b_insertar.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent arg0) {
				
			}
			
			public void mousePressed(MouseEvent arg0) {
				
			}
			
			public void mouseExited(MouseEvent arg0) {
				b_insertar.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Imagenes//b_insertarUsuario.jpg")));
			}
			
			public void mouseEntered(MouseEvent arg0) {
				b_insertar.setRolloverIcon(new ImageIcon(getClass().getClassLoader().getResource("Imagenes//b_inertarUsuario.gif")));
			}
			
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});

        add(b_borrar);
        b_borrar.setBounds(60, 110, 130, 23);
        b_borrar.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Imagenes//b_borrarUsuario.jpg")));
        b_borrar.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent arg0) {
				
			}
			
			public void mousePressed(MouseEvent arg0) {
				
			}
			
			public void mouseExited(MouseEvent arg0) {
				b_borrar.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Imagenes//b_borrarUsuario.jpg")));
			}
			
			public void mouseEntered(MouseEvent arg0) {
				b_borrar.setRolloverIcon(new ImageIcon(getClass().getClassLoader().getResource("Imagenes//b_borrarUsuario.gif")));
			}
			
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});

        add(b_modificar);
        b_modificar.setBounds(270, 110, 140, 23);
        b_modificar.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Imagenes//b_modificarUsuario.jpg")));
        b_modificar.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent arg0) {
				
			}
			
			public void mousePressed(MouseEvent arg0) {
				
			}
			
			public void mouseExited(MouseEvent arg0) {
				b_modificar.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Imagenes//b_modificarUsuario.jpg")));
			}
			
			public void mouseEntered(MouseEvent arg0) {
				b_modificar.setRolloverIcon(new ImageIcon(getClass().getClassLoader().getResource("Imagenes//b_modificarUsuario.gif")));
			}
			
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});
        
        fondo.setIcon(new javax.swing.ImageIcon("src\\Imagenes\\fondo2.jpg"));
        add(fondo);
        fondo.setBounds(0, 0, 693, 430);
        
        //Ayuda en los botones.
        b_borrar.setToolTipText("Selecciona al usuario en la tabla antes de darle a BORRAR");
        b_modificar.setToolTipText("Selecciona al usuario en la tabla antes de darle a MODIFICAR");
        
        //Para que se habra con este tamaño como mínimo.
        this.setMinimumSize(new Dimension(693, 430));
        
        //Para que se vea el contenido de la ventana.
        this.setVisible(true);
        
        //Añadir funcionalidad a los botones.
        b_borrar.addActionListener(this);
        b_insertar.addActionListener(this);
        b_modificar.addActionListener(this);
        
    }
    
    public void cargarArrays()
    {
    	ResultSet rs = DataBase.getInstance().consult("SELECT * FROM PACIENTE");
    	try {
			while(rs.next())
			{
				lID.add(rs.getString("id"));
				lUsername.add(rs.getString("username"));
				lPassword.add(rs.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    public String[][] volcarListasAArray()
    {
    	String [][] s = new String[lID.size()][3];
    	for(int i = 0; i < lID.size(); i++)
    	{
    		s[i][0] = lID.get(i);
    		s[i][1] = lUsername.get(i);
    		s[i][2] = lPassword.get(i);
    	}
    	return s;
    }
    
    public void borrarPaciente(String s)
    {
    	boolean enc = false;
    	int pos = 0;
    	while(!enc&&pos < lID.size())
    	{
    		if(lID.get(pos).equals(s))
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
    		lID.remove(pos);
    		lUsername.remove(pos);
    		lPassword.remove(pos);
    	}    	
    }
    
    private javax.swing.JButton b_borrar;
    private javax.swing.JButton b_insertar;
    private javax.swing.JButton b_modificar;
    private DefaultTableModel modelo;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel fondo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JFrame actual;
    private JPanel estePanel = this;

	public void mouseClicked(MouseEvent e) {
		
	}
	
	public void mouseEntered(MouseEvent e) {
		
	}
	
	public void mouseExited(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		
	}

	public void mouseReleased(MouseEvent e) {
		
	}

	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == b_borrar)
		{
			borrarPaciente(id);
			DataBase.getInstance().update("DELETE FROM PACIENTE WHERE id = '"+id+"'");
			actual = (JFrame) UtilidadesGUI.getContenedorPrincipal(estePanel);
			actual.getContentPane().remove(0);
			actual.getContentPane().add(new GestionUsuarios());
			actual.setPreferredSize(new Dimension(693, 430));
			actual.pack();
			actual.repaint();
			actual.setLocationRelativeTo(null);
		}
		else if(arg0.getSource() == b_modificar)
		{
			actual = (JFrame) UtilidadesGUI.getContenedorPrincipal(estePanel);
			actual.getContentPane().remove(0);
			actual.getContentPane().add(new PanelModificacion(id));
			actual.setPreferredSize(new Dimension(693, 430));
			actual.pack();
			actual.repaint();
			actual.setLocationRelativeTo(null);
		}
		else if(arg0.getSource() == b_insertar)
		{
			actual = (JFrame) UtilidadesGUI.getContenedorPrincipal(estePanel);
			actual.getContentPane().remove(0);
			actual.getContentPane().add(new PanelInserccion());
			actual.setPreferredSize(new Dimension(693, 430));
			actual.pack();
			actual.repaint();
			actual.setLocationRelativeTo(null);
		}
	}
}