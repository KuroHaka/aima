package probRedSensors;

//Author: Ginesta Basart

public class DefinicionEstado {
	
	//Implementacion del estado
	private static int numSensores;
	private static int numCentros;
	private int[][] estado;
	
	public DefinicionEstado(int s, int c){		
		int dim = s+c;
		estado = new int[dim][dim];
		numSensores = s;
		numCentros = c;
		
	}
	
	public int[][] actual(){
		return estado;
	}
	
	public int size() {
		return numSensores+numCentros;
	}
	
	public int numSensores() {
		return numSensores;
	}
	
	public int numCentros() {
		return numCentros;
	}
	
	/*public void asignaEstadoActual(int[][] nuevoActual) {
		estado = nuevoActual;
	}*/
	
	
	public int sumaConexiones(int elem) {
		//Necesario para comprovar la restriccion de conexiones
		int suma = 0;
		int dim = numSensores+numCentros;
		for(int i = 0; i < dim; i++ ) {
			suma += estado[elem][i];
		}
		return suma;
	}
	
	//Implementacion de los operadores conectar-desconectar
	
	public void nuevaConexion(int elem1, int elem2) {
		//canvia un bit 0 del estado a 1
		if (elem1 == elem2) System.out.print("No.");
		else {
			if (estado[elem1][elem2] == 0 && estado[elem2][elem1] == 0) {
				estado[elem1][elem2] = 1;
				estado[elem2][elem1] = 1;
			}
			else System.out.print("No.");
		}
		
	}
	
	public void eliminaConexion(int elem1, int elem2) {
		//canvia un bit 1 del estado a 0
		if (elem1 == elem2) System.out.print("No.");
		else {
			if (estado[elem1][elem2] == 1 && estado[elem2][elem1] == 1) {
				estado[elem1][elem2] = 0;
				estado[elem2][elem1] = 0;
			}
			else System.out.print("No.");
		}
	}
	
}
