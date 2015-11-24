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

public final class WebServer2 extends javax.swing.JFrame
{	
	public static void main(String argv[]) throws Exception
	{
		int port = 2600;
		System.out.println("Servidor 2 activo...");
		ServerSocket wellcomeSocket = new ServerSocket(port);

		while (true)
		{
			SocketManager sockManager = new SocketManager(wellcomeSocket.accept());
			
			HttpRequest2 request = new HttpRequest2(sockManager);

			Thread thre = new Thread(request);

			thre.start();			
		}
	}
}
