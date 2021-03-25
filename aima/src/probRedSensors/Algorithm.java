package probRedSensors;
import java.util.ArrayList;
import java.util.Iterator;

import IA.Red.CentrosDatos;
import IA.Red.Centro;
import IA.Red.Sensores;
import IA.Red.Sensor;

public class Algorithm {
	private CentrosDatos centroDatos;
	private Sensores sensores;
	
	public Algorithm(int nCentros, int seed) {
		centroDatos = new CentrosDatos(4, 4);
		sensores = new Sen
	}
	
	
	public ArrayList<Centro> printCentros() {
		ArrayList<Centro> array = new ArrayList<>();
		Iterator it = centroDatos.iterator();
		while(it.hasNext()) {
			array.add((Centro)it.next());
		}
		return array;
	}
	
	public ArrayList<Sensor> getSensor() {
		ArrayList<Sensor> array = new ArrayList<>();
		Iterator it = sensores.iterator();
		while(it.hasNext()) {
			array.add((Sensor)it.next());
		}
		return array;
	}
}
