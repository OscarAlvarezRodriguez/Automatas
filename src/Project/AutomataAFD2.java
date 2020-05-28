package Project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;

//PROCESAMIENTO DETERMINISTA
public class AutomataAFD2 {

	LinkedList<String> alphabet = new LinkedList<String>();
	LinkedList<String> states = new LinkedList<String>();
	String State0;
	LinkedList<String> acceptationStates = new LinkedList<String>();

	String[][] transition;

	public boolean procesarCadena(String cadena) {
		System.out.println("\n \n \n");
		System.out.println("INICIANDO PROCESAMIENTO");
		return procesarCadena(cadena, null);
	}

	private boolean procesarCadena(String cadena, String state) {
		if (state == null) {
			state = this.State0;
		}
		if (cadena.contentEquals("")) {
			System.out.println("Ha terminado el procesamiento --> Estado actual: Cadena '" + cadena + "'   Estado '"
					+ state + "'");

			if (acceptationStates.contains(state)) {
				System.out.println("El estado " + state + " es un estado de aceptacion");
				return true;
			} else {
				System.out.println("El estado " + state + " No es un estado de aceptacion");
				return false;
			}
		}

		System.out.println();
		System.out.println("Estado actual: Cadena '" + cadena + "'   Estado '" + state + "'");

		String sigma = cadena.substring(0, 1);
		cadena = cadena.substring(1);

		System.out.println("Division de cadena: Letra '" + sigma + "'    Cadena '" + cadena + "'");

		System.out.println();

		for (int i = 0; i < this.transition.length; i++) {
			if (this.transition[this.states.indexOf(state)][i] == null) {
			} else if (this.transition[this.states.indexOf(state)][i].contains(sigma) && this.transition[this.states.indexOf(state)][i].contains(",")) {
				state = states.get(i);
				return procesarCadena(cadena, state);
			} else if (this.transition[this.states.indexOf(state)][i].contentEquals(sigma)) {
				state = states.get(i);
				return procesarCadena(cadena, state);
			}
		}

		System.out.println("El estado " + state + " No tiene transicion con " + sigma + " esta cadena no es aceptada");
		return false;
	}

	public AutomataAFD2(String[] alphabet, String[] states, String state0, String[] acceptationState,
			String[] transition) {
		// get alphabet and add the list
		for (int i = 0; i < alphabet.length; i++) {
			this.alphabet.add(alphabet[i]);
		}

		// get states and add the list
		for (int i = 0; i < states.length; i++) {
			this.states.add(states[i]);
		}

		// declaration of initial state
		if (this.states.contains(state0)) {
			this.State0 = state0;
		} else {
			System.out.println("WEY QUE HACES!! \nel estado declarado como inicial no existe");
			System.exit(0);
		}

		this.State0 = state0;

		// get and comprobate the acceptation state in states
		for (int i = 0; i < acceptationState.length; i++) {
			if (this.states.contains(acceptationState[i])) {
				this.acceptationStates.add(acceptationState[i]);
			} else {
				System.out.println("WEY QUE HACES!! \ndeclaracion erronea de estados de aceptacion");
				System.exit(0);
			}
		}

		this.transition = TransitionMatriz(transition);
	}

	private String[][] TransitionMatriz(String[] transition) {
		// Declaration square matriz for transitions
		String[][] matriz = new String[this.states.size()][this.states.size()];

		/*
		 * The matriz with states s1,s0,s3 in order list would be
		 * 
		 * s1 s0 s2 s1 a b s0 b s2 a
		 *
		 * the transition s1 > s1 would be a the transition s0 > s1 would be b the
		 * transition s2 > s1 is undefined
		 */

		for (int i = 0; i < transition.length; i++) {

			String[] parts = transition[i].split(":");
			String[] parts2 = parts[1].split(">");

//			System.out.println(transition[i]);
//			System.out.println(parts[0] + "   -   " + parts[1]);
//			System.out.println(parts2[0] + "   -   " + parts2[1]);

			if (parts2[1].contains(",")) {
				String[] parts3 = parts2[1].split(",");
				for (int j = 0; j < parts3.length; j++) {
					if (matriz[this.states.indexOf(parts[0])][this.states.indexOf(parts3[j])] == null) {
						matriz[this.states.indexOf(parts[0])][this.states.indexOf(parts3[j])] = parts2[0];
					} else {
						String aux = matriz[this.states.indexOf(parts[0])][this.states.indexOf(parts3[j])];
						matriz[this.states.indexOf(parts[0])][this.states.indexOf(parts3[j])] = aux + "," + parts2[0];
					}
				}
			} else {
				if (matriz[this.states.indexOf(parts[0])][this.states.indexOf(parts2[1])] == null) {
					matriz[this.states.indexOf(parts[0])][this.states.indexOf(parts2[1])] = parts2[0];
				} else {
					String aux = matriz[this.states.indexOf(parts[0])][this.states.indexOf(parts2[1])];
					matriz[this.states.indexOf(parts[0])][this.states.indexOf(parts2[1])] = aux + "," + parts2[0];
				}
			}
//			imprimirMatriz(matriz);
		}

		return matriz;
		// obtiene el index del estado actual y al que va
	}

	private void imprimirMatriz(String[][] matriz) {
		System.out.println("#Transition Matriz");
		System.out.println("\t s0 \t s1 \t s2 \t s3");
		for (int i = 0; i < matriz.length; i++) {
			System.out.print("s" + i + "\t");
			for (int j = 0; j < matriz.length; j++) {
				System.out.print(matriz[i][j] + "\t");
			}
			System.out.println();
		}
	}

	public void displayAutomata() {

		System.out.println("#Alphabet");
		for (int i = 0; i < this.alphabet.size(); i++) {
			System.out.println(this.alphabet.get(i));
		}

		System.out.println("#States");
		for (int i = 0; i < this.states.size(); i++) {
			System.out.println(this.states.get(i));
		}

		System.out.println("#Initial State");
		System.out.println(this.State0);

		System.out.println("#Acceptation States");
		for (int i = 0; i < this.acceptationStates.size(); i++) {
			System.out.println(this.acceptationStates.get(i));
		}

		System.out.println("#Transition Matriz");
		System.out.println("\t s0 \t s1 \t s2 \t s3");
		// TODO FIla 1 declaracion de estados
		for (int i = 0; i < this.transition.length; i++) {
			System.out.print("s" + i + "\t");
			for (int j = 0; j < this.transition.length; j++) {
				System.out.print(transition[i][j] + "\t");
			}
			System.out.println();
		}
	}

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

			///////
			AutomataAFD2 prueba = new AutomataAFD2(alphabet, states, state0, acceptationState, transition);
			prueba.displayAutomata();
//			prueba.procesarCadena("babaaabaa");
//			prueba.procesarCadena("bbbabbb");
//			prueba.procesarCadena("bababa");
//			prueba.procesarCadena("bazb");
			prueba.procesarCadena("aaa");

			//////

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
