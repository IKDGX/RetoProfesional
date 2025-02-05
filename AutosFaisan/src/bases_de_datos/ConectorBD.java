package bases_de_datos;

import java.sql.*;

public class ConectorBD {
	
	public static Connection conexion;
	
	//Me conecto a la base de datos.
	
	public static void conectar() {
		
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver cargado");        
            try{
 
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/Concesionario","usuario","Plaiaundi1dam3");
          
            System.out.println("Conexion establecida");
          
        }catch(Exception e){
            System.out.println("Error en la conexion");
        }
        }catch(Exception e){
            System.out.println("Error en el driver");
        }
	}
	
	//Me desconecto.
	
	public static void desconectar() throws SQLException {
		
		conexion.close();
		System.out.println("Conexión cerrada");
	}

}
