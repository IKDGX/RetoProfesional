package bases_de_datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import menus.FormateadorTexto;
import model.Vehiculo;

public class CocheRepo {
	
	//Pido todos los coches que hay en un local.
	
	public static void mostrarCoches(String lista[],String local)throws SQLException{
		String query = "SELECT V.Matricula, Modelo, Color, Precio_dia, disponibilidad, Tipo FROM (Vehiculo V JOIN VCoche C ON V.Matricula = C.Matricula)JOIN CLocal L ON V.ID = L.ID WHERE L.ID = ?";                               
		for(String l: lista) {
			FormateadorTexto.tablas(l);
		}
		try (PreparedStatement prep = ConectorBD.conexion.prepareStatement(query)){
			prep.setString(1, local);
			ResultSet resultado = prep.executeQuery();
			while(resultado.next()) {
				for(int i=1;i<7;i++) {
					FormateadorTexto.tablas(resultado.getString(i));
				}
			}
			FormateadorTexto.formateo(6);

		}
	}
	
	//Solicito el coche especificado por el usuario junto a todos su atributos.
	
	public static void CocheElegido(String lista[],Vehiculo vehiculo)throws SQLException{
		String query = "SELECT V.Matricula, Modelo, Color, Precio_dia, disponibilidad, Tipo FROM Vehiculo V JOIN VCoche C ON V.Matricula = C.Matricula WHERE V.Matricula = ?";                               
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
			System.out.println("No se ha encontrado el vehículo especificado");
			return;
		}
		
	}
	

}
