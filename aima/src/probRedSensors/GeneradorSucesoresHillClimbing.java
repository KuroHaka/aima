package probRedSensors;

import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

//Author: Zhensheng Chen

public class GeneradorSucesoresHillClimbing implements SuccessorFunction {
	
	//Implentacion de la generacion de sucesores para Hill Climbing
	public List getSuccessors(Object state) {
		
		List <Successor> s = new ArrayList<>();
		DefinicionEstado e = (DefinicionEstado) state;
		RedSensor r = e.getRedSensor();
		
		int size = e.size();
		int nSensors = e.numSensores();
		
		//iterador de sensores
		for(int i = 0; i < nSensors; i++) {
			//iterador de componentes
			for(int j = 0; j < size; j++) {
				DefinicionEstado newE = new DefinicionEstado(e);
				if(newE.EliminarPadreYConnectar(i, j))
					s.add(new Successor(i+"con"+j, newE));
			}
		}
		
		for(int i = 0; i < s.size(); i++) {
			//plot(s,i);
		}
		
		
		return s;
	}
	
	public void plot(List <Successor> s, int i) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RedSensorJFrame frame = new RedSensorJFrame((DefinicionEstado)s.get(i).getState()); //<-----------------esta linia pinta el grafo :3
					frame.setTitle("Hill Climbing2");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
