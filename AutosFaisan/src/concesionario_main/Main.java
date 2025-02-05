package concesionario_main;

import java.sql.SQLException;

import bases_de_datos.ConectorBD;
import bases_de_datos.VehiculoRepo;
import menus.AuthMenu;

public class Main {
	
	//Se conecta a la base de datos, realiza una actualización general y ejecuta el menú de acceso.

	public static void main(String[] args) throws SQLException {
		ConectorBD.conectar();
		VehiculoRepo.updateGeneral();
		AuthMenu.menuPrincipal();
	}

}
