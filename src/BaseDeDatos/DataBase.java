package BaseDeDatos;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.xml.crypto.Data;

import utilities.FileUtils;

public class DataBase {

	private static DataBase	instance;	// Instancia del objecto DataBase
	private Connection	connection; // Conexion a la base de datos

	private DataBase()
	{
		try
		{
			Class.forName("org.sqlite.JDBC");
			// Se introduce la direccion donde queremos guardar nuestra base de datos. 
			this.connection = DriverManager.getConnection("jdbc:sqlite:data/patients.sqlite3"); 
			this.update("PRAGMA encoding = \"UTF-8\";");
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		/*Si no existe la base de datos en esa direccion, se crean las tablas con el
		 * metodo create_tables
		 */
		if (numberOfTables() == 0)
		{
			create_tables();
		}
	}

	public void disconnect()
	{
		try
		{
			connection.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	private void create_tables()
	{
		try
		{
			// Se actualiza la base de datos con el texto que tiene el archivo
			// data/createDB.sql
			this.update(FileUtils.toString("data/createDB.sql"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		(new File("createDB.sql")).delete();
	}

	private Statement create_statement()
	{
		Statement statement = null;
		try
		{
			statement = this.connection.createStatement();
		}
		catch (SQLException e)
		{
			System.out.println("Error creating statement.");
		}
		return statement;
	}

	//Utilizaremos el método consul para obtener consultas SELECT.
	public ResultSet consult(String consult)
	{
		Statement statement = this.create_statement();
		try
		{
			return statement.executeQuery(consult);
		}
		catch (SQLException e)
		{
			System.out.println("Error consulting database.");
			e.printStackTrace();
			return null;
		}
	}

	//Utilizaremos el método update para hacer modificaciones en la base de datos
	//INSERT, DELETE, UPDATE, CREATE...
	public int update(String consult) 
	{
		Statement statement = this.create_statement();
		try
		{
			return statement.executeUpdate(consult);
		}
		catch (SQLException e)
		{
			System.out.println("Error updating database.\n " + e.toString());
			return 0;
		}
	}

	// Sirve para contar las filas de una tabla con una condicion dada.
	public int count(String table, String condition) 
	{
		int number = 0;
		String where = condition == null ? "" : " WHERE " + condition;
		String consult = "SELECT COUNT(*) as number FROM " + table + where
		+ ";";
		ResultSet result = consult(consult);
		try
		{
			if (result != null)
			{
				while (result.next())
				{
					number = result.getInt("number");
				}
			}
		}
		catch (SQLException e)
		{
			System.out.println("Error counting tables.");
		}
		return number;
	}

	// Devuelve el numero de tablas de la base de datos.
	private int numberOfTables()
	{
		return count("sqlite_master", "type='table'");
	}

	public static DataBase getInstance()
	{
		if (instance == null)
		{
			instance = new DataBase();
		}
		return instance;
	}
	
	public static void main(String args[]) {
		
	}
}