package Project;

import java.util.LinkedList;

import javax.swing.text.html.ListView;


public class Automata {

	LinkedList<String> alphabet = new LinkedList<String>();
	LinkedList<String> states = new LinkedList<String>();
	String State0;
	LinkedList<String> acceptationStates = new LinkedList<String>();

	public Automata(String[] alphabet, String[] states, String state0, String[] acceptationState) {
		// get alphabet and add the list
		for (int i = 0; i < alphabet.length; i++) {
			this.alphabet.add(alphabet[i]);
		}
		
		// get states and add the list
		for (int i = 0; i < states.length; i++) {
			this.states.add(states[i]);
		}
		
		// declaration of initial state
		if(this.states.contains(state0)) {
			this.State0 = state0;
		}else {
			System.out.println("WEY QUE HACES!! \nel estado declarado como inicial no existe" );
			System.exit(0);
		}
		
		this.State0 = state0;
		
		//	get and comprobate the acceptation state in states
		for (int i = 0; i < acceptationState.length; i++) {
			if(this.states.contains(acceptationState[i])) {
				this.acceptationStates.add(acceptationState[i]);
			}else {
				System.out.println("WEY QUE HACES!! \ndeclaracion erronea de estados de aceptacion" );
				System.exit(0);
			}
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
		
		String[] acceptationState = new String[3];
		acceptationState[0] = "q1";
		acceptationState[1] = "q2";
		
		
		Automata pruebas = new Automata(alphabet,states,state0,acceptationState);
		pruebas.displayAutomata();
	}
}
