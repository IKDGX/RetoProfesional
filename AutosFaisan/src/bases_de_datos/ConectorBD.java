package bases_de_datos;

import java.sql.*;

public class ConectorBD {
	
	public static Connection conexion;
	
	public static void conectar() {
		
        try{
            //Cargamos el driver, el driver es la libreria que nos permite conectarnos a la BD
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver cargado");        
            try{
            //Establecemos la conexion con la BD            
            //La BD se encuentra en el localhost(en mi ordenador)
            //El usuario es root y la contraseña es 1234
            //La conexion se hace a traves del puerto 3306
            //La BD se llama cine, es la que viene por defecto en MySQL
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/Concesionario","usuario","Plaiaundi1dam3");
          
            System.out.println("Conexion establecida");
          
        }catch(Exception e){
            System.out.println("Error en la conexion");
        }
        }catch(Exception e){
            System.out.println("Error en el driver");
        }
	}
	
	public static void desconectar() throws SQLException {
		
		conexion.close();
		System.out.println("Conexión cerrada");
	}

}
