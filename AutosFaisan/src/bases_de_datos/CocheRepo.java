package bases_de_datos;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import menus.FormateadorTexto;
import menus.MenuUser;
import menus.Utiles;
import model.Vehiculo;

public class CocheRepo {
	
	//Solicito el coche especificado por el usuario junto a todos su atributos.
	
	public static void CocheElegido(String lista[],Vehiculo vehiculo)throws SQLException{
		String query = "SELECT V.Matricula, Modelo, Color, Precio_dia, Tipo FROM Vehiculo V JOIN VCoche C ON V.Matricula = C.Matricula WHERE V.Matricula = ?";                               
		for(String l: lista) {
			FormateadorTexto.tablas(l);
		}
		try (PreparedStatement prep = ConectorBD.conexion.prepareStatement(query)){
			prep.setString(1, vehiculo.getMatricula());
			ResultSet resultado = prep.executeQuery();
			resultado.next();
			for(int i=1;i<6;i++) {
				FormateadorTexto.tablas(resultado.getString(i));
			}
			vehiculo.setPrecio(resultado.getFloat(4));
			
			FormateadorTexto.formateo(5);

		}
		catch(SQLException e) {
			System.out.println("No se ha encontrado el vehículo especificado");
			return;
		}
		
	}
	/*Esta consulta pide a la base de datos un listado de los coches disponibles en las fechas especificadas por el cliente,
	 en el local al que ha accedido previamente y guarda un registro de las matrículas para que no pueda reservar un coche de otro
	 local o un tipo de vehículo distinto habiendo seleccionado un listado en concreto */

	public static void mostrarCoches2(String lista[],String local, Date fechainicio, Date fechafin)throws SQLException{
		String query = "SELECT V.Matricula, Modelo, Color, Precio_dia, Tipo FROM (Vehiculo V JOIN VCoche C ON V.Matricula = C.Matricula)JOIN CLocal L ON V.ID = L.ID WHERE L.ID = ? AND disponibilidad = 1 \r\n"
				+ "UNION \r\n"
				+ "SELECT V.Matricula, Modelo, Color, Precio_dia, Tipo FROM (Vehiculo V JOIN VCoche C ON V.Matricula = C.Matricula) JOIN CLocal L ON V.ID = L.ID \r\n"
				+ "WHERE V.Matricula NOT IN (SELECT V.Matricula FROM (Vehiculo V JOIN CLocal L ON V.ID = L.ID)JOIN Alquiler A ON V.Matricula = A.Matricula WHERE ((? BETWEEN fecha AND DATE_ADD(fecha, INTERVAL dias DAY)) OR fecha BETWEEN ? AND ? ) AND L.ID = ?)AND L.ID = ?";                               

		try (PreparedStatement prep = ConectorBD.conexion.prepareStatement(query)){
			prep.setString(1, local);
			prep.setDate(2, fechainicio);
			prep.setDate(3, fechainicio);
			prep.setDate(4, fechafin);
			prep.setString(5, local);
			prep.setString(6, local);
			ResultSet resultado = prep.executeQuery();
			if(!resultado.next()) {
				System.out.println("No hay coches disponibles en esas fechas\n");
				MenuUser.Catalogo();
			}
			for(String l: lista) {
				FormateadorTexto.tablas(l);
			}
			do {
				Utiles.matricula(resultado.getString(1));
				for(int i=1;i<6;i++) {
					FormateadorTexto.tablas(resultado.getString(i));
				}
			}
			while(resultado.next()); 
			FormateadorTexto.formateo(5);

		}
	}
}
