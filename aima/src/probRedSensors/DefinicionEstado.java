package probRedSensors;

import java.util.ArrayList;
import java.util.Stack;

import IA.Red.Centro;
import IA.Red.Sensor;

//Author: Ginesta Basart

public class DefinicionEstado{
	
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
	
	public DefinicionEstado(DefinicionEstado d){
		this.redSensor = d.redSensor;
		numSensores = redSensor.getSensor().size();
		numCentros = redSensor.getCentros().size();
		int dim = size();
		estado = new int[dim][dim];
		for(int i=0; i<dim; i++)
			  for(int j=0; j<dim; j++)
			    this.estado[i][j]=d.getConexion(i, j);
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
		
	public int getConexion(int x, int y) {
		return estado[y][x];
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
	
	public boolean esConnectable (int elem1, int elem2) {
		if (estado[elem1][elem2] == 1 && estado[elem2][elem1] == 1)
			return false;
		else if (elem1 == elem2) 
			return false;
		else if (elem1 >= numSensores && elem2 >= numSensores)
			return false;
		else if (((elem1 < numSensores && this.sumaConexiones(elem1) >= 3) || (elem2 < numSensores && this.sumaConexiones(elem2) >= 3)) || 
				((elem1 >= numSensores && this.sumaConexiones(elem1) >= 25) || (elem2 >= numSensores && this.sumaConexiones(elem2) >= 25))) 
			return false;
		else if (this.esHijo(elem1, elem2, new Stack<Integer>()))
			return false;
		else
			return true;
	}
	
	
	public boolean EliminarPadreYConnectar(int elem1, int elem2) {
		if(esConnectable(elem1, elem2)) {
			eliminaConexionPadre(elem1);
			nuevaConexionForzada(elem1, elem2);
			return true;
		}
		return false;
	}
	
	public void nuevaConexionForzada(int elem1, int elem2) {
		//canvia un bit 0 del estado a 1
		estado[elem1][elem2] = 1;
		estado[elem2][elem1] = 1;
	}
	
	public boolean nuevaConexion(int elem1, int elem2) {
		//canvia un bit 0 del estado a 1
		if (elem1 == elem2) System.out.println("No:elem1 == elem2 ");
		else if (this.esHijo(elem1, elem2, new Stack<Integer>())) {
		}
		else if (((elem1 <= numSensores && this.sumaConexiones(elem1) == 3) || (elem2 <= numSensores && this.sumaConexiones(elem2) == 3)) && 
				((elem1 > numSensores && this.sumaConexiones(elem1) == 25) || (elem2 > numSensores && this.sumaConexiones(elem2) == 25))) System.out.println("No:imite ");
		else {
			if (estado[elem1][elem2] == 0 && estado[elem2][elem1] == 0) {
				estado[elem1][elem2] = 1;
				estado[elem2][elem1] = 1;
				return true;
			}
			else System.out.println("No: ja connectat");
		}
		return false;
	}
	
	public boolean creaConexion(int elem1, int elem2) {
		//canvia un bit 0 del estado a 1
		if (elem1 == elem2) System.out.println("No:elem1 == elem2 ");
		else if (((elem1 <= numSensores && this.sumaConexiones(elem1) == 3) || (elem2 <= numSensores && this.sumaConexiones(elem2) == 3)) && 
				((elem1 > numSensores && this.sumaConexiones(elem1) == 25) || (elem2 > numSensores && this.sumaConexiones(elem2) == 25))) System.out.println("No:imite ");
		else {
			if (estado[elem1][elem2] == 0 && estado[elem2][elem1] == 0) {
				estado[elem1][elem2] = 1;
				estado[elem2][elem1] = 1;
				return true;
			}
			else System.out.println("No:ja connectat ");
		}
		return false;
	}

	public void creaConexion(Sensor s1, Sensor s2) {
		creaConexion(redSensor.getSensor().indexOf(s1), redSensor.getSensor().indexOf(s2));
	}
	public void creaConexion(Sensor s, Centro c) {
		creaConexion(redSensor.getSensor().indexOf(s), redSensor.getCentros().indexOf(c)+numSensores);
	}
	
	public boolean eliminaConexion(int elem1, int elem2) {
		//canvia un bit 1 del estado a 0
		if (elem1 == elem2) System.out.print("No.");
		else {
			if (estado[elem1][elem2] == 1 && estado[elem2][elem1] == 1) {
				estado[elem1][elem2] = 0;
				estado[elem2][elem1] = 0;
				return true;
			}
			else System.out.print("No.");
		}
		return false;
	}
	
	public void eliminaConexionForzada(int elem1, int elem2) {
		//canvia un bit 1 del estado a 0
		estado[elem1][elem2] = 0;
		estado[elem2][elem1] = 0;
	}

	private boolean esHijo(int elem1, int elem2, Stack<Integer> visitado) {
		
		for(int i = 0; i < this.numSensores(); i++) {
			if (estado[elem2][i] == 1 && !visitado.contains(i)) {
					if (i == elem1) return true;
					visitado.add(elem2);
					if(esHijo(elem1, i, visitado)) return true;		
			}
		}
		return false;
		//si es false los hijos de elem1 no tendran ningún padre aka centro de datos
	}
	
	public void eliminaConexionPadre(int elem1) {
		//elimina una conexión de elem1 de forma que sus hijos sigan conectados a un centro de datos
		for(int i = 0; i < this.numSensores(); i++) {

			if (estado[elem1][i] == 1) {
				if(i >= numSensores) {
					this.eliminaConexionForzada(elem1, i);
					return; 
				}
				else {
					Stack <Integer> s = new Stack<>();
					s.add(elem1);
					if (eliminaConexionPadre(i, s)) {
						this.eliminaConexion(elem1, i);
						return;
					}
				}		
			}
		}
	}
		
	
	
	private boolean eliminaConexionPadre(int elem1, Stack<Integer> visitado) {
		
		for(int i = 0; i < this.size(); i++) {

			if (estado[elem1][i] == 1 && !visitado.contains(i)) {
				if(i >= numSensores) {
					return true; 
				}
				
				else {
					visitado.add(elem1);
					if (eliminaConexionPadre(i,visitado)) {
						return true;
					}
				}		
			}
		}
		return false;
	}
	
}
