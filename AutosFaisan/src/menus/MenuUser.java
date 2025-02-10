package menus;

import java.sql.Date;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

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
	
	public static Date fechainicio;
	
	public static Date fechafin;
	
	private static int eleccion;
	
	public static Vehiculo vehiculo = new Vehiculo();
	
	public static Local local = new Local();
	
	//Menú principal del cliente

	public static void menuFunciones(Usuario usuario) throws SQLException {
		
		//Este clear es de un listado de matrículas que se genera cuando el usuario accede al catálogo de un local con intenciones de alquilar un vehículo.
		
		Utiles.matriculas.clear();
		user = usuario; //Declaro el objeto de usuario que recibo del menú de login.
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
				//Llamo a otro menú
				Catalogo();
				break;
			case 2:
				//Utiles.titulo es una función que le añade un subrayado al array que le envies.
				AlquilerRepo.mostrarAlquileres(Utiles.titulo(new String[] {"Código","DNI","Matrícula","fecha","días","cargo"}), user);
				break;
			case 0:
				ConectorBD.desconectar();
				System.exit(0);
				break;
			default:
				System.out.println("\nIntroduce una opción válida\n");
				break;
			}
		}
		while(eleccion!=0);
	}
	
	
	/*Le muestra al cliente un listado de los locales a los que puede acceder y le pide que acceda a uno además de preguntarle
	en qué fechas desea reservar un vehículo.*/
	
	public static void Catalogo() throws SQLException {
		
		//Intento acceder al local especificado por el usuario.

		Utiles.accederLocal(local);
		
		if(fechainicio == null && fechafin == null) {
			/*Le pido al usuario las fechas en las que quiere reservar un vehículo.
			  Estas fechas se mantendran hasta que el usuario realice una reserva o reinicie el programa.*/
			
			fechainicio = Utiles.fechaReserva("\nIntroduzca la fecha de inicio de la reserva:\n", new Date(System.currentTimeMillis()), fechainicio);
			fechafin = Utiles.fechaReserva("\nIntroduzca la fecha final de la reserva:\n", fechainicio, fechafin);
		}
		
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
				//Muestro los coches disponibles en las fechas que ha introducido el usuario.
				CocheRepo.mostrarCoches(Utiles.titulo(new String[] {"Matrícula","Modelo","Color","Precio/dia","Tipo"}), local.getId(), fechainicio, fechafin);
				MenuAlquiler();
				break;
			case 2:
				//Hago lo mismo pero con las motos.
				MotoRepo.mostrarMotos(Utiles.titulo(new String[] {"Matrícula","Modelo","Color","Precio/dia","Cilindrada"}), local.getId(), fechainicio, fechafin);
				MenuAlquiler();
				break;
			case 0:
				ConectorBD.desconectar();
				System.exit(0);
				break;
			default:
				System.out.println("\nIntroduce una opción válida\n");
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
				VehiculoRepo.encontrarVehiculo(vehiculo); //Compruebo que el vehículo exista en la base de datos.
				Utiles.encontrarvehiculo(vehiculo.getMatricula()); //Compruebo que el vehículo este en el listado que le he mostrado al usuario.
				switch(eleccion) {
				case 1:
					//Muestro el coche elegido.
					CocheRepo.CocheElegido(Utiles.titulo(new String[] {"Matrícula","Modelo","Color","Precio/dia","Tipo"}), vehiculo);
					procesoAlquiler(input);
					break;
				case 2:
					//Muestro la moto elegida.
					MotoRepo.MotoElegida(Utiles.titulo(new String[] {"Matrícula","Modelo","Color","Precio/dia","Cilindrada"}) , vehiculo);
					procesoAlquiler(input);
					break;
				default:
					System.out.println("\nIntroduce una opción válida\n");
					break;
				}
			}

		}
		while(input != "0");
		
	}
	
	//Recibe los datos necesarios para realizar el trámite y si se da el caso lo realiza.
	
	public static void procesoAlquiler(String input) throws SQLException {
		
		input = Utiles.finalizar(input);
		if(input.equalsIgnoreCase("finalizar")) {
			long fecinicio = fechainicio.getTime();
			long fecfin = fechafin.getTime();

			long timeDiff = Math.abs(fecinicio - fecfin); //Calculo la diferencia de días entre la fecha de inicio y fin del alquiler.

			int dias =(int) TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);//Convierto la diferencia calculada en milésimas a días.
			
			AlquilerRepo.realizarAlquiler(vehiculo, user, dias+1, fechainicio);
			/*Se le suma 1 a días para tener en cuenta el primer día del alquiler
			, si no, el primer día siempre sería gratis.*/
		}
		else if(input.equalsIgnoreCase("0")) {
			menuFunciones(user);
		}
		
	}
	
}
