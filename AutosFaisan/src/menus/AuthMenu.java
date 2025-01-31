package menus;

import java.sql.SQLException;

import bases_de_datos.ConectorBD;
import bases_de_datos.Escaner;
import enums.TipoUsuario;
import model.Usuario;

public class AuthMenu {
	
	public static Usuario user = new Usuario();
	
	private static int eleccion;

	public static void menuPrincipal() throws SQLException {
		do {
			if(user.getTipo()==(TipoUsuario.Administrador)) {
				MenuAdmin.PanelAdmin();
			}
			eleccion = Escaner.leerNumero("""
					
					+---------------Menú De Inicio---------------+
					|                                            |
					| 1.Iniciar sesión                           |
					|                                            |
					| 2.Registrarse                              |
					|                                            |
					| 0.Salir                                    |
					|                                            |
					+--------------------------------------------+
					
					""");
			switch(eleccion) {
			case 1:
				Utiles.logUsuario(user);
				break;
			case 2:
				Utiles.crearUsuario(user);
				break;
			case 0:	
				ConectorBD.desconectar();
				System.exit(0);
				break;
			}
		}while(eleccion != 0);
		
	}
	
	
}
