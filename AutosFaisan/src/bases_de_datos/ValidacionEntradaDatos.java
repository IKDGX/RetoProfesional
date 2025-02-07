package bases_de_datos;

import java.sql.Date;
import java.util.Scanner;
public class ValidacionEntradaDatos {

	private static Scanner sc = new Scanner(System.in);
	
	//Método de lectura de datos de tipo String.
	
	public static String leerTexto(String mensaje) {
		String texto;
		do {
			System.out.println(mensaje);
			texto = sc.nextLine();
			if(texto.isBlank() || texto.equals("\n")) {
				texto = sc.nextLine();
			}
		}while(texto.isBlank() || texto.equals("\n"));

		return texto;
	}
	
	//Método de lectura de datos de tipo int.
	
	public static int leerNumero(String mensaje) {
		int numero = -1;
		do {
			System.out.println(mensaje);
			try{
				numero = sc.nextInt();
				
			}catch(Exception e) {
				System.out.println("Asegurate de que el número introducido solo contenga caracteres numéricos.\nY no exceda el límite de dígitos.\n");
				sc.next();
			}
			
		}while(numero <0);

		return numero;
	}
	
	//Método de lectura de datos de tipo float.
	
	public static float leerDecimal(String mensaje) {
		float decimal = -1;
		do {
			System.out.println(mensaje);
			try{
				decimal = sc.nextFloat();
				
			}catch(Exception e) {
				System.out.println("Asegurate de que el número introducido no es negativo y \nde que solo contenga caracteres numéricos.\n");
				sc.next();
			}
			
			
		}while(decimal <0);
		return decimal;
	}
	
	//Método de lectura de datos de tipo Date.
	
	public static Date leerFecha(String mensaje) {
		Date fecha = Date.valueOf("2000-12-12");
		Boolean funciona = false;
		String fechavar;
		do {
			System.out.println(mensaje);
			try {
				fechavar = sc.next();
				fecha = Date.valueOf(fechavar);
				if(fecha.toString().equals(fechavar)) {
					funciona = true;
				}else {
					funciona = false;
				}
				
			}catch(Exception e) {
				System.out.println("Asegurate de que el formato de la fecha introducida es correcto");
				funciona = false;
			}

		}while(!funciona );
		return fecha;
	}
}
