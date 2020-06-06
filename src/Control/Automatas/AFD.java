package Control.Automatas;

//PROCESAMIENTO DETERMINISTA
public class AFD extends Automata{

	public AFD(String direccion) {
		super(direccion);
	}
	
	public AFD(String[] alphabet, String[] states, String state0, String[] acceptationState, String[] transition) {
		super(alphabet, states, state0, acceptationState, transition);
	}

	@Override
	public boolean procesarCadena(String cadena) {
		return procesarCadena(cadena, null);
	}

	private boolean procesarCadena(String cadena, String state) {
		if (state == null) {
			state = this.State0;
		}
		if (cadena.contentEquals("")) {
			if (acceptationStates.contains(state)) {
				return true;
			} else {
				return false;
			}
		}

		String sigma = cadena.substring(0, 1);
		cadena = cadena.substring(1);

		for (int i = 0; i < this.transition.length; i++) {
			if (this.transition[this.states.indexOf(state)][i] == null) {
			} else if (this.transition[this.states.indexOf(state)][i].contains(sigma)
					&& this.transition[this.states.indexOf(state)][i].contains(",")) {
				state = states.get(i);
				return procesarCadena(cadena, state);
			} else if (this.transition[this.states.indexOf(state)][i].contentEquals(sigma)) {
				state = states.get(i);
				return procesarCadena(cadena, state);
			}
		}

		return false;
	}
	
	@Override
	public boolean procesarCadenaDetallada(String cadena) {
		System.out.println("\n \n \n");
		System.out.println("INICIANDO PROCESAMIENTO");
		return procesarCadenaDetallada(cadena, null);
	}

	private boolean procesarCadenaDetallada(String cadena, String state) {
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

		System.out.println("Estado actual: \t\tEstado '" + state + "'\tCadena '" + cadena + "'");

		String sigma = cadena.substring(0, 1);
		cadena = cadena.substring(1);

		System.out.println("Division de cadena: \tSigma '" + sigma + "'    \tCadena restante '" + cadena + "'");

		System.out.println();

		for (int i = 0; i < this.transition.length; i++) {
			if (this.transition[this.states.indexOf(state)][i] == null) {
			} else if (this.transition[this.states.indexOf(state)][i].contains(sigma)
					&& this.transition[this.states.indexOf(state)][i].contains(",")) {
				state = states.get(i);
				return procesarCadenaDetallada(cadena, state);
			} else if (this.transition[this.states.indexOf(state)][i].contentEquals(sigma)) {
				state = states.get(i);
				return procesarCadenaDetallada(cadena, state);
			}
		}

		System.out.println("El estado " + state + " No tiene transicion con " + sigma + " esta cadena no es aceptada");
		return false;
	}

	public static void main(String[] arg) {
		AFD prueba = new AFD("G:\\Prueba.txt");
		prueba.displayAutomata();
		System.out.println(prueba.procesarCadena("aaaa"));
	}
}
