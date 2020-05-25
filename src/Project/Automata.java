package Project;

import java.util.LinkedList;

public class Automata {

	LinkedList<String> alphabet = new LinkedList<String>();
	LinkedList<String> states = new LinkedList<String>();
	String State0;
	LinkedList<String> acceptationStates = new LinkedList<String>();

	String[][] transition;

	public Automata(String[] alphabet, String[] states, String state0, String[] acceptationState, String[] transition) {
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
//			System.out.println(parts[0] + "   -   " + parts[1]);

			String[] parts2 = parts[1].split(">");
//			System.out.println(parts2[0] + "   -   " + parts2[1]);

			if (parts2[1].contains(",")) {
				String[] parts3 = parts2[1].split(",");
				for (int j = 0; j < parts3.length; j++) {
					matriz[this.states.indexOf(parts[0])][this.states.indexOf(parts3[j])] = parts2[0];
				}
			} else {
				matriz[this.states.indexOf(parts[0])][this.states.indexOf(parts2[1])] = parts2[0];
			}
		}

		return matriz;
		// obtiene el index del estado actual y al que va
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
		for (int i = 0; i < this.transition.length; i++) {
			for (int j = 0; j < this.transition.length; j++) {
				System.out.print(transition[i][j] + "\t");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		String[] alphabet = new String[3];
		alphabet[0] = "a";
		alphabet[1] = "b";
		alphabet[2] = "c";

		String[] states = new String[3];
		states[0] = "q0";
		states[1] = "q1";
		states[2] = "q2";

		String state0 = "q0";

		String[] acceptationState = new String[2];
		acceptationState[0] = "q1";
		acceptationState[1] = "q2";

		String[] transition = new String[2];
		transition[0] = "q0:b>q1";
		transition[1] = "q1:a>q2,q1";
//		transition[1] = "q1:a>q0";

		Automata pruebas = new Automata(alphabet, states, state0, acceptationState, transition);
		pruebas.displayAutomata();
		System.out.println();
		pruebas.TransitionMatriz(transition);

	}
}
