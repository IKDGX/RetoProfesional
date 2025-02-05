package menus;

import java.sql.Date;
import java.sql.SQLException;

import bases_de_datos.AlquilerRepo;
import bases_de_datos.CocheRepo;
import bases_de_datos.ConectorBD;
import bases_de_datos.ValidacionEntradaDatos;
import bases_de_datos.MotoRepo;
import bases_de_datos.VehiculoRepo;
import model.Local;
import model.Usuario;
import model.Vehiculo;

public class MenuUser {
	
	public static Usuario user;
	
	public static Date fecha;
	
	private static int eleccion;
	
	public static Vehiculo vehiculo = new Vehiculo();
	
	public static int dias;
	
	public static Local local = new Local();
	
	//Menú principal del cliente

	public static void menuFunciones(Usuario usuario) throws SQLException {
		user = usuario;
		do {
			eleccion = ValidacionEntradaDatos.leerNumero("""
					
					+---------------Panel De Cliente---------------+
					|                                              |
					| 1.Alquilar un vehículo                       |
					|                                              |
					| 2.Historial de reservas                      |
					|                                              |
					| 0.Salir                                      |
					|                                              |
					+----------------------------------------------+
									
					""");
			switch(eleccion) {
			case 1:
				Catalogo();
				break;
			case 2:
				AlquilerRepo.mostrarAlquileres(Utiles.titulo(new String[] {"Código","DNI","Matrícula","fecha","días","cargo"}), user);
				break;
			case 0:
				ConectorBD.desconectar();
				System.exit(0);
				break;
			}
		}
		while(eleccion!=0);
	}
	
	
	//Le muestra al cliente un listado de los locales a los que puede acceder y le pide que acceda a uno.
	
	public static void Catalogo() throws SQLException {
		
		Utiles.accederLocal(local);
		do {
			

			eleccion = ValidacionEntradaDatos.leerNumero("""
					
					+------------------Vehículos-------------------+
					|                                              |
					| 1.Coche                                      |
					|                                              |
					| 2.Motocicleta                                |
					|                                              |
					| 0.Salir                                      |
					|                                              |
					+----------------------------------------------+
									
					""");
			switch(eleccion) {
			case 1:
				CocheRepo.mostrarCoches(Utiles.titulo(new String[] {"Matrícula","Modelo","Color","Precio/dia","Disponibles","Tipo"}), local.getId());
				MenuAlquiler();
				break;
			case 2:
				MotoRepo.mostrarMotos(Utiles.titulo(new String[] {"Matrícula","Modelo","Color","Precio/dia","Disponibles","Cilindrada"}) , local.getId());
				MenuAlquiler();
				break;
			case 0:
				ConectorBD.desconectar();
				System.exit(0);
				break;
			}
		}
		while(eleccion != 0);
	}
	
	
	//Le pide al usuario que elija un vehículo y verifica que existe para posteriormente mostrarle su elección.
	
	public static void MenuAlquiler() throws SQLException{
		String input = "";
		do {
			input = ValidacionEntradaDatos.leerTexto("""
					
					Introduzca la matrícula del vehículo elegido:
					
					0.Volver atrás
									
					""");
			if(input.equals("0")) {
				menuFunciones(user);
				
			}
			else {
				vehiculo.setMatricula(input);
				VehiculoRepo.encontrarVehiculo(vehiculo);
				switch(eleccion) {
				case 1:
					CocheRepo.CocheElegido(Utiles.titulo(new String[] {"Matrícula","Modelo","Color","Precio/dia","Disponibles","Tipo"}), vehiculo);
					procesoAlquiler(input);
					break;
				case 2:
					MotoRepo.MotoElegida(Utiles.titulo(new String[] {"Matrícula","Modelo","Color","Precio/dia","Disponibles","Cilindrada"}) , vehiculo);
					procesoAlquiler(input);
					break;
				}
			}

		}
		while(input != "0");
		
	}
	
	//Recibe los datos necesarios para realizar el trámite y si se da el caso lo realiza
	
	public static void procesoAlquiler(String input) throws SQLException {
		dias = Utiles.diasAlquiler(dias);
		Date fecha1 = Utiles.fechaDisponibilidad(vehiculo, fecha, dias);
		System.out.println("Alquiler disponible desde: "+fecha1);
		do {
			fecha = ValidacionEntradaDatos.leerFecha("""
					
					Introduzca la fecha en la que desea realizar la reserva \"yyyy-mm-dd\":
					
					""");
		}while(fecha1.after(fecha));
		input = Utiles.finalizar(input);
		if(input.equalsIgnoreCase("finalizar")) {
			AlquilerRepo.realizarAlquiler(vehiculo, user, dias, fecha);
		}
		else if(input.equalsIgnoreCase("0")) {
			menuFunciones(user);
		}
	}
	
}
