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
	
	public static float precio;
	
	public static Date fecha;
	
	private static int eleccion;
	
	public static Vehiculo vehiculo = new Vehiculo();
	
	public static int dias = 0;
	
	public static Local local = new Local();
	
	//Menús

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
	
	public static void MenuAlquiler() throws SQLException{
		String input = "";
		do {
			input = ValidacionEntradaDatos.leerTexto("""
					
					Introduzca la matrícula del vehículo elegido:
					
					0.Volver atrás
									
					""");
			if(input.equals("0")) {
				Catalogo();
				
			}
			else {
				vehiculo.setMatricula(input);
				VehiculoRepo.encontrarVehiculo(vehiculo);
				switch(eleccion) {
				case 1:
					CocheRepo.CocheElegido(Utiles.titulo(new String[] {"Matrícula","Modelo","Color","Precio/dia","Disponibles","Tipo"}), vehiculo);
					dias = Utiles.diasAlquiler(dias);
					Date fecha1 = Utiles.fechaDisponibilidad(vehiculo, fecha, dias);
					System.out.println("Alquiler disponible desde: "+fecha1);
					do {
						fecha = ValidacionEntradaDatos.leerFecha("""
								
								Introduzca la fecha en la que desea realizar la reserva \"yyyy-mm-dd\":
								
								""");
					}while(fecha1.after(fecha));
					if(Utiles.finalizar(input).equalsIgnoreCase("finalizar")) {
						AlquilerRepo.realizarAlquiler(vehiculo, user, dias, fecha);
					}
					break;
				case 2:
					MotoRepo.MotoElegida(Utiles.titulo(new String[] {"Matrícula","Modelo","Color","Precio/dia","Disponibles","Cilindrada"}) , vehiculo);
					dias = Utiles.diasAlquiler(dias);
					Date fecha2 = Utiles.fechaDisponibilidad(vehiculo, fecha, dias);
					System.out.println("Alquiler disponible desde: "+fecha2);
					do {
						fecha = ValidacionEntradaDatos.leerFecha("""
								
								Introduzca la fecha en la que desea realizar la reserva \"yyyy-mm-dd\":
								
								""");
					}while(fecha2.after(fecha));
					if(Utiles.finalizar(input).equalsIgnoreCase("finalizar")) {
						AlquilerRepo.realizarAlquiler(vehiculo, user, dias, fecha);
					}
					
					break;
				}
			}

		}
		while(input != "0");
		
	}
	
}
