package probRedSensors;

public class DefinicionEstado {
	
	//Implementacion del estado
	private static int dim;
	private int[][] estado;
	
	public DefinicionEstado(int s, int c){		
		dim = s+c;
		estado = new int[dim][dim];
	}
	
	public int[][] actual(){
		return estado;
	}
	
	public void asignaEstadoActual(int[][] nuevoActual) {
		estado = nuevoActual;
	}
	
	//Necesario para comprovar la restriccion de conexiones
	public int sumaConexiones(int elem) {
		int suma = 0;
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
			if (estado[elem1][elem2] == 0) estado[elem1][elem2] = 1;
			else System.out.print("No.");
		}
		
	}
	
	public void eliminaConexion(int elem1, int elem2) {
		//canvia un bit 1 del estado a 0
		if (elem1 == elem2) System.out.print("No.");
		else {
			if (estado[elem1][elem2] == 1) estado[elem1][elem2] = 0;
			else System.out.print("No.");
		}
	}
	
}
