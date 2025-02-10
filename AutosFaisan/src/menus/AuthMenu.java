package menus;

import java.sql.SQLException;

import bases_de_datos.ConectorBD;
import bases_de_datos.ValidacionEntradaDatos;
import enums.TipoUsuario;
import model.Usuario;

public class AuthMenu {
	
	public static Usuario user = new Usuario();
	
	private static int eleccion;
	
	//El primer menú del programa, en el que el usuario debe ingresar sus datos.

	public static void menuPrincipal() throws SQLException {
		do {
			/*Si cualquiera de estas dos condiciones se cumplen significa que el usuario se ha registrado o ha iniciado sesión
			 y lo redirije a su correspondiente menú.*/
			if(user.getTipo()==(TipoUsuario.Administrador)) {
				MenuAdmin.PanelAdmin();
			}
			if(user.getTipo()==(TipoUsuario.Cliente)) {
				MenuUser.menuFunciones(user);
			}
			eleccion = ValidacionEntradaDatos.leerNumero("""
					
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
