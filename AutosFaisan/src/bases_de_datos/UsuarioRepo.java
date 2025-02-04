package bases_de_datos;

import java.sql.*;

import enums.TipoUsuario;
import model.Usuario;

public class UsuarioRepo {
	
	public static void registrarUsuario(Usuario user, String clave) throws SQLException{
		String query = "INSERT INTO Usuario VALUES(?,?,?,?,?,?)";
		
		try(PreparedStatement prepSt = ConectorBD.conexion.prepareStatement(query)){
			
			prepSt.setString(1, user.getDni());
			prepSt.setString(2, user.getNombre());
			prepSt.setString(3, user.getApellido());
			prepSt.setDate(4, user.getFec_nac());
			prepSt.setString(5, clave);
			prepSt.setString(6, user.getTipo().toString());
			prepSt.executeUpdate();
		}
		catch(SQLException e) {
			System.out.println("Ese usuario ya está registrado en la base de datos, intenta iniciar sesión");
			user.setTipo(null);
			return;
		}
		
		/*Preparo la consulta de isert con el usuario creado y en caso de que falle por la restricción de la Primary key (Mismo DNI) 
		 hago un catch y le indico al usuario que ese DNI ya está registrado.*/
		System.out.println("Usuario registrado de forma exitosa");
	}
	
	public static void iniciarSesion(Usuario user, String dni, String clave) throws SQLException {
		
		String query = "SELECT * FROM Usuario WHERE DNI = ?";

		try(PreparedStatement check = ConectorBD.conexion.prepareStatement(query)){
			
			check.setString(1, dni);
			ResultSet resultado = check.executeQuery();
			
			if(!resultado.next()) {
				System.out.println("No se ha podido encontrar al usuario\nSi eres un nuevo usuario debes registrarte antes de poder iniciar sesión");
				return;
			}
			if(!resultado.getString(5).equals(clave)) {
				System.out.println("Las credenciales introducidas no coinciden");
			}
			else {
				user.setDni(resultado.getString(1));
				user.setNombre(resultado.getString(2));
				user.setApellido(resultado.getString(3));
				user.setFec_nac(resultado.getDate(4));
				user.setTipo(TipoUsuario.valueOf(resultado.getString(6)));
			}
			
		}

	}

	
	
	public static String cantidadUsuarios() throws SQLException {
		String query= "SELECT COUNT(*) FROM Usuario";
		
		try(Statement st = ConectorBD.conexion.createStatement()){
			
			ResultSet res = st.executeQuery(query);
			res.next();
			return res.getString(1);
		}
	
	}
}

