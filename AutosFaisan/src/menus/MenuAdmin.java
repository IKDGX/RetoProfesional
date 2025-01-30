package menus;

import java.sql.SQLException;

import bases_de_datos.ConectorBD;
import bases_de_datos.Escaner;

public class MenuAdmin {
	
	public static int eleccion;
	
	public static void PanelAdmin() throws SQLException {
		do {
			eleccion = Escaner.leerNumero("""
					
					+---------------Panel De Gestión---------------+
					|                                              |
					| 1.Eliminar un usuario                        |
					|                                              |
					| 2.Listado de alquileres activos              |
					|                                              |
					| 0.Salir                                      |
					|                                              |
					+----------------------------------------------+
					
					""");
			switch(eleccion) {
			case 1:
				
				break;
			case 2:
				
				break;
			case 0:	
				ConectorBD.desconectar();
				System.exit(0);
				break;
			}
		}while(eleccion!=0);
	}
}
