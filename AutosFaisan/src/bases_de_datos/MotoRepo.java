package bases_de_datos;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import menus.FormateadorTexto;
import menus.Utiles;
import model.Vehiculo;

public class MotoRepo {

	//Solicito la moto especificada por el usuario junto a todos su atributos.
	
	public static void MotoElegida(String lista[],Vehiculo vehiculo)throws SQLException{
		String query = "SELECT V.Matricula, Modelo, Color, Precio_dia, disponibilidad, Cilindrada FROM Vehiculo V JOIN VMoto C ON V.Matricula = C.Matricula WHERE V.Matricula = ?";                               
		for(String l: lista) {
			FormateadorTexto.tablas(l);
		}
		try (PreparedStatement prep = ConectorBD.conexion.prepareStatement(query)){
			prep.setString(1, vehiculo.getMatricula());
			ResultSet resultado = prep.executeQuery();
			resultado.next();
			for(int i=1;i<7;i++) {
				FormateadorTexto.tablas(resultado.getString(i));
			}
			vehiculo.setDisponibilidad(resultado.getBoolean(5));
			vehiculo.setPrecio(resultado.getFloat(4));
			FormateadorTexto.formateo(6);
		}
		catch(SQLException e) {
			e.printStackTrace();
			return;
		}
	}
	
	public static void mostrarMotos2(String lista[],String local, Date fechainicio, Date fechafin)throws SQLException{
		String query = "SELECT V.Matricula, Modelo, Color, Precio_dia, disponibilidad, Cilindrada FROM (Vehiculo V JOIN VMoto C ON V.Matricula = C.Matricula)JOIN CLocal L ON V.ID = L.ID WHERE L.ID = ? AND disponibilidad = 1 UNION SELECT V.Matricula, Modelo, Color, Precio_dia, disponibilidad, Cilindrada FROM ((Vehiculo V JOIN VMoto C ON V.Matricula = C.Matricula)JOIN Alquiler A ON V.Matricula = A.Matricula) JOIN CLocal L ON V.ID = L.ID WHERE (fecha > ? OR DATE_ADD(fecha, INTERVAL dias DAY)< ?) AND L.ID = ?";                               
		try (PreparedStatement prep = ConectorBD.conexion.prepareStatement(query)){
			prep.setString(1, local);
			prep.setDate(2, fechafin);
			prep.setDate(3, fechainicio);
			prep.setString(4, local);
			ResultSet resultado = prep.executeQuery();
			if(!resultado.next()) {
				System.out.println("No hay motos disponibles en esas fechas");
				return;
			}
			for(String l: lista) {
				FormateadorTexto.tablas(l);
			}
			do {
				Utiles.matricula(resultado.getString(1));
				for(int i=1;i<7;i++) {
					FormateadorTexto.tablas(resultado.getString(i));
				}
			}
			while(resultado.next()); 
			FormateadorTexto.formateo(6);

		}
	}
	
	
}
