package Control.Automatas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;

import Control.Atributos.State;
import Control.Atributos.Transition;
import Control.Atributos.alphabet;

public abstract class Automata {

	alphabet alphabet = new alphabet("");
	State states = new State();

	Transition transition = new Transition(null);

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
					this.transition = new Transition(this.states);
				} else {
					switch (time) {
					case 0:
						this.alphabet.addAlfabeto(linea);
						break;
					case 1:
						this.states.addState(linea);
						break;
					case 2:
						this.states.setStateInitial(linea);
						break;
					case 3:
						this.states.addStatesAceptation(linea);
						break;
					case 4:
						this.transition.addTransition(linea);
						break;
					default:
						System.out.println(time);
						throw new IllegalArgumentException("Unexpected value: " + time);
					}
				}

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

	public Automata(String[] alphabet, String[] states, String state0, String[] acceptationState, String[] transition) {
		// get alphabet and add the list
		this.alphabet.addAlfabeto(alphabet);

		// get states and add the list
		this.states.addState(states);

		// declaration of initial state
		this.states.setStateInitial(state0);

		// get and comprobate the acceptation state in states
		this.states.addStatesAceptation(acceptationState);

		this.transition = new Transition(transition, new State(states));
	}

	public void displayAutomata() {

		System.out.println("#Alphabet");
		this.alphabet.displaySimbolos();
		
		System.out.println("#States");
		this.states.displayEstados();

		System.out.println("#Initial State");
		this.states.displayEstadoInicial();
		
		System.out.println("#Acceptation States");
		this.states.displayEstadosAceptacion();

		System.out.println("#Transition Matriz");
		this.transition.displayTransition();
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

	public abstract boolean procesarCadena(String cadena);

	public abstract boolean procesarCadenaDetallada(String cadena);

	public static void main(String[] args) {

	}

}
