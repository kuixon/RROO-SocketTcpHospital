package Ventanas;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ImageIcon;

public class Pantalla extends javax.swing.JFrame {

    public Pantalla() {
        initComponents();
    }

    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		//Añadimos el panel al JFrame.
		getContentPane().add(new PantallaInicio());
		
		//Para que se vea el panel.
		this.setVisible(true);
		
		//Para que se habra con este tamaño como mínimo.
        this.setMinimumSize(new Dimension(445, 472));

        //Para que no se pueda agrandar la ventana, que sea de tamaño fijo.
        this.setResizable(true);
        
        //Para que la ventana salga centrada.
        this.setLocationRelativeTo(null);
        
        //Para poner icono en la ventana.
        this.setIconImage(new ImageIcon("src/Imagenes/Icono.jpg").getImage());
        
        pack();
    }
    
    public static void cerrar()
    {
    	System.exit(-1);
    }

    public static void main(String args[]) {
    
    	try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Pantalla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pantalla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pantalla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pantalla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
    	java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pantalla().setVisible(true);
            }
        });
    }
}