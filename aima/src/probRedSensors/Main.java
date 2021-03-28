package probRedSensors;

import java.awt.EventQueue;

public class Main {
	public static void main(String[] args) {
		RedSensor rd = new RedSensor(4, 1234, 100, 4321);
		DefinicionEstado de = new DefinicionEstado(rd);
		GeneradorSolucionInicial gsi = new GeneradorSolucionInicial(de);
		gsi.generaSolucionInicial3(de);
		
		/*EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tablero frame = new Tablero(de);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		*/
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RedSensorJFrame frame = new RedSensorJFrame(de);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
