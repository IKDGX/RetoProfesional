package menus;

import java.util.ArrayList;

public class TextoForm {

	public static void formateo(ArrayList<String> texto, int columnas) {
		int tam = 0;
		for(String l: texto) {
			if(l.length()>tam) {
				tam = l.length();
			}
		}
		tam += 2;
		String col1 = "%-" + tam +"s"+" ";

		for(int i = 0, j =1; i<texto.size();i++,j++) {
			System.out.printf(col1,texto.get(i));
			if(j ==columnas) {
				System.out.println();
				j=0;
				
			}
		}

	}
}
