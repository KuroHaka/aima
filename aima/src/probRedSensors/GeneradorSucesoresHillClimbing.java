package probRedSensors;

import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;

import java.util.ArrayList;
import java.util.List;

//Author: Liangwei Dong

public class GeneradorSucesoresHillClimbing implements SuccessorFunction {
	
	//Implentacion de la generacion de sucesores para Hill Climbing
	public List getSuccessors(Object aState) {
		
		ArrayList r = new ArrayList();
		DefinicionEstado e = (DefinicionEstado) aState;
		
		int size = e.size();
		int nSensors = e.numSensores();
		
		for(int i = 0; i < size; i++) {
			for(int j = i+1; j < size; j++) {
				DefinicionEstado suc_e = e;
				if (suc_e.getConexion(i, j) == 0) {
					if (i < nSensors) {
						if (suc_e.sumaConexiones(i) == 3)
							suc_e.eliminaConexionPadre(i);
						else if (suc_e.sumaConexiones(i) == 25)
							suc_e.eliminaConexionPadre(i);
					}
					if (j < nSensors) {
						if (suc_e.sumaConexiones(j) == 3)
							suc_e.eliminaConexionPadre(j);
						else if (suc_e.sumaConexiones(j) == 25)
							suc_e.eliminaConexionPadre(j);
					}
					if (suc_e.nuevaConexion(i, j))
						r.add(suc_e);
				}
			}
		}
		return r;
	}
}
