package probRedSensors;
import java.util.ArrayList;
import java.util.Iterator;

import IA.Red.CentrosDatos;
import IA.Red.Centro;

public class Algorithm {
	private CentrosDatos centroDatos;
	private Sensores sensores;
	
	public Algorithm(int nCentros, int seed) {
		centroDatos = new CentrosDatos(4, 4);
	}
	
	
	public void printCentros() {
		Iterator it = centroDatos.iterator();
		while(it.hasNext()) {
			System.out.println(((Centro)it.next()).toString());
		}
	}
	
	public void printSensores() {
		Iterator it = centroDatos.iterator();
		while(it.hasNext()) {
			System.out.println(((Centro)it.next()).toString());
		}
	}
}
