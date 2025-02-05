package menus;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Arrays;

import bases_de_datos.AlquilerRepo;
import bases_de_datos.ValidacionEntradaDatos;
import bases_de_datos.LocalRepo;
import bases_de_datos.UsuarioRepo;
import enums.TipoUsuario;
import model.Local;
import model.Usuario;
import model.Vehiculo;

public class Utiles {

	//Este método crea un objeto Usuario usado para registrarlo en la base de datos.

	public static void crearUsuario(Usuario user) throws SQLException {
		String mensaje[] = {"Introduce el DNI","Introduce el nombre","Introduce el apellido","Introduce la fecha de nacimiento en formato \"yyyy-mm-dd\"","Introduce una clave de acceso"};
		String dni;
		String clave;
		do {
			dni = ValidacionEntradaDatos.leerTexto(mensaje[0]);
		}
		while(!verificaDNI(dni));
		user.setDni(dni.toUpperCase());
		user.setNombre(ValidacionEntradaDatos.leerTexto(mensaje[1]));
		user.setApellido(ValidacionEntradaDatos.leerTexto(mensaje[2]));
		user.setFec_nac(ValidacionEntradaDatos.leerFecha(mensaje[3]));
		do {
			clave = ValidacionEntradaDatos.leerTexto(mensaje[4]);
		}while(!reqClave(clave));
		user.setTipo(TipoUsuario.Cliente);
		UsuarioRepo.registrarUsuario(user, clave);

	}
	
	//Como su nombre lo indica, este método es para que el usuario inicie sesión.
	
	public static void logUsuario(Usuario user) throws SQLException{
		String mensaje[] = {"Introduce el DNI","Introduce la clave"};
		String dni = ValidacionEntradaDatos.leerTexto(mensaje[0]);
		String clave = ValidacionEntradaDatos.leerTexto(mensaje[1]);
		UsuarioRepo.iniciarSesion(user, dni, clave);
	}
	
	//Este método calcula la letra que le corresponde al DNI introducido por el usuario en el proceso de registro.
	
	public static boolean verificaDNI(String dni) {
		String lista[] = {"T","R","W","A","G","M","Y","F","P","D","X","B","N","J","Z","S","Q","V","H","L","C","K","E"};
		boolean resultado = false;
		try {
			String letra = dni.substring(8);
			int numero = Integer.valueOf(dni.substring(0, 8));
			float numero2 = numero/23;
			numero2 = numero2 - numero2%1;
			numero =numero- (int)numero2*23;
			if(letra.equalsIgnoreCase(lista[numero])&&dni.length()==9) {
				resultado = true;
			}
			else{
					System.out.println("\nEl DNI no es válido\n");
				}
		}catch(Exception e) {
			System.out.println("\nEl DNI no es válido\n");
		}
			
		return resultado;
	}
	
	//Este método se asegura de que la contraseña del usuario cumpla ciertos requisitos mínimos.
	
	public static boolean reqClave(String clave) {
		boolean pasos[] = {false,false,false};
		boolean resultado = false;
		if(clave.length()>=8) {
			pasos[0] = true;
		}
		for(String e: clave.split("")){
            if("0123456789".contains(e)){
                pasos[1]=true;
            }
            else if(e.toUpperCase().equals(e)){
                pasos[2]= true;
            }
        }
		for(boolean a: pasos) {
			if(a) {
				resultado = true;
			}
			else {
				resultado = false;
				break;
			}
		}
		if(!resultado) {
			System.out.println("\nLa clave debe contener al menos 8 caracteres, 1 mayúscula y 1 número\n");
		}

		return resultado;
	}
	
	//Este método añade una línea divisora para el encabezado de una lista que se vaya a mostrar.
	
	public static String[] titulo(String[] encabezado) {
		String[] lista = Arrays.copyOf(encabezado, encabezado.length*2);
		for (int i = 0; i<encabezado.length;i++) {
			lista[i+encabezado.length]= "-".repeat(lista[i].length());
		}
		return lista;
	}
	
	public static Date fechaDisponibilidad(Vehiculo vehiculo, Date fecha, int dias) throws SQLException {
		fecha = new Date(System.currentTimeMillis());
		if(!vehiculo.isDisponibilidad()) {
			if(AlquilerRepo.fechaDispo(vehiculo).after(fecha)) {
				fecha = AlquilerRepo.fechaDispo(vehiculo);
			}
		}
		return fecha;
	}
	
	//Este método se ocupa de pedirle al cliente la cantidad de días que quiere alquilar un vehículo.
	
	public static int diasAlquiler(int dias) {
		dias = -80;
		do {
			dias = ValidacionEntradaDatos.leerNumero("Introduzca la cantidad de dias del alquiler");
		}
		while(dias<=0);
		return dias;
	}
	
	//Este método lee lo que el usuario desea hacer como último paso de un alquiler y lo devuelve.
	
	public static String finalizar(String input) {
		do {
			input = ValidacionEntradaDatos.leerTexto("""
					
					Introduzca "finalizar" para realizar el alquiler:
					
					0.Volver atrás
					
					""");
		}while(!(input.equalsIgnoreCase("finalizar")||input.equals("0")));
		return input;
	}
	
	//Este método se encarga del acceso a un local.
	
	public static void accederLocal(Local local)throws SQLException {
		do {
			LocalRepo.mostrarLocales(Utiles.titulo(new String[] {"Id","Nombre","Localidad"}));
			local.setId(ValidacionEntradaDatos.leerTexto("""
					
					Introduce el id del local al que quieras acceder:

					"""));
		}while(!LocalRepo.encontrarLocal(local));
	}
}
