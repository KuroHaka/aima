package probRedSensors;

//Author: Ginesta Basart

import aima.search.framework.HeuristicFunction;

public class Heuristica1 implements HeuristicFunction{
	
	@Override
	public double getHeuristicValue(Object state) {
		
		DefinicionEstado e = (DefinicionEstado) state;
		double estimador = 0;
		
		//Suponiendo que el paquete sale de i para ir a j, se transmite la capacidad de i
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
					estimador += e.getRedSensor().getSensor().get(i).getCapacidad() * Math.pow(distancia(elem1, elem2), 2);
				}
			}
		}
		
		return estimador;
	}
	
	private int distancia(int[] elem1, int[] elem2) {
		
		int x = (elem1[0] - elem2[0])^2;
		int y = (elem1[1] - elem2[1])^2;
		return (int) Math.sqrt(x + y);
		
	}

}
