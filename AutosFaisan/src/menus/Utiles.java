package menus;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Arrays;

import bases_de_datos.AlquilerRepo;
import bases_de_datos.Escaner;
import bases_de_datos.UsuarioRepo;
import enums.TipoUsuario;
import model.Usuario;
import model.Vehiculo;

public class Utiles {


	public static void crearUsuario(Usuario user) throws SQLException {
		String mensaje[] = {"Introduce el DNI","Introduce el nombre","Introduce el apellido","Introduce la fecha de nacimiento en formato \"yyyy-mm-dd\"","Introduce una clave de acceso"};
		String dni;
		String clave;
		do {
			dni = Escaner.leerTexto(mensaje[0]);
		}
		while(!verificaDNI(dni));
		user.setDni(dni.toUpperCase());
		user.setNombre(Escaner.leerTexto(mensaje[1]));
		user.setApellido(Escaner.leerTexto(mensaje[2]));
		user.setFec_nac(Escaner.leerFecha(mensaje[3]));
		do {
			clave = Escaner.leerTexto(mensaje[4]);
		}while(!reqClave(clave));
		user.setTipo(TipoUsuario.Cliente);
		UsuarioRepo.registrarUsuario(user, clave);

	}
	
	public static void logUsuario(Usuario user) throws SQLException{
		String mensaje[] = {"Introduce el DNI","Introduce la clave"};
		String dni = Escaner.leerTexto(mensaje[0]);
		String clave = Escaner.leerTexto(mensaje[1]);
		UsuarioRepo.iniciarSesion(user, dni, clave);
	}
	
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
			if(AlquilerRepo.fechaDispo(dias, vehiculo).after(fecha)) {
				fecha = AlquilerRepo.fechaDispo(dias, vehiculo);
			}
		}
		return fecha;
	}
	
	public static int diasAlquiler(int dias) {
		dias = -80;
		do {
			dias = Escaner.leerNumero("Introduzca la cantidad de dias del alquiler");
		}
		while(dias<=0);
		return dias;
	}
	
	public static String finalizar(String input) {
		do {
			input = Escaner.leerTexto("""
					
					Introduzca "finalizar" para realizar el alquiler:
					
					0.Volver atrás
					
					""");
		}while(!(input.equalsIgnoreCase("finalizar")||input.equals("0")));
		return input;
	}
	
	public static void estadisticas() {
		
	}
}
