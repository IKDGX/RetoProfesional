package bases_de_datos;

import java.sql.*;

import enums.TipoUsuario;
import menus.MenuUser;
import model.Usuario;

public class UsuarioRepo {
	
	public static void registrarUsuario(Usuario user, String clave) throws SQLException{
		String query = "INSERT INTO Usuario VALUES(?,?,?,?,?,?)";
		
		if(encontrarUsuario(user.getDni(),clave)>0) {
			System.out.println("Ese usuario ya está registrado en la base de datos, intenta iniciar sesión");
			return;
		}
		
		try(PreparedStatement prepSt = ConectorBD.conexion.prepareStatement(query)){
			
			prepSt.setString(1, user.getDni());
			prepSt.setString(2, user.getNombre());
			prepSt.setString(3, user.getApellido());
			prepSt.setDate(4, user.getFec_nac());
			prepSt.setString(5, clave);
			prepSt.setString(6, user.getTipo().toString());
			prepSt.executeUpdate();
			System.out.println("Usuario registrado de forma exitosa");
			
			MenuUser.menuFunciones(user);

		}
	}
	
	public static void iniciarSesion(Usuario user, String dni, String clave) throws SQLException {
		
		String query = "SELECT * FROM Usuario WHERE DNI = ? AND clave = ?";
		
		if(encontrarUsuario(dni,clave)==0) {
			System.out.println("No se ha podido encontrar al usuario\nSi eres un nuevo usuario debes registrarte antes de poder iniciar sesión");
			return;
		}
		try(PreparedStatement check = ConectorBD.conexion.prepareStatement(query)){
			
			check.setString(1, dni);
			check.setString(2, clave);
			
			ResultSet resultado = check.executeQuery();
			resultado.next();
			if(resultado.getString(1)==null) {
				return;
			}
			else {
				user.setDni(resultado.getString(1));
				user.setNombre(resultado.getString(2));
				user.setApellido(resultado.getString(3));
				user.setFec_nac(resultado.getDate(4));
				user.setTipo(TipoUsuario.valueOf(resultado.getString(6)));
			}

			
		}
		catch(SQLException e) {
			System.out.println("Las credenciales introducidas no coinciden");
			return;
		}

	}
	
	public static int encontrarUsuario(String dni, String clave) throws SQLException {
		String query = "SELECT COUNT(*) FROM Usuario WHERE DNI = ?";
		int res = 0;
		try(PreparedStatement check = ConectorBD.conexion.prepareStatement(query)){
			
			check.setString(1, dni);
			
			ResultSet resultado = check.executeQuery();
			resultado.next();
			res = resultado.getInt(1);

			}
		catch(SQLException e){
			e.printStackTrace();
		}
		return res;
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

