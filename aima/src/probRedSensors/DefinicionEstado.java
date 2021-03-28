package probRedSensors;

import java.util.ArrayList;

import IA.Red.Centro;
import IA.Red.Sensor;

//Author: Ginesta Basart

public class DefinicionEstado {
	
	//Implementacion del estado
	private RedSensor redSensor;
	private static int numSensores;
	private static int numCentros;
	private int[][] estado;
	
	public DefinicionEstado(RedSensor redsensor){
		this.redSensor = redsensor;
		numSensores = redsensor.getSensor().size();
		numCentros = redsensor.getCentros().size();
		int dim = size();
		estado = new int[dim][dim];
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
	
	public int getConexion(int x, int y) {
		return estado[x][y];
	}
	
	public RedSensor getRedSensor() {
		return redSensor;
	}
	
	public void printEstados() {
		System.out.println("-----------------------------------");
		for(int y = 0; y<size();y++) {
			for(int x = 0; x<size();x++) {
				System.out.print(" "+getConexion(x, y));
			}
			System.out.println();
		}
	}
	
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
	
	public void nuevaConexion(Sensor s1, Sensor s2) {
		nuevaConexion(redSensor.getSensor().indexOf(s1), redSensor.getSensor().indexOf(s2));
	}
	public void nuevaConexion(Sensor s, Centro c) {
		nuevaConexion(redSensor.getSensor().indexOf(s), redSensor.getCentros().indexOf(c)+numSensores);
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
