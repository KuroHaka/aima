package probRedSensors;

public class Main {
	public static void main (String[] args) {
		RedSensor rd = new RedSensor(4, 12121, 10, 12121221);
		DefinicionEstado de = new DefinicionEstado(rd);
		GeneradorSolucionInicial gsi = new GeneradorSolucionInicial(de);
		gsi.generaSolucionInicial3(de);
		
	}
}
