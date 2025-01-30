package menus;

import java.sql.Date;
import java.sql.SQLException;

import bases_de_datos.AlquilerRepo;
import bases_de_datos.CocheRepo;
import bases_de_datos.ConectorBD;
import bases_de_datos.Escaner;
import bases_de_datos.LocalRepo;
import bases_de_datos.MotoRepo;
import model.Usuario;
import model.Vehiculo;

public class MenuUser {
	
	public static float precio;
	
	public static Date fecha;
	
	public static int eleccion;
	
	public static String local;
	
	public static Vehiculo vehiculo = new Vehiculo();
	
	public static int dias = 0;
	
	//Menús

	public static void menuFunciones(Usuario user) throws SQLException {
		do {
			eleccion = Escaner.leerNumero("""
					
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
				Catalogo(user);
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
	
	public static void Catalogo(Usuario user) throws SQLException {
		
		LocalRepo.mostrarLocales(Utiles.titulo(new String[] {"Id","Nombre","Localidad"}));
		
		local = Escaner.leerTexto("""
				
				Introduce el id del local al que quieras acceder:

				""");
		do {
			

			eleccion = Escaner.leerNumero("""
					
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
				CocheRepo.mostrarCoches(Utiles.titulo(new String[] {"Matrícula","Modelo","Color","Precio/dia","Disponibles","Tipo"}), local);
				MenuAlquiler(user);
				break;
			case 2:
				MotoRepo.mostrarMotos(Utiles.titulo(new String[] {"Matrícula","Modelo","Color","Precio/dia","Disponibles","Cilindrada"}) , local);
				MenuAlquiler(user);
				break;
			case 0:
				ConectorBD.desconectar();
				System.exit(0);
				break;
			}
		}
		while(eleccion != 0);
	}
	
	public static void MenuAlquiler(Usuario user) throws SQLException{
		String input = "";
		do {
			input = Escaner.leerTexto("""
					
					Introduzca la matrícula del vehículo elegido:
					
					0.Volver atrás
									
					""");
			if(input.equals("0")) {
				Catalogo(user);
				
			}
			else {
				vehiculo.setMatricula(input);
				switch(eleccion) {
				case 1:
					CocheRepo.CocheElegido(Utiles.titulo(new String[] {"Matrícula","Modelo","Color","Precio/dia","Disponibles","Tipo"}), vehiculo);
					dias = Utiles.diasAlquiler(dias);
					Date fecha1 = Utiles.fechaDisponibilidad(vehiculo, fecha, dias);
					System.out.println("Alquiler disponible desde: "+fecha1);
					do {
						fecha = Escaner.leerFecha("""
								
								Introduzca la fecha en la que desea realizar la reserva \"yyyy-mm-dd\":
								
								""");
					}while(fecha1.after(fecha));
					AlquilerRepo.realizarAlquiler(vehiculo, user, dias, fecha);
					
					break;
				case 2:
					MotoRepo.MotoElegida(Utiles.titulo(new String[] {"Matrícula","Modelo","Color","Precio/dia","Disponibles","Cilindrada"}) , vehiculo);
					Date fecha2 = Utiles.fechaDisponibilidad(vehiculo, fecha, dias);
					System.out.println("Alquiler disponible desde: "+fecha2);
					do {
						fecha = Escaner.leerFecha("""
								
								Introduzca la fecha en la que desea realizar la reserva \"yyyy-mm-dd\":
								
								""");
					}while(fecha2.after(fecha));
					
					
					break;
				}
			}

		}
		while(input != "0");
		
	}
	
	
	//Creación y verificación de los datos de un usuario
	
	
	
}
