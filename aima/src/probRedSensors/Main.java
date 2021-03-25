package probRedSensors;

public class Main {
	public static void main (String[] args) {
		RedSensor algo = new RedSensor(4, 12121, 10, 12121221);
		Estado estado = new Estado(algo.getSensor());
		System.out.println(estado.to_String());
	}
}
