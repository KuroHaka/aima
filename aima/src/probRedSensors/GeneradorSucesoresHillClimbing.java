package probRedSensors;

import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;

import java.util.ArrayList;
import java.util.List;

public class GeneradorSucesoresHillClimbing implements SuccessorFunction {
	
	//Implentacion de la generacion de sucesores para Hill Climbing
	public List getSuccessors(Object aState) {
		
		ArrayList r = new ArrayList();
		DefinicionEstado e = (DefinicionEstado) aState;
		
		int nSensors = e.numSensores();
		
		for(int i = 0; i < nSensors; i++) {
			for(int j = i+1; j < nSensors; j++) {
				DefinicionEstado suc_e = e;
				if (suc_e.getConexion(i, j) == 0) {
					suc_e.nuevaConexion(i, j);
					if (suc_e.sumaConexiones(j) == 3) {
						for(int k = 0; k < nSensors; ++k)
					}
					if (suc_e.sumaConexiones(i) == 3)
						for(int k = 0; k < n)
						suc_e.eliminaConexion(i);
					}
				
				
				
				r.add(suc_e);
			}
		}
		
		return r;
	}
}
