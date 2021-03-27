package probRedSensors;

public class Main {
	public static void main (String[] args) {
		RedSensor algo = new RedSensor(4, 12121, 10, 12121221);
		Tauler estado = new Tauler(algo.getSensor(), algo.getCentros());
		System.out.println(estado.to_String());
	}
}
