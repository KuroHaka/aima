package probRedSensors;
import java.util.ArrayList;
import java.util.Iterator;

import IA.Red.CentrosDatos;
import IA.Red.Centro;
import IA.Red.Sensores;
import IA.Red.Sensor;

public class RedSensor {
	private CentrosDatos centroDatos;
	private Sensores sensores;
	
	public RedSensor(int nCentros, int seedCentros, int nSensores, int seedSensores) {
		centroDatos = new CentrosDatos(nCentros, seedCentros);
		sensores = new Sensores(nSensores,seedSensores);
	}
	
	
	public ArrayList<Centro> getCentros() {
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
	
	public void test() {
		
	}
	
}
