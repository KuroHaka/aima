package probRedSensors;

import aima.search.framework.SuccessorFunction;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

//Author: Liangwei Dong

public class GeneradorSucesoresHillClimbing implements SuccessorFunction {
	
	//Implentacion de la generacion de sucesores para Hill Climbing
	public List getSuccessors(Object aState) {
		
		ArrayList <DefinicionEstado>r = new ArrayList<>();
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
											DefinicionEstado suc_e = new DefinicionEstado (e);
											if (suc_e.esConnectable(i, j)) {
												suc_e.nuevaConexionForzada(i, j);
												suc_e.eliminaConexionForzada(i, k);
												suc_e.eliminaConexionForzada(j, l);
												r.add(suc_e);
											}
										}
									}
								}
								else {
									DefinicionEstado suc_e = new DefinicionEstado (e);
									if (suc_e.esConnectable(i, j)) {
										suc_e.nuevaConexionForzada(i, j);
										suc_e.eliminaConexionForzada(i, k);
										r.add(suc_e);
									}
								}
							}
						}
					}
					else if (e.sumaConexiones(j) >= 3) {
						for(int l = 0; l < size; ++l) {
							if (e.getConexion(j, l) == 1) {
								DefinicionEstado suc_e = new DefinicionEstado (e);
								if (suc_e.esConnectable(i, j)) {
									suc_e.nuevaConexionForzada(i, j);
									suc_e.eliminaConexionForzada(j, l);
									r.add(suc_e);
								}
							}
						}
					}
					else {
						DefinicionEstado suc_e = new DefinicionEstado (e);
						if (suc_e.esConnectable(i, j)) {
							suc_e.nuevaConexionForzada(i, j);
							r.add(suc_e);
						}
					}
				}
				else if (e.sumaConexiones(i) > 1 && e.sumaConexiones(j) > 1){
					DefinicionEstado suc_e = new DefinicionEstado (e);
					suc_e.eliminaConexion(i, j);//!!!
					r.add(suc_e);
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
											DefinicionEstado suc_e = new DefinicionEstado (e);
											if (suc_e.esConnectable(i, j)) {
												suc_e.nuevaConexionForzada(i, j);
												suc_e.eliminaConexionForzada(i, k);
												suc_e.eliminaConexionForzada(j, l);
												r.add(suc_e);
											}
										}
									}
								}
								else {
									DefinicionEstado suc_e = new DefinicionEstado (e);
									if (suc_e.esConnectable(i, j)) {
										suc_e.nuevaConexionForzada(i, j);
										suc_e.eliminaConexionForzada(i, k);
										r.add(suc_e);
									}
								}
							}
						}
					}
					else if (e.sumaConexiones(j) >= 25) {
						for(int l = 0; l < size; ++l) {
							if (e.getConexion(j, l) == 1) {
								DefinicionEstado suc_e = new DefinicionEstado (e);
								if (suc_e.esConnectable(i, j)) {
									suc_e.nuevaConexionForzada(i, j);
									suc_e.eliminaConexionForzada(j, l);
									r.add(suc_e);
								}
							}
						}
					}
					else {
						DefinicionEstado suc_e = new DefinicionEstado (e);
						if (suc_e.nuevaConexion(i, j)) {
							r.add(suc_e);
						}
					}
				}
				else if (e.sumaConexiones(i) > 1 && e.sumaConexiones(j) > 1){
					DefinicionEstado suc_e = new DefinicionEstado (e);
					suc_e.eliminaConexion(i, j);
					r.add(suc_e);
				}
			}
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RedSensorJFrame frame = new RedSensorJFrame(r.get(0)); //<-----------------esta linia pinta el grafo :3
					frame.setTitle("Intento de HillClimbing");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RedSensorJFrame frame = new RedSensorJFrame(r.get(1)); //<-----------------esta linia pinta el grafo :3
					frame.setTitle("Intento de HillClimbing");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RedSensorJFrame frame = new RedSensorJFrame(r.get(2)); //<-----------------esta linia pinta el grafo :3
					frame.setTitle("Intento de HillClimbing");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		return r;
	}
}
