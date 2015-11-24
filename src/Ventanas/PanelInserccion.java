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
import javax.swing.JOptionPane;

import BaseDeDatos.DataBase;

public class PanelInserccion extends javax.swing.JPanel implements ActionListener {

    public PanelInserccion() {
        initComponents();
    }

    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        fondo = new javax.swing.JLabel();
        t_Contraseña = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        t_Usuario1 = new javax.swing.JTextField();
        b_aceptar = new javax.swing.JButton();

        setLayout(null);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 24));
        jLabel5.setForeground(new java.awt.Color(255, 0, 0));
        jLabel5.setText("CREAR NUEVO USUARIO");
        add(jLabel5);
        jLabel5.setBounds(200, 30, 300, 30);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel2.setForeground(new java.awt.Color(238,44,44));
        jLabel2.setText("Contraseña:");
        add(jLabel2);
        jLabel2.setBounds(180, 190, 120, 22);
        add(t_Contraseña);
        t_Contraseña.setFont(new java.awt.Font("Tahoma", 1, 12));
        t_Contraseña.setForeground(new java.awt.Color(0,0,0));
        t_Contraseña.setBackground(new java.awt.Color(238,44,44));
        t_Contraseña.setBounds(300, 190, 210, 20);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel3.setForeground(new java.awt.Color(238,44,44));
        jLabel3.setText("Usuario:");
        add(jLabel3);
        jLabel3.setBounds(180, 140, 80, 22);
        add(t_Usuario1);
        t_Usuario1.setFont(new java.awt.Font("Tahoma", 1, 12));
        t_Usuario1.setForeground(new java.awt.Color(0,0,0));
        t_Usuario1.setBackground(new java.awt.Color(238,44,44));
        t_Usuario1.setBounds(270, 140, 240, 20);

        b_aceptar.setFont(new java.awt.Font("Tahoma", 1, 11));
        add(b_aceptar);
        b_aceptar.setBounds(280, 250, 120, 30);
        b_aceptar.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Imagenes//b_aceptarInserccion.jpg")));
        b_aceptar.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent arg0) {
				
			}
			
			public void mousePressed(MouseEvent arg0) {
				
			}
			
			public void mouseExited(MouseEvent arg0) {
				b_aceptar.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Imagenes//b_aceptarInserccion.jpg")));
			}
			
			public void mouseEntered(MouseEvent arg0) {
				b_aceptar.setRolloverIcon(new ImageIcon(getClass().getClassLoader().getResource("Imagenes//b_aceptarInserccion.gif")));
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
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel fondo;
    private javax.swing.JPasswordField t_Contraseña;
    private javax.swing.JTextField t_Usuario1;
    
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == b_aceptar)
		{
			boolean enc = false;
			ResultSet rs = DataBase.getInstance().consult("SELECT * FROM PACIENTE");
			try {
				while(rs.next()&&!enc)
				{
					if(rs.getString("username").equals(t_Usuario1.getText()))
					{
						enc = true;
					}
				}
				if(enc)
				{
					JOptionPane.showMessageDialog(this, "Ya hay un usuario registrado con ese nombre", "Usuario existente", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					DataBase.getInstance().update("INSERT INTO PACIENTE (username, password) VALUES ('"+t_Usuario1.getText()+"', '"+t_Contraseña.getText()+"')");
					JOptionPane.showMessageDialog(this, "Has creado un nuevo usuario correctamente", "Nuevo Usuario", JOptionPane.INFORMATION_MESSAGE);
					WebServerPanel.cerrar();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}