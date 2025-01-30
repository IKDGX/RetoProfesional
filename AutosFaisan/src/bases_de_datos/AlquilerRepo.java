package bases_de_datos;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import menus.TextoForm;
import model.Usuario;
import model.Vehiculo;

public class AlquilerRepo {
	
	public static void mostrarAlquileres(String lista[], Usuario user)throws SQLException{
		String query = "SELECT * FROM Alquiler WHERE DNI = ?";
		String querycheck = "SELECT COUNT(*) FROM Alquiler WHERE DNI = ?";
		
		try(PreparedStatement check = ConectorBD.conexion.prepareStatement(querycheck)){
			check.setString(1, user.getDni());
			ResultSet result = check.executeQuery();
			result.next();
			if(result.getInt(1)==0) {
				System.out.println("Registro vacío");
				return;
			}
		}
		for(String l: lista) {
			TextoForm.tablas(l);
		}
		try(PreparedStatement prep = ConectorBD.conexion.prepareStatement(query)){
			prep.setString(1, user.getDni());
			
			ResultSet res = prep.executeQuery();
			while(res.next()) {
				for(int i=1;i<7;i++) {
					TextoForm.tablas(res.getString(i));
				}
				TextoForm.formateo(6);
			}
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
	
	public static Date fechaDispo(int dias, Vehiculo vehiculo)throws SQLException{
		String query = "SELECT DATE_ADD(fecha, INTERVAL ? DAY) AS fecha FROM Alquiler A JOIN Vehiculo V ON A.Matricula = V.Matricula WHERE A.Matricula = ?";
		
		try(PreparedStatement check = ConectorBD.conexion.prepareStatement(query)){
			
			check.setInt(1, dias);
			check.setString(2, vehiculo.getMatricula());
			
			ResultSet res = check.executeQuery();
			res.next();
			return res.getDate(1);
			
		}
	}
	
	public static void Alquileres()throws SQLException {
		String query = "SELECT * FROM Alquiler";
		
		try(Statement st = ConectorBD.conexion.createStatement()){
			
			ResultSet res = st.executeQuery(query);
			
			while(res.next()) {
				for(int i=1; i<7;i++) {
					TextoForm.tablas(res.getString(i));
				}
			}
			TextoForm.formateo(6);
			
		}
	}
	
	
}
