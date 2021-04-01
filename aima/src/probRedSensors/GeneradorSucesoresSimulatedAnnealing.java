package probRedSensors;

import java.util.ArrayList;
import java.util.List;

import aima.search.framework.SuccessorFunction;

//Author: Ginesta Basart

public class GeneradorSucesoresSimulatedAnnealing implements SuccessorFunction {

	@Override
	public List getSuccessors(Object state) {
		
		DefinicionEstado e = (DefinicionEstado) state;
		ArrayList<DefinicionEstado> sucesor = new ArrayList<DefinicionEstado>();
		
		int nS = e.numSensores();
		boolean found = false;
				
		while (!found) {
			int elem1 = (int) Math.random()* (nS + 1);
			int elem2 = (int) Math.random()* (nS + 1);
			
			if (elem1 != elem2) {
				if(e.getConexion(elem1, elem2) == 0) 
					//l'operador es afegir conexió
					e.eliminaConexionPadre(elem1);
					found = e.nuevaConexion(elem1, elem2);
					if (found) sucesor.add(e);				
			}
		}
		
		return sucesor;
	}

}
