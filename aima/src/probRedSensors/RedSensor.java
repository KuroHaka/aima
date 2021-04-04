package probRedSensors;
import java.util.ArrayList;
import java.util.Iterator;

import IA.Red.CentrosDatos;
import IA.Red.Centro;
import IA.Red.Sensores;
import IA.Red.Sensor;

//Author: Zhensheng Chen
public class RedSensor {
	private ArrayList<Centro> centroDatos;
	private ArrayList<Sensor> sensores;
	
	public RedSensor(int nCentros, int seedCentros, int nSensores, int seedSensores) {
		
		CentrosDatos cd = new CentrosDatos(nCentros, seedCentros);
		centroDatos = new ArrayList<>();
		Iterator<Centro> it = cd.iterator();
		while(it.hasNext()) {
			centroDatos.add(it.next());
		}
		
		Sensores s = new Sensores(nSensores,seedSensores);
		sensores = new ArrayList<>();
		Iterator<Sensor> it1 = s.iterator();
		while(it1.hasNext()) {
			sensores.add(it1.next());
		}
		
	}
	
	
	public ArrayList<Centro> getCentros() {
		return centroDatos;
	}
	
	public ArrayList<Sensor> getSensor() {
		return sensores;
	}
	
	public void test() {
		
	}
	
	public int maxCapacidad() {
		int acc = 0;
		
		Iterator<Sensor> it = this.sensores.iterator();
		while(it.hasNext()) {
			acc += (int)it.next().getCapacidad();
		}
		return acc;
	}
	
}
