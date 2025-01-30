package bases_de_datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import menus.MenuUser;
import model.Usuario;
import model.Vehiculo;

public class VehiculoRepo {

	public static void actuEstado(Vehiculo vehiculo, Usuario user)throws SQLException {
		String query = "UPDATE Vehiculo SET Disponibilidad = false WHERE Matricula = ?";
		
		try(PreparedStatement prep = ConectorBD.conexion.prepareStatement(query)){
			
			prep.setString(1, vehiculo.getMatricula());
			
			prep.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Alquiler realizado con éxito");
		MenuUser.menuFunciones(user);
	}
	
	public static void updateGeneral()throws SQLException {
		String query = "UPDATE Vehiculo SET Disponibilidad = 1 WHERE Matricula =(SELECT A.Matricula FROM Alquiler A WHERE current_date()> DATE_ADD(fecha, INTERVAL dias DAY)) OR Matricula NOT IN (SELECT Matricula FROM Alquiler)";
		
		try(Statement prep = ConectorBD.conexion.createStatement()){
			
			prep.executeUpdate(query);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void encontrarVehiculo(Vehiculo vehiculo)throws SQLException {
		String query = "SELECT COUNT(*) FROM Vehiculo WHERE Matricula = ?";
		try(PreparedStatement check = ConectorBD.conexion.prepareStatement(query)){
			
			check.setString(1, vehiculo.getMatricula());
			ResultSet res = check.executeQuery();
			res.next();
			if(res.getInt(1)!=1) {
				MenuUser.MenuAlquiler();
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
