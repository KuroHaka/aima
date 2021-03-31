package probRedSensors;
import java.util.*;

//Author: Ginesta Basart

public class FuncionHeuristica {

	public FuncionHeuristica() {
		
	}
	
	private int distancia(int[] elem1, int[] elem2) {
				
		int x = (elem1[0] - elem2[0])^2;
		int y = (elem1[1] - elem2[1])^2;
		return (int) Math.sqrt(x + y);
		
	}
	
	private void recDFS(int v, boolean visited[], LinkedList<Integer> adj[], ArrayList<Integer> red, ArrayList<Integer> hojas, int count){
        visited[v] = true;
        red.add(v);
        
        Iterator<Integer> i = adj[v].listIterator();
        while (i.hasNext())
        {
            count++;
        	int n = i.next();
            if (!visited[n])
            	recDFS(n, visited, adj, red, hojas, count);
        }
        if (!i.hasNext()) {
        	hojas.add(0 - count);
        }
    }

    private void DFS(int[][] a, int size, int nS, ArrayList<ArrayList<Integer>> clusters, ArrayList<Integer> hojas){
    	    	
    	LinkedList<Integer> adj[] = new LinkedList[size];
        for (int i = 0; i < size; ++i) adj[i] = new LinkedList();
    	
        //recorrido de la matriz espejo
        for(int i = 0; i < size; i++) {
        	for(int j = i+1; j < size; j++) {
        		if (a[i][j] == 1) {
        			adj[i].add(j);
        			adj[j].add(i);
        		}
        	}
        }
              
        for (int i = nS+1; i <= size; i++) {
        	boolean visited[] = new boolean[size];
        	ArrayList<Integer> redDelSensor = new ArrayList<Integer>();
        	recDFS(i, visited, adj, redDelSensor, hojas, 0);
        	clusters.add(redDelSensor);
        }
    }
	
	
	
	//Implementación de la primera función heurística: suma del coste de todas las connexiones
	public int funcionHeuristica1(DefinicionEstado e, int valorPaquete) {
		int estimador = 0;
		
		for (int i = 0; i < e.size(); i++) {
			for(int j = i+1; j < e.size(); j++) {
				
				if (e.getConexion(i, j) == 1) {
					int[] elem1 = {0, 0}, elem2 = {0, 0};
					if (i > e.numSensores()) {
						//elem1 es un centros de dados
						int aux[] = {e.getRedSensor().getCentros().get(i-e.numSensores()).getCoordX(), e.getRedSensor().getCentros().get(i-e.numSensores()).getCoordY()};
						elem1 = aux;
					}
					else {
						int aux[] = {e.getRedSensor().getSensor().get(j).getCoordX(), e.getRedSensor().getSensor().get(j).getCoordY()};
						elem2 = aux;
					}
					
					if (j > e.numSensores()) {
						//elem2 es un centros de dados
						int aux[] = {e.getRedSensor().getCentros().get(j-e.numSensores()).getCoordX(), e.getRedSensor().getCentros().get(j-e.numSensores()).getCoordY()};
						elem2 = aux;
					}
					else {
						int aux[] = {e.getRedSensor().getSensor().get(i).getCoordX(), e.getRedSensor().getSensor().get(i).getCoordY()};
						elem1 = aux;
					}
					estimador += valorPaquete * distancia(elem1, elem2)^2;
				}
			}
		}
		
		return estimador;
	}
	
	//Implementación de la segunda función heurística: suma de la cantidad de información de todas las connexiones
	public int funcionHeuristica2(DefinicionEstado e, int valorPaquete) {
		int estimador = 0;
				
		int numSensores = e.numSensores();
		
		ArrayList<ArrayList<Integer>> clusters = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> hojas = new ArrayList<Integer>();
		
		DFS(e.actual(), e.size(), numSensores, clusters, hojas);
			
		
		for (int i = 0; i < clusters.size(); i++) {
			ArrayList<Integer> red = clusters.get(i);
			int[] capacidades = new int[red.size()];
			//recorremos cada red para asignar las capacidades de casa sensor
			for(int j = 0; j < red.size(); j++ ) {
				if (red.get(j) <= numSensores) capacidades[j] = (int) e.getRedSensor().getSensor().get(red.get(j)).getCapacidad();
				else capacidades[j] = 125;
			}
			
			//recorremos hojas para encontrar los puntos de entrada de la red y procesar las capacidades
			int hoja_anterior = 0;
			int id_hoja = -1;
			int max = 0;
			for(int j = 0; j < hojas.size(); j++) {
				hoja_anterior = id_hoja;
				id_hoja = hojas.get(j);
				int capacidad_camino = 10;
				for (int k = id_hoja; k > hoja_anterior; k--) {
					
					if (capacidades[k] < capacidad_camino) {
						capacidad_camino = capacidades[k];
					}
					else if (capacidades[k] == 125) capacidades[k] = capacidad_camino;
					else {
						//si té conectivitat amb un altre sensor de major capacitat
						//capacidad_camino = l'altre capacitat;
						//capacidades[k] = capacidad_camino;
						
					}
				}
				if (capacidad_camino > max) max = capacidad_camino;
			}
			if (max > estimador) estimador = max;
		}
		
		
		
		return estimador;
	}
		
}
