package bases_de_datos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import menus.TextoForm;


public class LocalRepo {
	
	public static void mostrarLocales(String lista[])throws SQLException{
		String query = "SELECT * FROM CLocal";
		ArrayList<String> texto = new ArrayList<>();
		for(String l: lista) {
			texto.add(l);
		}
		try (Statement statement = ConectorBD.conexion.createStatement()){
			ResultSet resultado = statement.executeQuery(query);
			while(resultado.next()) {
				for(int i=1;i<4;i++) {
					texto.add(resultado.getString(i));
				}
			}
			TextoForm.formateo(texto,3);
		}
	}
	
}
