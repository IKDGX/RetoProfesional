package menus;

import java.sql.SQLException;
import java.util.ArrayList;

import bases_de_datos.AlquilerRepo;
import bases_de_datos.UsuarioRepo;

public class FormateadorTexto {
	
	private static ArrayList<String> tabla = new ArrayList<>();
	
	//Le aplica un formato a los datos añadidos anteriormente y los printea en un listado con columnas bien separadas.

	public static void formateo(int columnas) {
		int tam = 0;
		for(String l: tabla) {
			if(l.length()>tam) {
				tam = l.length();
			}
		}
		tam += 2;
		String col1 = "%-" + tam +"s"+" ";

		for(int i = 0, j =1; i<tabla.size();i++,j++) {
			System.out.printf(col1,tabla.get(i));
			if(j ==columnas) {
				System.out.println();
				j=0;
			}
		}
		tabla.clear();

	}
	
	//Recibe los datos que se mostrarán y los añade al arraylist.
	
	public static ArrayList<String> tablas(String a) {
		tabla.add(a);
		return tabla;
	}
	
	//Recibe toda la información que se mostrará  al administrador que ha solicitado ver las estadísticas.
	
	public static void Estadisticas() throws SQLException {
		String titulo[] = {"Usuarios","Dinero generado","Alquileres de coches","Alquileres de motos"};
		String titulo2[] = Utiles.titulo(titulo);
		for(int i=0;i<titulo2.length;i++) {
			tablas(titulo2[i]);
		}
		tablas(UsuarioRepo.cantidadUsuarios());
		AlquilerRepo.DineroTotal();
		formateo(4);
	}
}
