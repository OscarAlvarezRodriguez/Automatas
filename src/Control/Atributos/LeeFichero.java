package Control.Atributos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;

class LeeFichero {
	public static void main(String[] arg) {

		/*
		 * Codigo base de lectura tomado de
		 * http://chuwiki.chuidiang.org/index.php?title=
		 * Lectura_y_Escritura_de_Ficheros_en_Java solo modificaré un poco el codigo
		 */

		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;

		try {
			// Apertura del fichero y creacion de BufferedReader para poder
			// hacer una lectura comoda (disponer del metodo readLine()).
			archivo = new File("G:\\Prueba.txt");
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			// Lectura del fichero
			String linea;
			int time = -1;

			LinkedList<String> auxAlphabet = new LinkedList<String>();
			LinkedList<String> auxStates = new LinkedList<String>();
			String auxState0 = null;
			LinkedList<String> auxAcceptationStates = new LinkedList<String>();
			LinkedList<String> auxtransition = new LinkedList<String>();

			while ((linea = br.readLine()) != null) {
//				System.out.println(linea + "\t\t" + linea.length());
				if (linea.contentEquals("#alphabet")) {
					time = 0;
				} else if (linea.contentEquals("#states")) {
					time = 1;
				} else if (linea.contentEquals("#initial")) {
					time = 2;
				} else if (linea.contentEquals("#accepting")) {
					time = 3;
				} else if (linea.contentEquals("#transitions")) {
					time = 4;
				} else {
					switch (time) {
					case 0:
						auxAlphabet.add(linea);
						break;
					case 1:
						auxStates.add(linea);
						break;
					case 2:
						auxState0 = linea;
						break;
					case 3:
						auxAcceptationStates.add(linea);
						break;
					case 4:
						auxtransition.add(linea);
						break;
					default:
						System.out.println(time);
						throw new IllegalArgumentException("Unexpected value: " + time);
					}
				}

			}

			// pasar las listas a arrays para despues pasarlas a listas.....

			String[] alphabet = null;
			String[] states = null;
			String state0 = null;
			String[] acceptationState = null;
			String[] transition = null;

			alphabet = new String[auxAlphabet.size()];
			for (int i = 0; i < auxAlphabet.size(); i++) {
				alphabet[i] = auxAlphabet.get(i);
			}

			states = new String[auxStates.size()];
			for (int i = 0; i < auxStates.size(); i++) {
				states[i] = auxStates.get(i);
			}

			state0 = auxState0;

			acceptationState = new String[auxAcceptationStates.size()];
			for (int i = 0; i < auxAcceptationStates.size(); i++) {
				acceptationState[i] = auxAcceptationStates.get(i);
			}

			transition = new String[auxtransition.size()];
			for (int i = 0; i < auxtransition.size(); i++) {
				transition[i] = auxtransition.get(i);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// En el finally cerramos el fichero, para asegurarnos
			// que se cierra tanto si todo va bien como si salta
			// una excepcion.
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}