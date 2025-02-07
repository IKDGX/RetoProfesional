package bases_de_datos;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import menus.FormateadorTexto;
import menus.MenuUser;
import model.Usuario;
import model.Vehiculo;

public class AlquilerRepo {
	
	//Solicito todos los alquileres realizados por un cliente.
	
	public static void mostrarAlquileres(String lista[], Usuario user)throws SQLException{
		String query = "SELECT * FROM Alquiler WHERE DNI = ?";
		try(PreparedStatement prep = ConectorBD.conexion.prepareStatement(query)){
			prep.setString(1, user.getDni());
			
			ResultSet res = prep.executeQuery();
			if(!res.next()) {
				System.out.println("No alquileres en tu historial");
				return;
			}
			for(String l: lista) {
				FormateadorTexto.tablas(l);
			}
			do {
				for(int i=1;i<7;i++) {
					FormateadorTexto.tablas(res.getString(i));
				}
			}
			while(res.next());
			FormateadorTexto.formateo(6);

		}
			
	}
	
	//Guardo el nuevo alquiler en la base de datos.
	
	public static void realizarAlquiler(Vehiculo vehiculo, Usuario user, int dias,Date fecha)throws SQLException {
		String query = "INSERT INTO Alquiler (DNI,Matricula,fecha,dias,cargo)VALUES(?,?,?,?,?)";
		
		try(PreparedStatement prep = ConectorBD.conexion.prepareStatement(query)){
			prep.setString(1, user.getDni());
			prep.setString(2, vehiculo.getMatricula());
			prep.setDate(3, fecha);
			prep.setInt(4, dias);
			prep.setFloat(5, dias*vehiculo.getPrecio());
			prep.executeUpdate();
			MenuUser.fechainicio = null;
			MenuUser.fechafin = null;
			VehiculoRepo.actuEstado(vehiculo, user);
		}

	}
	
	//Solicito todos los alquileres guardados.
	
	public static void Alquileres(String[] titulo)throws SQLException {
		String query = "SELECT A.Codigo,A.DNI,A.Matricula,A.fecha,A.dias,A.cargo, C.ID FROM (Alquiler A JOIN Vehiculo V ON A.Matricula = V.Matricula)JOIN CLocal C ON V.ID = C.ID";

		
		try(Statement st = ConectorBD.conexion.createStatement()){
			
			ResultSet res = st.executeQuery(query);
			if(!res.next()) {
				System.out.println("Registro vecío");
				return;
			}
			for(int i=0;i<titulo.length;i++) {
				FormateadorTexto.tablas(titulo[i]);
			}
			do {
				for(int i=1; i<8;i++) {
					FormateadorTexto.tablas(res.getString(i));
				}
			}
			while(res.next());
			FormateadorTexto.formateo(7);
			
		}
	}
	
	//Pido el dinero total recibido de los alquileres, la cantidad del alquileres de coches y motos.
	
	public static void DineroTotal()throws SQLException {
		String query = "SELECT IFNULL(SUM(cargo),0)FROM Alquiler";
		String query2 = "SELECT IFNULL(COUNT(*),0) FROM Alquiler A JOIN VCoche V ON A.Matricula = V.Matricula";
		String query3 = "SELECT IFNULL(COUNT(*),0) FROM Alquiler A JOIN VMoto V ON A.Matricula = V.Matricula";
		String queries[] = {query,query2,query3};
		
		try(Statement st = ConectorBD.conexion.createStatement()){
			ResultSet res;
			for(int i = 0;i<queries.length;i++) {
				res = st.executeQuery(queries[i]);
				res.next();
				FormateadorTexto.tablas(res.getString(1));
			}
		
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	//Solicito todos los alquileres realizados en un local.
	
	public static void AlquilerLocal(String[] titulo, String local)throws SQLException {
		String query = "SELECT Codigo, DNI, A.Matricula, fecha, dias, cargo FROM Alquiler A JOIN Vehiculo V ON A.Matricula = V.Matricula WHERE V.ID = ? ";

		try(PreparedStatement prep = ConectorBD.conexion.prepareStatement(query)){
			prep.setString(1, local);
			ResultSet res = prep.executeQuery();
			
			if(!res.next()) {
				System.out.println("El registro está vacío");
				return;
			}
			for(int i = 0; i<titulo.length;i++) {
				FormateadorTexto.tablas(titulo[i]);
			}
			do {
				for(int i = 1; i<7;i++) {
					FormateadorTexto.tablas(res.getString(i));
				}
			}
			while(res.next());
			FormateadorTexto.formateo(6);
			
		}
	}
	
}
