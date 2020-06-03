package Project;

public class PruebasLelas extends Automata{

	public PruebasLelas(String direccion) {
		super(direccion);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		String prueba = "LEIA";
		System.out.println(prueba.contains("I"));
	}

	@Override
	public boolean procesarCadena(String cadena) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean procesarCadenaDetallada(String cadena) {
		// TODO Auto-generated method stub
		return false;
	}

}
