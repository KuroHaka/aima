package probRedSensors;

//Author: Ginesta Basart

public class FuncionHeuristica {

	public FuncionHeuristica() {
		
	}
	
	private int distancia(int[] elem1, int[] elem2) {
				
		int x = (elem1[0] - elem2[0])^2;
		int y = (elem1[1] - elem2[1])^2;
		return (int) Math.sqrt(x + y);
		
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
		
		return estimador/2;
		//se divide entre dos porque las conexiones son bidireccionales. se puede optimizar (y no dejar tan cutre) recorriendo solo una mitad de la matriz espejo
	}
	
}
