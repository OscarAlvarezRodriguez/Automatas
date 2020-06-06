package Control.Atributos;

import java.util.LinkedList;
import java.util.Random;

public class alphabet {

	LinkedList<String> simbolos = new LinkedList<String>();

	public alphabet(String string) {
		if(string.isEmpty()) {
			return;
		}
		addAlfabeto(string);
	}
	
	public void addAlfabeto(String string) {
		if(string.length() == 1) {
			simbolos.add(string);
			return;
		}
		
		String[] limites = string.split("-");
		for (int i = limites[0].codePointAt(0); i <= limites[1].codePointAt(0); i++) {
			char ch = (char) i;
			this.simbolos.add(String.valueOf(ch));
		}
	}
	
	public void addAlfabeto(String[] string) {
		for (int i = 0; i < string.length; i++) {
			addAlfabeto(string[i]);
		}
	}

	public LinkedList<String> getAlfabeto() {
		return simbolos;
	}

	public void setAlfabeto(LinkedList<String> alfabeto) {
		this.simbolos = alfabeto;
	}

	public String generarCadenaAleatoria(int n) {
		if(this.simbolos.isEmpty()) {
			return null;
		}
		
		Random rnd = new Random();
		String cadena = "";

		for (int i = 0; i < n; i++) {
			int simb = rnd.nextInt(simbolos.size());
			cadena += simbolos.get(simb);
		}
		return cadena;
	}

	public void displaySimbolos() {
		if(this.simbolos.isEmpty()) {
			System.out.println("No se ha agregado ningun simbolo como alfabeto");
			return;
		}
		for (int i = 0; i < this.simbolos.size(); i++) {
			System.out.println(this.simbolos.get(i));
		}
	}

	public boolean confirmarComposicionCadena(String simbolo) {
		if(simbolo == null) {
			return false;
		}
		
		for (int i = 0; i < simbolo.length(); i++) {
			if (this.simbolos.contains(String.valueOf(simbolo.charAt(i)))) {

			} else
				return false;

		}
		return true;
	}

	public static void main(String[] args) {

		alphabet alfa = new alphabet("a");

		alfa.displaySimbolos();

		System.out.println(alfa.confirmarComposicionCadena(alfa.generarCadenaAleatoria(10)));
		System.out.println(alfa.confirmarComposicionCadena("[]"));
	}

}
