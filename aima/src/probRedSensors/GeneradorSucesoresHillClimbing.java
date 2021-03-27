package probRedSensors;
import java.util.ArrayList;

public class GeneradorSucesoresHillClimbing {

	
	public GeneradorSucesoresHillClimbing() {
		
	}
	
	//Implentacion de la generacion de sucesores para Hill Climbing
	public ArrayList<DefinicionEstado> sucesores(DefinicionEstado actual, int v) {
		//v es el valor del paquete a transmitir!!!!!!!!!!
		
		ArrayList<DefinicionEstado> r = new ArrayList<DefinicionEstado>();
		FuncionHeuristica heuristico = new FuncionHeuristica();
		int h_actual = heuristico.funcionHeuristica1(actual, v);
		
		for(int i = 0; i<actual.size(); i++) {
			for(int j = i+1; j > actual.size(); j++) {
				DefinicionEstado hijo = actual;
				
				
				
				r.add(hijo);
			}
		}
		
		return r;
	}
}
