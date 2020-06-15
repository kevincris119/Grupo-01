package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;


/*
 * Permite crer una conexion a la BD
 * Se debe tener:
 * 1) Driver JDBC
 * 2) Ip del Servidor
 * 3) puerto
 * 4) Nombre de la BD
 * 5) Usuario
 * 6) Password  
 * 
 */
public class MySqlDBConexion {

	
	//permite el acceso los parámetros del archivo properties
		private static ResourceBundle rb = 	ResourceBundle.getBundle("database_conexion");
		
		//Accede a las clases del mysqlconnectorXXXX.jar
		static{
			try {
				Class.forName(rb.getString("driver"));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		//Metodo para crear conexiones
		public static Connection  getConexion(){
			Connection salida = null;
			try {
				salida = DriverManager.getConnection(
										rb.getString("url"),
										rb.getString("username"),
										rb.getString("password"));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return salida;	
		}
	
	

}
