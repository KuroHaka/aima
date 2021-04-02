package probRedSensors;

import java.util.ArrayList;

import IA.Red.Centro;
import IA.Red.Sensor;

public class Cluster {
	private Centro centro;
	private int connexiones;
	private ArrayList<Pair<Sensor,Integer>> coste5;
	private ArrayList<Pair<Sensor,Integer>> coste2;
	private ArrayList<Pair<Sensor,Integer>> coste1;
	
	public Cluster(Centro c){
		this.centro = c;
		this.coste5 = new ArrayList<>();
		this.coste2 = new ArrayList<>();
		this.coste1 = new ArrayList<>();
		this.connexiones = 25;
	}
	
	public Centro getCentro() {
		return centro;
	}
	public ArrayList<Pair<Sensor,Integer>> getCoste5() {
		return coste5;
	}
	public ArrayList<Pair<Sensor,Integer>> getCoste2() {
		return coste2;
	}
	public ArrayList<Pair<Sensor,Integer>> getCoste1() {
		return coste1;
	}
	public int connexionesRestantes() {
		return connexiones;
	}
	
	public void addSensor(Sensor s) {
		switch ((int)s.getCapacidad()){
			case 5:
				this.coste5.add(new Pair<Sensor, Integer>(s,3));
				break;
			case 2:
				this.coste2.add(new Pair<Sensor, Integer>(s,3));
				break;
			case 1:
				this.coste1.add(new Pair<Sensor, Integer>(s,3));
				break;
				
		}
	}

	public void CentroDec() {
		this.connexiones--;
	}
	
}
