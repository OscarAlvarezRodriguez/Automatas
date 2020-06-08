package Control.Atributos;

import java.util.LinkedList;

public class State {

	LinkedList<String> states;
	LinkedList<String> statesAceptation;
	String stateInitial;

	public State() {
		this.stateInitial = null;
		this.states = new LinkedList<String>();
		this.statesAceptation = new LinkedList<String>();
	}

	public State(String[] states) {
		this.stateInitial = null;
		this.states = new LinkedList<String>();
		this.statesAceptation = new LinkedList<String>();

		if (states == null)
			return;

		for (int i = 0; i < states.length; i++) {
			addState(states[i]);
		}
	}

	public void addState(String[] states) {
		if(!(states == null)) {
			for (int i = 0; i < states.length; i++) {
				addState(states[i]);
			}			
		}
	}

	public void addState(String state) {
		if(!(state == null)) {
			this.states.add(state);			
		}
	}

	public boolean confirmarEstadoExistente(String state) {
		if (this.states.contains(state)) {
			return true;
		}
		return false;
	}

	public boolean confirmarEstadoAceptacion(String state) {
		if (this.statesAceptation.contains(state)) {
			return true;
		}
		return false;
	}
	
	public LinkedList<String> getStates() {
		return states;
	}

	public void setStates(LinkedList<String> states) {
		this.states = states;
	}

	public String getStateInitial() {
		return stateInitial;
	}

	public void setStateInitial(String stateInitial) {
		if (confirmarEstadoExistente(stateInitial)) {
			this.stateInitial = stateInitial;
			return;
		}
		System.out.println("El estado que especifico no existe vuelva a declararlo");
	}

	public LinkedList<String> getStatesAceptation() {
		return statesAceptation;
	}

	public void addStatesAceptation(String statesAceptation) {
		if (this.confirmarEstadoExistente(statesAceptation)) {
			this.statesAceptation.add(statesAceptation);
		} else {
			System.out.println("El estado que ha pedido cambiar no sirve!");
			return;
		}
	}
	public void addStatesAceptation(String[] statesAceptation) {
		for (int i = 0; i < statesAceptation.length; i++) {
			this.addStatesAceptation(statesAceptation[i]);
		}
	}

	public void displayEstados() {
		if (this.states.isEmpty()) {
			System.out.println("No se ha agregado ningun estado");
			return;
		}
		for (int i = 0; i < this.states.size(); i++) {
			System.out.println(this.states.get(i));
		}
	}

	public void displayEstadoInicial() {
		System.out.println(this.stateInitial);
	}

	public void displayEstadosAceptacion() {
		if (this.statesAceptation.isEmpty()) {
			System.out.println("No se ha agregado ningun estado");
			return;
		}
		for (int i = 0; i < this.statesAceptation.size(); i++) {
			System.out.println(this.statesAceptation.get(i));
		}
	}

	public static void main(String[] args) {
		String[] string = new String[6];

		string[0] = "s0";
		string[1] = "s1";
		string[2] = "s2";
		string[3] = "s3";
		string[4] = "s4";
		string[5] = "s3";

		State states = new State();
		states.addState(string);
		states.setStateInitial("s1");

		states.displayEstados();
		System.out.println("\n");
		states.displayEstadoInicial();

	}

}
