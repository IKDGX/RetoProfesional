package bases_de_datos;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import menus.FormateadorTexto;
import model.Usuario;
import model.Vehiculo;

public class AlquilerRepo {
	
	public static void mostrarAlquileres(String lista[], Usuario user)throws SQLException{
		String query = "SELECT * FROM Alquiler WHERE DNI = ?";
		try(PreparedStatement prep = ConectorBD.conexion.prepareStatement(query)){
			prep.setString(1, user.getDni());
			
			ResultSet res = prep.executeQuery();
			if(!res.next()) {
				System.out.println("Registro vacío");
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
	
	
	public static void realizarAlquiler(Vehiculo vehiculo, Usuario user, int dias,Date fecha)throws SQLException {
		String query = "INSERT INTO Alquiler (DNI,Matricula,fecha,dias,cargo)VALUES(?,?,?,?,?)";
		
		try(PreparedStatement prep = ConectorBD.conexion.prepareStatement(query)){
			prep.setString(1, user.getDni());
			prep.setString(2, vehiculo.getMatricula());
			prep.setDate(3, fecha);
			prep.setInt(4, dias);
			prep.setFloat(5, dias*vehiculo.getPrecio());
			prep.executeUpdate();
			VehiculoRepo.actuEstado(vehiculo, user);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}

	}
	
	public static Date fechaDispo(Vehiculo vehiculo)throws SQLException{
		String query = "SELECT DATE_ADD(fecha, INTERVAL dias DAY) AS fecha FROM Alquiler A JOIN Vehiculo V ON A.Matricula = V.Matricula WHERE A.Matricula = ? ORDER BY fecha DESC";
		
		try(PreparedStatement check = ConectorBD.conexion.prepareStatement(query)){
			
			check.setString(1, vehiculo.getMatricula());
			
			ResultSet res = check.executeQuery();
			res.next();
			return res.getDate(1);
			
		}
	}
	
	public static void Alquileres(String[] titulo)throws SQLException {
		String query = "SELECT * FROM Alquiler";

		
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
				for(int i=1; i<7;i++) {
					FormateadorTexto.tablas(res.getString(i));
				}
			}
			while(res.next());
			FormateadorTexto.formateo(6);
			
		}
	}
	
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
