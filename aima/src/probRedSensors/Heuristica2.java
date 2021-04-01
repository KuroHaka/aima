package probRedSensors;

//Author: Zhensheng Chen
import java.util.Iterator;
import java.util.Stack;
import IA.Red.Sensor;
import aima.search.framework.HeuristicFunction;

public class Heuristica2 implements HeuristicFunction{
	@Override
	public double getHeuristicValue(Object state) {
		DefinicionEstado e = (DefinicionEstado) state;
		return DFS(e.actual(),e);
	}
	
	private double DFS(int[][] m, DefinicionEstado d) {
		double coste = 0;
		for(int y = d.numSensores(); y < m.length; y++) {
			
			//DFS por cada centro
			Stack<Sensor> v = new Stack<>();// visitado
			Stack<Sensor> nv = new Stack<>();//no visitado
			for(int x = 0; x < m.length; x++) {
				if(m[y][x]==1){
					v.add(d.getRedSensor().getSensor().get(x));
					nv.add(d.getRedSensor().getSensor().get(x));
				}
			}
			coste += DFSforEach(v,nv,d,m);
			System.out.println(coste);
		}
		return coste;
	}
	
	private double DFSforEach(Stack<Sensor> v,Stack<Sensor> nv, DefinicionEstado d, int[][]m){
		double coste = 0;
		Stack<Sensor> nv2 = new Stack<>();
		Iterator<Sensor> it = nv.iterator();
		while(it.hasNext() && nv2.size()<2){
			Sensor actual = it.next();
			//add Hijos
			for (int i = 0; i< d.numSensores() ; i++){
				if(m[d.getRedSensor().getSensor().indexOf(actual)][i]==1){
					Sensor hijo = d.getRedSensor().getSensor().get(i);
					if(!v.contains(hijo)){
						nv2.add(hijo);
						v.add(hijo);
					}
				}
			}
			nv.empty();

			//llamada recursiva
			double costeHijos = DFSforEach(v,nv2,d,m);
			double costePadre = actual.getCapacidad();
			if (costeHijos<=0) {
				coste += costePadre;
			}
			else
				coste += costeHijos<costePadre? costeHijos:costePadre;
		}
		
		return coste;
	}
	
}