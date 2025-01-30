package bases_de_datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import menus.TextoForm;
import model.Vehiculo;

public class MotoRepo {
	public static void mostrarMotos(String lista[],String local)throws SQLException{
		String query = "SELECT V.Matricula, Modelo, Color, Precio_dia, disponibilidad, Cilindrada FROM (Vehiculo V JOIN VMoto C ON V.Matricula = C.Matricula)JOIN CLocal L ON V.ID = L.ID WHERE L.ID = ?";                               
		ArrayList<String> texto = new ArrayList<>();
		for(String l: lista) {
			texto.add(l);
		}
		try (PreparedStatement prep = ConectorBD.conexion.prepareStatement(query)){
			prep.setString(1, local);
			ResultSet resultado = prep.executeQuery();
			while(resultado.next()) {
				for(int i=1;i<7;i++) {
					texto.add(resultado.getString(i));
				}
			}
			TextoForm.formateo(texto,6);
		}
	}
	
	public static void MotoElegida(String lista[],Vehiculo vehiculo)throws SQLException{
		String query = "SELECT V.Matricula, Modelo, Color, Precio_dia, disponibilidad, Cilindrada FROM Vehiculo V JOIN VMoto C ON V.Matricula = C.Matricula WHERE V.Matricula = ?";                               
		ArrayList<String> texto = new ArrayList<>();
		for(String l: lista) {
			texto.add(l);
		}
		try (PreparedStatement prep = ConectorBD.conexion.prepareStatement(query)){
			prep.setString(1, vehiculo.getMatricula());
			ResultSet resultado = prep.executeQuery();
			resultado.next();
			for(int i=1;i<7;i++) {
				texto.add(resultado.getString(i));
			}
			vehiculo.setDisponibilidad(resultado.getBoolean(5));
			vehiculo.setPrecio(resultado.getFloat(4));
			TextoForm.formateo(texto,6);
		}
		catch(SQLException e) {
			e.printStackTrace();
			return;
		}
	}
	
	
}
