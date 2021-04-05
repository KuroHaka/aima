package probRedSensors;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;

//Author: Ginesta Basart y Zhensheng Chen

public class GeneradorSucesoresSimulatedAnnealing implements SuccessorFunction {

	@Override
	public List getSuccessors(Object state) {
		DefinicionEstado olde = (DefinicionEstado)state;
		DefinicionEstado e = new DefinicionEstado(olde);
		List<Successor> sucesor = new ArrayList<>();
		
		int nS = e.numSensores();
		int n = e.size();
		int c = e.numCentros();
		boolean found = false;
				
		while (!found) {
			int elem1 = (int) (Math.random()*nS);
			int elem2 = (int) (Math.random()*n);
			
			//l'operador es afegir conexió
			int p = (int) (Math.random()*2);
			if(p==0)
				found = e.EliminarPadreYConnectar(elem1, elem2);
			else
				found = e.EliminarPadreYConnectar(elem1, (nS-1)+elem2%c);
			if (found) sucesor.add(new Successor(""+elem1+","+elem2, e));
		}
		 
		return sucesor;
	}

}
