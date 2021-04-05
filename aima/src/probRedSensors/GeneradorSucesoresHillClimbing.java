package probRedSensors;

import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//Author: Zhensheng Chen

public class GeneradorSucesoresHillClimbing implements SuccessorFunction {
	
	//Implentacion de la generacion de sucesores para Hill Climbing
	public List getSuccessors(Object state) {
		
		List <Successor> s = new ArrayList<>();
		DefinicionEstado e = (DefinicionEstado) state;
		int minHeur = 999999999; 
		
		int size = e.size();
		int nSensors = e.numSensores();
		
		//escojemos un sensor
		for (int i = 0; i<nSensors; i++) {
			//int i = (int) (Math.random()*nSensors);
				//iterador de componentes
			for(int j = 0; j < size; j++) {
				DefinicionEstado newE = new DefinicionEstado(e);
				if(newE.EliminarPadreYConnectar(i, j)) {
					int h = (int) new Heuristica3().getHeuristicValue(newE);
					if(h<minHeur) {
						minHeur = h;
						s.add(new Successor(i+"con"+j, newE));
					}
				}
			}
		}
		
		
		return s;
	}
}
