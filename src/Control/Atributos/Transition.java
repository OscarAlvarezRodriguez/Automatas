package Control.Atributos;



public class Transition {
	
	String[][] matriz;
	State states;
	
	public Transition(State states) {
		if(states != null) {
			this.matriz = new String[states.getStates().size()][states.getStates().size()];
			this.states = states;			
		}
	}
	
	public Transition(String[] transitions, State states) {
		this.matriz = new String[states.getStates().size()][states.getStates().size()];
		this.states = states;
		
		if(transitions == null);
		else {
			addTransition(transitions);
		}
		
	}
	
	public void addTransition(String[] transitions) {
		if(transitions != null) {
			for (int i = 0; i < transitions.length; i++) {
				addTransition(transitions[i]);
			}
		}
	}

	public void addTransition(String string) {
		if(string != null) {
			String[] parts = string.split(":");
			String[] parts2 = parts[1].split(">");
			
			if (parts2[1].contains(parts[0]) && parts2[0].contentEquals("$")) {
				System.out.println("WEY AQUI!\t" + parts2[1] + "\t" + parts[0]);
				System.out.println(
						"Existe un error que no se ha podido reparar \n ciclo infinito de un Lambda que vuelve a si mismo");
				System.out.println("Se sigue trabajando en su solucion");
			}

			if (parts2[1].contains(";")) {
				String[] parts3 = parts2[1].split(";");
				for (int j = 0; j < parts3.length; j++) {
					if (matriz[this.states.getStates().indexOf(parts[0])][this.states.getStates().indexOf(parts3[j])] == null) {
						matriz[this.states.getStates().indexOf(parts[0])][this.states.getStates().indexOf(parts3[j])] = parts2[0];
					} else {
						String aux = matriz[this.states.getStates().indexOf(parts[0])][this.states.getStates().indexOf(parts3[j])];
						matriz[this.states.getStates().indexOf(parts[0])][this.states.getStates().indexOf(parts3[j])] = aux + "," + parts2[0];
					}
				}
			} else {
				if (matriz[this.states.getStates().indexOf(parts[0])][this.states.getStates().indexOf(parts2[1])] == null) {
					matriz[this.states.getStates().indexOf(parts[0])][this.states.getStates().indexOf(parts2[1])] = parts2[0];
				} else {
					String aux = matriz[this.states.getStates().indexOf(parts[0])][this.states.getStates().indexOf(parts2[1])];
					matriz[this.states.getStates().indexOf(parts[0])][this.states.getStates().indexOf(parts2[1])] = aux + "," + parts2[0];
				}
			}
		}
	}

	public boolean stateContains(String state, String sigma) {
		for (int i = 0; i < this.matriz.length; i++) {
			if (stateContains(state, sigma, i)) {
				return true;
			}
		}
		return false;
	}

	public boolean stateContains(String state, String sigma, int i) {
		if (this.matriz[this.states.getStates().indexOf(state)][i] == null)
			return false;
		else if (this.matriz[this.states.getStates().indexOf(state)][i].contains(sigma))
			return true;
		return false;
	}
	
	
	
	public String[][] getMatriz() {
		return matriz;
	}

	public void displayTransition() {
		System.out.print("\t");
		for (int i = 0; i < matriz.length ;i++) {
			System.out.print(this.states.getStates().get(i) + "\t");
		}
		System.out.println();
		for (int i = 0; i < this.matriz.length; i++) {
			System.out.print(this.states.getStates().get(i) + "\t");
			for (int j = 0; j < this.matriz.length; j++) {
				System.out.print(matriz[i][j] + "\t");
			}
			System.out.println();
		}
	}
	
	
	public static void main(String[] args) {
		String[] transitions = new String[7];
		
		transitions[0] = "s0:b>s0";
		transitions[1] = "s0:a>s1";
		transitions[2] = "s1:a>s2";
		transitions[3] = "s1:b>s0";
		transitions[4] = "s2:a>s2";
		transitions[5] = "s2:b>s0";
		transitions[5] = "s2:b>s1;s2";
		transitions[6] = "s0:$>s0;s1";
		
		String[] states = new String[3];
		states[0] = "s0";
		states[1] = "s1";
		states[2] = "s2";
		
		State estados = new State(states);
		
		Transition prueba = new Transition(estados);
		prueba.addTransition(transitions);
		prueba.displayTransition();
	}

}
