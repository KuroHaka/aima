package probRedSensors;

import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;

import java.util.ArrayList;
import java.util.List;

//Author: Liangwei Dong

public class GeneradorSucesoresHillClimbing implements SuccessorFunction {
	
	//Implentacion de la generacion de sucesores para Hill Climbing
	public List getSuccessors(Object aState) {
		
		ArrayList<DefinicionEstado> r = new ArrayList<>();
		DefinicionEstado e = (DefinicionEstado) aState;
		
		int size = e.size();
		int nSensors = e.numSensores();
		
		for(int i = 0; i < nSensors; i++) {
			for(int j = i+1; j < nSensors; j++) {
				if (e.getConexion(i, j) == 0) {
					if (e.sumaConexiones(i) >= 3) {
						for(int k = 0; k < size; ++k) {
							if (e.getConexion(i, k) == 1) {
								if (e.sumaConexiones(j) >= 3) {
									for(int l = 0; l < size; ++l) {
										if (e.getConexion(j, l) == 1) {
											DefinicionEstado suc_e = e;
											if (suc_e.creaConexion(i, j)) {
												suc_e.eliminaConexion(i, k);
												suc_e.eliminaConexion(j, l);
												r.add(suc_e);
											}
										}
									}
								}
								else {
									DefinicionEstado suc_e = e;
									if (suc_e.creaConexion(i, j)) {
										suc_e.eliminaConexion(i, k);
										r.add(suc_e);
									}
								}
							}
						}
					}
					else if (e.sumaConexiones(j) >= 3) {
						for(int l = 0; l < size; ++l) {
							if (e.getConexion(j, l) == 1) {
								DefinicionEstado suc_e = e;
								if (suc_e.creaConexion(i, j)) {
									suc_e.eliminaConexion(j, l);
									r.add(suc_e);
								}
							}
						}
					}
					else {
						DefinicionEstado suc_e = e;
						if (suc_e.creaConexion(i, j)) {
							r.add(suc_e);
						}
					}
				}
			}
			for(int j = nSensors; j < size; j++) {
				if (e.getConexion(i, j) == 0) {
					if (e.sumaConexiones(i) >= 3) {
						for(int k = 0; k < size; ++k) {
							if (e.getConexion(i, k) == 1) {
								if (e.sumaConexiones(j) >= 25) {
									for(int l = 0; l < size; ++l) {
										if (e.getConexion(j, l) == 1) {
											DefinicionEstado suc_e = e;
											if (suc_e.creaConexion(i, j)) {
												suc_e.eliminaConexion(i, k);
												suc_e.eliminaConexion(j, l);
												r.add(suc_e);
											}
										}
									}
								}
								else {
									DefinicionEstado suc_e = e;
									if (suc_e.creaConexion(i, j)) {
										suc_e.eliminaConexion(i, k);
										r.add(suc_e);
									}
								}
							}
						}
					}
					else if (e.sumaConexiones(j) >= 25) {
						for(int l = 0; l < size; ++l) {
							if (e.getConexion(j, l) == 1) {
								DefinicionEstado suc_e = e;
								if (suc_e.creaConexion(i, j)) {
									suc_e.eliminaConexion(j, l);
									r.add(suc_e);
								}
							}
						}
					}
					else {
						DefinicionEstado suc_e = e;
						if (suc_e.creaConexion(i, j)) {
							r.add(suc_e);
						}
					}
				}
			}
		}
		return r;
	}
}
