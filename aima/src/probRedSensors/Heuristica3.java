package probRedSensors;

//Author: Ginesta Basart
import aima.search.framework.HeuristicFunction;

public class Heuristica3 implements HeuristicFunction{
	@Override
	public double getHeuristicValue(Object state) {
		DefinicionEstado e = (DefinicionEstado) state;
		System.out.println(new Heuristica2().getHeuristicValue(e)+"/"+(-1)*new Heuristica1().getHeuristicValue(e));
		return (new Heuristica2().getHeuristicValue(e) / ((-1)*new Heuristica1().getHeuristicValue(e)));
	}
	
}