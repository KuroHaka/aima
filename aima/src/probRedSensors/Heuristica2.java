package probRedSensors;

//Author: Ginesta Basart

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import aima.search.framework.HeuristicFunction;

public class Heuristica2 implements HeuristicFunction{

	private void recDFS(int v, boolean visited[], LinkedList<Integer> adj[], ArrayList<Integer> red, int count){
        visited[v] = true;
        red.add(v);
        
        Iterator<Integer> i = adj[v].listIterator();
        while (i.hasNext())
        {
            count++;
        	int n = i.next();
            if (!visited[n])
            	recDFS(n, visited, adj, red, count);
        }
        if (!i.hasNext()) {
        	red.add(0-count); //guarda el indice de cada hoja. marcado con un negativo
        }
    }

    private void DFS(int[][] a, int size, int nS, ArrayList<ArrayList<Integer>> clusters){
    	    	
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
        	recDFS(i, visited, adj, redDelSensor, 0);
        	clusters.add(redDelSensor);
        }
    }
	
	@Override
	public double getHeuristicValue(Object state) {
		
		//Necesita el estado DefinicionEstado e (pasado por parámetro??)
		double estimador = 0;
		
		int numSensores = e.numSensores();
		
		ArrayList<ArrayList<Integer>> clusters = new ArrayList<ArrayList<Integer>>();

		
		DFS(e.actual(), e.size(), numSensores, clusters);
			
		
		for (int i = 0; i < clusters.size(); i++) {
			ArrayList<Integer> red = clusters.get(i);
			ArrayList<Integer> hojas = new ArrayList<Integer>();
			int[] capacidades = new int[red.size()];
			//recorremos cada red para asignar las capacidades de casa sensor
			for(int j = 0; j < red.size(); j++ ) {
				if (red.get(j) > 0) {
					if (red.get(j) <= numSensores) {
						capacidades[j] = (int) e.getRedSensor().getSensor().get(red.get(j)).getCapacidad();
					}
					else capacidades[j] = 125;
				}
				else {
					hojas.add(-red.get(j));
					j--;
				}
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
						
						int idElem = red.get(k);
						if (red.lastIndexOf(idElem) == red.indexOf(idElem)) {
							//si té conectivitat amb un altre sensor
							if (capacidades[(red.get(lastIndexOf(idElem)))]) {
								
							}
							//de major capacitat
							
							//capacidad_camino = l'altre capacitat;
							capacidades[k] = capacidad_camino;
						}
						
						
					}
				}
				if (capacidad_camino > max) max = capacidad_camino;
			}
			if (max > estimador) estimador = max;
		}
		
		
		
		return estimador;
		
	}

}
