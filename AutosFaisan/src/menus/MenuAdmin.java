package menus;

import java.sql.SQLException;

import bases_de_datos.AlquilerRepo;
import bases_de_datos.ConectorBD;
import bases_de_datos.ValidacionEntradaDatos;
import model.Local;

public class MenuAdmin {
	
	public static int eleccion;
	
	public static Local local = new Local();
	
	
	//Menú principal del administrador.
	
	public static void PanelAdmin() throws SQLException {
		do {
			eleccion = ValidacionEntradaDatos.leerNumero("""
					
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
				FormateadorTexto.Estadisticas();
				break;
			case 0:	
				ConectorBD.desconectar();
				System.exit(0);
				break;
			}
		}while(eleccion!=0);
	}
	
	//Menú de registros de alquileres con y sin filtros
	
	public static void AlquileresFiltrados() throws SQLException {
		do {
			eleccion = ValidacionEntradaDatos.leerNumero("""
					
					+------------Panel De Administrador------------+
					|                                              |
					| 1.Filtrar por local                          |
					|                                              |
					| 2.Sin filtros                                |
					|                                              |
					| 0.Salir                                      |
					|                                              |
					+----------------------------------------------+
					
					""");
			switch(eleccion) {
			case 1:
				Utiles.accederLocal(local);
				AlquilerRepo.AlquilerLocal(Utiles.titulo(new String[] {"Código","DNI","Matrícula","fecha","días","cargo"}), local.getId());
				break;

			case 2:
				AlquilerRepo.Alquileres(Utiles.titulo(new String[] {"Código","DNI","Matrícula","fecha","días","cargo"}));
				break;
			case 0:	
				ConectorBD.desconectar();
				System.exit(0);
				break;
			}
		}while(eleccion!=0);
	}
}
