package bases_de_datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import menus.FormateadorTexto;
import model.Local;


public class LocalRepo {
	
	public static void mostrarLocales(String lista[])throws SQLException{
		String query = "SELECT * FROM CLocal";
		for(String l: lista) {
			FormateadorTexto.tablas(l);
		}
		try (Statement statement = ConectorBD.conexion.createStatement()){
			ResultSet resultado = statement.executeQuery(query);
			while(resultado.next()) {
				for(int i=1;i<4;i++) {
					FormateadorTexto.tablas(resultado.getString(i));
				}
			}
			FormateadorTexto.formateo(3);
		}
	}
	
	public static boolean encontrarLocal(Local local)throws SQLException {
		String query = "SELECT COUNT(*) FROM CLocal WHERE ID = ?";
		try(PreparedStatement check = ConectorBD.conexion.prepareStatement(query)){
			
			check.setString(1, local.getId());
			ResultSet res = check.executeQuery();
			res.next();
			if(res.getInt(1)!=1) {
				return false;
			}
		}
		return true;
	}
	
}
