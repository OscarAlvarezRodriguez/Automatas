package Project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;

//PROCESAMIENTO NO DETERMINISTA
public class AFNDL extends Automata{

	public AFNDL(String direccion) {
		super(direccion);
	}
	
	public AFNDL(String[] alphabet, String[] states, String state0, String[] acceptationState, String[] transition) {
		super(alphabet, states, state0, acceptationState, transition);
	}
	
	@Override
	public boolean procesarCadena(String cadena) {
		this.bools = new LinkedList<Boolean>();
		procesarCadena(cadena, null);
		return procesarCadenaBool();
	}

	private boolean procesarCadena(String cadena, String state) {

		if (state == null) {
			state = this.State0;
		}

		if (cadena.contentEquals("")) {
			if (acceptationStates.contains(state)) {
				addBools(true);
				return true;
			} else {
				addBools(false);
				return false;
			}
		}

		String sigma = cadena.substring(0, 1);
		cadena = cadena.substring(1);

		for (int i = 0; i < this.transition.length; i++) {
//			System.out.println(stateContains(state, sigma, i));
			if (stateContains(state, sigma, i)) {
				procesarCadena(cadena, states.get(i));
			}
		}

		if (stateContains(state, sigma)) {
			return true;
		} else {
			addBools(false);
			return false;
		}

	}
	
	@Override
	public boolean procesarCadenaDetallada(String cadena) {
		System.out.println("\n \n \n");
		System.out.println("INICIANDO PROCESAMIENTO");
		this.bools = new LinkedList<Boolean>();
		procesarCadenaDetallada(cadena, null, null);
		return procesarCadenaBool();
	}

	private boolean procesarCadenaDetallada(String cadena, String state, String estadosPasados) {

		String estadoAux = estadosPasados;
		estadosPasados = estadosPasados + ("[" + state + "," + cadena + "]->");

		if (state == null) {
			state = this.State0;
			estadosPasados = ("[" + state + "," + cadena + "]->");
		}

		if (cadena.contentEquals("")) {
			if (acceptationStates.contains(state)) {
				addBools(true);
				System.out.println(estadosPasados + "Aceptacion");
				return true;
			} else {
				addBools(false);
				System.out.println(estadosPasados + "No aceptacion");
				return false;
			}
		}

		String sigma = cadena.substring(0, 1);
		cadena = cadena.substring(1);

		for (int i = 0; i < this.transition.length; i++) {
//			System.out.println(stateContains(state, sigma, i));
			if (stateContains(state, "$", i)) {
				if (estadoAux == null)
					procesarCadenaDetallada((sigma + cadena), states.get(i), estadosPasados);
				else {
					String aux = ("[" + state + "," + (sigma + cadena) + "]->");
					if ((estadoAux.contains((aux)))) {
						addBools(false);
						System.out.println(estadosPasados + "Ciclo");
						return false;
					} else {
						procesarCadenaDetallada((sigma + cadena), states.get(i), estadosPasados);
					}
				}
			}

			if (stateContains(state, sigma, i)) {
				procesarCadenaDetallada(cadena, states.get(i), estadosPasados);
			}
		}

		if (stateContains(state, sigma)) {
			return true;
		} else {
			addBools(false);
			System.out.println(estadosPasados + "Abortado");
			return false;
		}
	}


	public static void main(String[] arg) {
		AFNDL prueba = new AFNDL("G:\\Prueba.txt");
		prueba.displayAutomata();

		prueba.procesarCadenaDetallada("ab");
		for (int i = 0; i < prueba.bools.size(); i++) {
			System.out.println(prueba.bools.get(i));
		}

		prueba.procesarCadenaDetallada("aaaa");
		for (int i = 0; i < prueba.bools.size(); i++) {
			System.out.println(prueba.bools.get(i));
		}

		prueba.procesarCadenaDetallada("abbab");
		for (int i = 0; i < prueba.bools.size(); i++) {
			System.out.println(prueba.bools.get(i));
		}
	}
}
