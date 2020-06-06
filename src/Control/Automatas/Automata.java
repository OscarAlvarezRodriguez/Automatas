package Control.Automatas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;

import Control.Atributos.alphabet;

public abstract class Automata {

	alphabet alphabet = new alphabet("");
	LinkedList<String> states = new LinkedList<String>();
	String State0;
	LinkedList<String> acceptationStates = new LinkedList<String>();

	String[][] transition;

	LinkedList<Boolean> bools = new LinkedList<Boolean>();

	public Automata(String direccion) {
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
			archivo = new File(direccion);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			// Lectura del fichero
			String linea;
			int time = -1;

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
						this.alphabet.addAlfabeto(linea);
						break;
					case 1:
						this.states.add(linea);
						break;
					case 2:
						this.State0 = linea;
						break;
					case 3:
						this.acceptationStates.add(linea);
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
			String[] transition = new String[auxtransition.size()];
			for (int i = 0; i < auxtransition.size(); i++) {
				transition[i] = auxtransition.get(i);
			}
			this.transition = TransitionMatriz(transition);

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

	public Automata(String[] alphabet, String[] states, String state0, String[] acceptationState, String[] transition) {
		// get alphabet and add the list
		for (int i = 0; i < alphabet.length; i++) {
			this.alphabet.addAlfabeto(alphabet[i]);
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

			if (parts2[1].contains(parts[0]) && parts2[0].contentEquals("$")) {
				System.out.println("WEY AQUI!\t" + parts2[1] + "\t" + parts[0]);
				System.out.println(
						"Existe un error que no se ha podido reparar \n ciclo infinito de un Lambda que vuelve a si mismo");
				System.out.println("Se sigue trabajando en su solucion");
				System.exit(0);
			}

			if (parts2[1].contains(";")) {
				String[] parts3 = parts2[1].split(";");
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

	public void displayAutomata() {

		System.out.println("#Alphabet");
		this.alphabet.displaySimbolos();
		
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
		System.out.print("\t");
		for (int i = 0; i < states.size(); i++) {
			System.out.print("s" + i + "\t");
		}
		System.out.println();
		for (int i = 0; i < this.transition.length; i++) {
			System.out.print("s" + i + "\t");
			for (int j = 0; j < this.transition.length; j++) {
				System.out.print(transition[i][j] + "\t");
			}
			System.out.println();
		}
	}

	protected void addBools(boolean b) {
		this.bools.add(b);
	}

	protected boolean procesarCadenaBool() {

		for (int i = 0; i < this.bools.size(); i++) {
			if (bools.get(i) == true) {
				return true;
			}
		}

		return false;

	}

	protected boolean stateContains(String state, String sigma) {
		for (int i = 0; i < transition.length; i++) {
			if (stateContains(state, sigma, i)) {
				return true;
			}
		}
		return false;
	}

	protected boolean stateContains(String state, String sigma, int i) {
		if (this.transition[this.states.indexOf(state)][i] == null)
			return false;
		else if (this.transition[this.states.indexOf(state)][i].contains(sigma))
			return true;
		return false;
	}

	public abstract boolean procesarCadena(String cadena);

	public abstract boolean procesarCadenaDetallada(String cadena);

	public static void main(String[] args) {

	}

}
