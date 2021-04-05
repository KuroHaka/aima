package probRedSensors;

//Author: Ginesta Basart
import aima.search.framework.HeuristicFunction;

public class Heuristica3 implements HeuristicFunction{
	@Override
	public double getHeuristicValue(Object state) {
		DefinicionEstado e = (DefinicionEstado) state;
		return (new Heuristica1().getHeuristicValue(e)*(Math.pow(new Heuristica2().getHeuristicValue(e),2)+1));
	}
	
}