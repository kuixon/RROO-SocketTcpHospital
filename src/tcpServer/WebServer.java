package tcpServer;
import java.awt.Dimension;
import java.io.* ;
import java.net.* ;
import java.util.* ;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Ventanas.PantallaMenu;
import Ventanas.PantallaServidor;
import Ventanas.WebServerPanel;
import util.*;

public final class WebServer extends javax.swing.JFrame
{	
	public static void main(String argv[]) throws Exception
	{
		int port = 6968;
		System.out.println("Servidor activo...");
		ServerSocket wellcomeSocket = new ServerSocket(port);
		PantallaServidor p = new PantallaServidor();

		while (true)
		{
			
			if(WebServerPanel.max_con >0)
			{
				SocketManager sockManager = new SocketManager(wellcomeSocket.accept());
				
				HttpRequest request = new HttpRequest(sockManager);

				Thread thre = new Thread(request);

				thre.start();
				
				WebServerPanel.lista.add(sockManager);
				
				p.getContentPane().remove(0);
				p.getContentPane().add(new WebServerPanel());
				p.setPreferredSize(new Dimension(693, 430));
				p.pack();
				p.repaint();
				p.setLocationRelativeTo(null);
				WebServerPanel.max_con--;
				System.out.println("Puedes hacer "+WebServerPanel.max_con+" conexion mas.");
			}
			else
			{
				while(WebServerPanel.max_con == 0)
				{
					System.out.println("Numero maximo de conexiones alcanzado. Desconecte una conexion para entrar.");
				}
				SocketManager sockManager = new SocketManager(wellcomeSocket.accept());
				
				HttpRequest request = new HttpRequest(sockManager);

				Thread thre = new Thread(request);

				thre.start();
				
				WebServerPanel.lista.add(sockManager);
				
				p.getContentPane().remove(0);
				p.getContentPane().add(new WebServerPanel());
				p.setPreferredSize(new Dimension(693, 430));
				p.pack();
				p.repaint();
				p.setLocationRelativeTo(null);
				WebServerPanel.max_con--;
				System.out.println("Puedes hacer "+WebServerPanel.max_con+" conexion mas.");

			}
		}
	}
}