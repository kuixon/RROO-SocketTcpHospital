package util;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class UtilidadesGUI {

	/**
	 * Devuelve el contenedor principal del componente indicado
	 * 
	 * @param c
	 *            Cualquier componente visual
	 * @return Devuelve el contenedor de primer nivel al final de su cadena de
	 *         contenedores
	 */
	public static Container getContenedorPrincipal(Component c) {
		if (c == null)
			return null;
		Container ret = c.getParent();
		while (ret != null) {
			c = ret;
			ret = ret.getParent();
		}
		return (Container) c;
	}
}