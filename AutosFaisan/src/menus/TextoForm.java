package menus;

import java.util.ArrayList;

public class TextoForm {
	
	private static ArrayList<String> tabla = new ArrayList<>();

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
	
	public static ArrayList<String> tablas(String a) {
		tabla.add(a);
		return tabla;
	}
}
