package probRedSensors;

//Author: Ginesta Basart
import aima.search.framework.HeuristicFunction;

public class Heuristica3 implements HeuristicFunction{
	@Override
	public double getHeuristicValue(Object state) {
		DefinicionEstado e = (DefinicionEstado) state;
		return (new Heuristica2().getHeuristicValue(state) / new Heuristica1().getHeuristicValue(state));
	}
	
}