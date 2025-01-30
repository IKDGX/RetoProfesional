package menus;

import java.sql.SQLException;

import bases_de_datos.ConectorBD;
import bases_de_datos.Escaner;

public class MenuAdmin {
	
	public static int eleccion;
	
	public static void PanelAdmin() throws SQLException {
		do {
			eleccion = Escaner.leerNumero("""
					
					+------------Panel De Administrador------------+
					|                                              |
					| 1.Alquileres                                 |
					|                                              |
					| 2.Estadísticas                               |
					|                                              |
					| 0.Salir                                      |
					|                                              |
					+----------------------------------------------+
					
					""");
			switch(eleccion) {
			case 1:
				AlquileresFiltrados();
				break;
			case 2:
				Utiles.estadisticas();
				break;
			case 0:	
				ConectorBD.desconectar();
				System.exit(0);
				break;
			}
		}while(eleccion!=0);
	}
	
	public static void AlquileresFiltrados() throws SQLException {
		do {
			eleccion = Escaner.leerNumero("""
					
					+------------Panel De Administrador------------+
					|                                              |
					| 1.Filtrar por local                          |
					|                                              |
					| 2.Filtrar por vehículo                       |
					|                                              |
					| 3.Sin filtros                                |
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
