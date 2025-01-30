package concesionario_main;

import java.sql.SQLException;

import bases_de_datos.ConectorBD;
import bases_de_datos.VehiculoRepo;
import menus.AuthMenu;

public class Main {

	public static void main(String[] args) throws SQLException {
		ConectorBD.conectar();
		VehiculoRepo.updateGeneral();
		AuthMenu.menuPrincipal();
	}

}
