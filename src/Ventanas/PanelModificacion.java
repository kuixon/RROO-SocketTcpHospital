package Ventanas;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import util.UtilidadesGUI;
import BaseDeDatos.DataBase;

public class PanelModificacion extends javax.swing.JPanel implements ActionListener {

    public static String id;
	
    public PanelModificacion(String identificativo) {
        id = identificativo;
    	initComponents();
    }

    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        fondo = new javax.swing.JLabel();
        t_Contraseña = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        t_ID = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        t_Usuario1 = new javax.swing.JTextField();
        b_aceptar = new javax.swing.JButton();

        setLayout(null);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 24));
        jLabel5.setForeground(new java.awt.Color(255, 0, 0));
        jLabel5.setText("MODIFICAR USUARIO");
        add(jLabel5);
        jLabel5.setBounds(210, 10, 270, 30);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel2.setForeground(new java.awt.Color(0, 51, 204));
        jLabel2.setText("Contraseña:");
        jLabel2.setForeground(new java.awt.Color(238,44,44));
        add(jLabel2);
        jLabel2.setBounds(170, 240, 120, 22);
        add(t_Contraseña);
        t_Contraseña.setFont(new java.awt.Font("Tahoma", 1, 12));
        t_Contraseña.setForeground(new java.awt.Color(0,0,0));
        t_Contraseña.setBackground(new java.awt.Color(238,44,44));
        t_Contraseña.setBounds(290, 240, 210, 20);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel4.setForeground(new java.awt.Color(0, 51, 204));
        jLabel4.setText("ID:");
        jLabel4.setForeground(new java.awt.Color(238,44,44));
        add(jLabel4);
        jLabel4.setBounds(170, 140, 30, 20);
        add(t_ID);
        t_ID.setText(id);        
        t_ID.setEnabled(false);
        t_ID.setBounds(210, 140, 290, 20);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel3.setForeground(new java.awt.Color(0, 51, 204));
        jLabel3.setText("Usuario:");
        jLabel3.setForeground(new java.awt.Color(238,44,44));
        add(jLabel3);
        jLabel3.setBounds(170, 190, 80, 22);
        add(t_Usuario1);
        t_Usuario1.setFont(new java.awt.Font("Tahoma", 1, 12));
        t_Usuario1.setForeground(new java.awt.Color(0,0,0));
        t_Usuario1.setBackground(new java.awt.Color(238,44,44));
        t_Usuario1.setBounds(260, 190, 240, 20);

        b_aceptar.setFont(new java.awt.Font("Tahoma", 1, 11));
        add(b_aceptar);
        b_aceptar.setBounds(270, 300, 120, 30);
        b_aceptar.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Imagenes//b_aceptarModificacion.jpg")));
        b_aceptar.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent arg0) {
				
			}
			
			public void mousePressed(MouseEvent arg0) {
				
			}
			
			public void mouseExited(MouseEvent arg0) {
				b_aceptar.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Imagenes//b_aceptarModificacion.jpg")));
			}
			
			public void mouseEntered(MouseEvent arg0) {
				b_aceptar.setRolloverIcon(new ImageIcon(getClass().getClassLoader().getResource("Imagenes//b_aceptarModificacion.gif")));
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
        b_aceptar.addActionListener(this);
        
    }

    private javax.swing.JButton b_aceptar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel fondo;
    private javax.swing.JPasswordField t_Contraseña;
    private javax.swing.JTextField t_ID;
    private javax.swing.JTextField t_Usuario1;
    private javax.swing.JFrame actual;
    private JPanel estePanel = this;
    
    public static void vaciarStringBidimensional(String [][] s)
    {
    	for(int i = 0; i < s.length; i++)
    	{
    		for(int j = 0; j < 3; j++)
    		{
    			s[i][j] = null;
    		}
    	}
    }
    
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == b_aceptar)
		{
			if((!t_Usuario1.getText().equals(""))&&(!t_Contraseña.getText().equals("")))
			{
				DataBase.getInstance().update("UPDATE PACIENTE SET username = '"+t_Usuario1.getText()+"' WHERE id = '"+id+"'");
				DataBase.getInstance().update("UPDATE PACIENTE SET password = '"+t_Contraseña.getText()+"' WHERE id = '"+id+"'");
				GestionUsuarios.lID.remove(id);
				GestionUsuarios.lPassword.remove(id);
				GestionUsuarios.lUsername.remove(id);
				GestionUsuarios.lID.add(id);
				GestionUsuarios.lUsername.add(t_Usuario1.getText());
				GestionUsuarios.lPassword.add(t_Contraseña.getText());
				JOptionPane.showMessageDialog(this, "El usuario ha sido modificado correctamente", "Usuario modificado", JOptionPane.INFORMATION_MESSAGE);
				WebServerPanel.cerrar();
			}
		}
	}
}