package menus;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import bases_de_datos.ValidacionEntradaDatos;
import bases_de_datos.LocalRepo;
import bases_de_datos.UsuarioRepo;
import enums.TipoUsuario;
import model.Local;
import model.Usuario;

public class Utiles {

	public static ArrayList<String> matriculas = new ArrayList<>();
	//Este método crea un objeto Usuario usado para registrarlo en la base de datos.

	public static void crearUsuario(Usuario user) throws SQLException {
		//Creo un array con todos los mensajes que le mostraré al usuario para que introduzca sus datos.
		String mensaje[] = {"Introduce el DNI","Introduce el nombre","Introduce el apellido","Introduce la fecha de nacimiento en formato \"yyyy-mm-dd\"","Introduce una clave de acceso"};
		String dni;
		String clave;
		do {
			dni = ValidacionEntradaDatos.leerTexto(mensaje[0]);
		}
		while(!verificaDNI(dni));
		//Le pido al usuario que introduzca su DNI y si no es real se lo vuelvo a pedir.
		user.setDni(dni.toUpperCase());
		user.setNombre(ValidacionEntradaDatos.leerTexto(mensaje[1]));
		user.setApellido(ValidacionEntradaDatos.leerTexto(mensaje[2]));
		user.setFec_nac(ValidacionEntradaDatos.leerFecha(mensaje[3]));
		do {
			clave = ValidacionEntradaDatos.leerTexto(mensaje[4]);
		}while(!reqClave(clave));
		//Le pido que introduzca una clave y si no cumple los requisitos se repite.
		user.setTipo(TipoUsuario.Cliente); //Es un cliente porque los administradores son insertados directamente en la base de datos.
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
			//Esto comprueba el largo de la contraseña.
		}
		for(String e: clave.split("")){
            if("0123456789".contains(e)){
                pasos[1]=true;
                //Compruebo si la contraseña tiene un número.
            }
            else if(e.toUpperCase().equals(e)){
                pasos[2]= true;
                //Compruebo si la contraseña tiene una mayúscula.
            }
        }
		//En este for verifico que la contraseña cumpla todos los requisitos.
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
	
	//Solicita una fecha al cliente en base a un requisito.

	public static Date fechaReserva(String mensaje,Date fechaRequisito, Date fecharesultado) throws SQLException{
		//Le pide al usuario que introduzca una fecha de manera indefinida hasta que la fecha sea posterior a la fecha requisito.
		do {
			fecharesultado = ValidacionEntradaDatos.leerFecha(mensaje);

		}while(fechaRequisito.after(fecharesultado));
		return fecharesultado;
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
	
	//Añade las matrículas que se le han mostrado al cliente en un listado a un arraylist.
	
	public static void matricula(String matricula){
		matriculas.add(matricula);
	}
	
	//Se asegura de que la matrícula introducida por el cliente se encuentra en el listado que se le ha mostrado.
	
	public static void encontrarvehiculo(String matricula) throws SQLException {
		for(String e: matriculas) {
			if(e.equalsIgnoreCase(matricula)) {
				return;
			}
		}
		System.out.println("\nNo se ha encontrado ese vehículo en el listado mostrado anteriormente\n");
		MenuUser.MenuAlquiler();
		
	}
}
