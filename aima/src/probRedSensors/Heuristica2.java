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
		return (e.getRedSensor().maxCapacidad()-DFS(e.actual(),e));
	}
	
	private double DFS(int[][] m, DefinicionEstado d) {
		double coste = 0;
		for(int y = d.numSensores(); y < d.size(); y++) {
			
			//DFS por cada centro
			Stack<Sensor> v = new Stack<>();// visitado
			Stack<Sensor> nv = new Stack<>();//no visitado
			for(int x = 0; x < m.length; x++) {
				if(m[y][x]==1){
					v.add(d.getRedSensor().getSensor().get(x));
					nv.add(d.getRedSensor().getSensor().get(x));
				}
			}
			Pair<Double, Double> val = DFSforEach(v,nv,d,m);
			coste += val.second+val.first;
		}
		return coste;
	}
	
	//Pair.first capacidad accumulada, Pair.second capacidad hijos
	private Pair<Double,Double> DFSforEach(Stack<Sensor> v,Stack<Sensor> nv, DefinicionEstado d, int[][]m){
		double capacidadAcc = 0;
		double capacidadHermanos = 0;
		Iterator<Sensor> it = nv.iterator();
		
		while(it.hasNext()){
			Stack<Sensor> nv2 = new Stack<>();
			Sensor actual = it.next();
			//add Hijos
			for (int i = 0; i< d.numSensores() ; i++){
				if(m[i][d.getRedSensor().getSensor().indexOf(actual)]==1){
					Sensor hijo = d.getRedSensor().getSensor().get(i);
					if(!v.contains(hijo)){
						nv2.add(hijo);
						v.add(hijo);
					}
				}
			}
			nv.empty();
			
			//llamada recursiva
			Pair<Double, Double> val = DFSforEach(v,nv2,d,m);
			double costeHijos = val.second;
			capacidadAcc += val.first;
			double costeMio = actual.getCapacidad();
			
			if (costeHijos<=0) 
				capacidadHermanos += costeMio;
			else {
				capacidadAcc += costeHijos<costeMio? costeHijos:costeMio;
				capacidadHermanos += costeMio;
			}
			
		}
		
		return new Pair<>(capacidadAcc,capacidadHermanos);
	}
	
}