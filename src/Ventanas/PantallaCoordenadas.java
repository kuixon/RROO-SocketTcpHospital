
package Ventanas;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class PantallaCoordenadas extends javax.swing.JPanel implements ActionListener {

    public static int idAleatorio;
	public PantallaCoordenadas() {
        initComponents();
    }

    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        fondo = new javax.swing.JLabel();
        b_aceptar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        b_salir = new javax.swing.JButton();

        setLayout(null);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 24));
        jLabel5.setForeground(new java.awt.Color(255, 0, 0));
        jLabel5.setText("COORDENADAS");
        add(jLabel5);
        jLabel5.setBounds(130, 60, 190, 40);

        b_aceptar.setFont(new java.awt.Font("Tahoma", 1, 11));
        b_aceptar.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Imagenes//b_aceptarServidor.jpg")));
        b_aceptar.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent arg0) {
				
			}
			
			public void mousePressed(MouseEvent arg0) {
				
			}
			
			public void mouseExited(MouseEvent arg0) {
				b_aceptar.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Imagenes//b_aceptarServidor.jpg")));
			}
			
			public void mouseEntered(MouseEvent arg0) {
				b_aceptar.setRolloverIcon(new ImageIcon(getClass().getClassLoader().getResource("Imagenes//b_aceptarServidor.gif")));
			}
			
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});
        add(b_aceptar);
        b_aceptar.setBounds(260, 250, 90, 23);
        
        add(b_salir);
        b_salir.setIcon(new javax.swing.ImageIcon("src//Imagenes//cerrar.jpg"));
        b_salir.setBounds(10, 10, 40, 40);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel2.setForeground(new java.awt.Color(0,250,154));
        jLabel2.setText("Obtener coordenadas:");
        add(jLabel2);
        jLabel2.setBounds(40, 250, 210, 20);
        
        fondo.setIcon(new javax.swing.ImageIcon("src\\Imagenes\\fondo.jpg"));
        add(fondo);
        fondo.setBounds(0, 0, 445, 472);
        
        //Para que se habra con este tamaño como mínimo.
        this.setMinimumSize(new Dimension(445, 472));
        
        //Para que se vea el contenido de la ventana.
        this.setVisible(true);
        
        //Añadir funcionalidad a los botones.
        b_aceptar.addActionListener(this);
        b_salir.addActionListener(this);
    }

    private javax.swing.JButton b_aceptar;
    private javax.swing.JButton b_salir;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel fondo;
	
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == b_salir)
		{
			try {
				PantallaInicio2.sm2.Escribir("SALIR\n");
				System.out.println("Desde el cliente: SALIR");
				String respuesta = PantallaInicio2.sm2.Leer();
				System.out.println("Desde el servidor: "+respuesta);
				PantallaInicio2.sm2.Escribir("fin");
				PantallaInicio2.cerrarSocket();
				Pantalla2.cerrar();
			} catch (IOException e1) {
			
			}
		}
		else if(arg0.getSource() == b_aceptar)
		{
			idAleatorio = 1 + (int) Math.floor(Math.random()*2);
			try {
				PantallaInicio2.sm2.Escribir("GETCOOR "+idAleatorio + '\n');
				System.out.println("Desde el cliente: GETCOOR "+idAleatorio);
				String respuesta = PantallaInicio2.sm2.Leer();
				System.out.println("Desde el servidor: "+respuesta);
				if(respuesta.equals("527 ERR Celda desconocida."))
				{
					JOptionPane.showMessageDialog(this, "El ID de esta coordenada no existe", "ID inexistente", JOptionPane.ERROR_MESSAGE);
				}
				else if(respuesta.equals("528 ERR Falta parámetro cell_id."))
				{
					JOptionPane.showMessageDialog(this, "No se ha introducido el parámetro id", "ID vacio", JOptionPane.ERROR_MESSAGE);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
