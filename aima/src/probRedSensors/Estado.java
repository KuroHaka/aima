package probRedSensors;

import java.util.ArrayList;
import java.util.Iterator;

import IA.Red.Sensor;

public class Estado {
	
	private int[][] estado;
	private ArrayList<Sensor> s;
	
	public Estado(ArrayList<Sensor> s) {
		this.s = s;
		cajaContenedora();
		System.out.println(to_String());
		create();
	}
	
	private void cajaContenedora() {
		int maxX = 0;
		int maxY = 0;
		Iterator<Sensor> it = s.iterator();
		while(it.hasNext()) {
			int x = it.next().getCoordX();
			int y = it.next().getCoordY();
			if( x > maxX ) {
				maxX = x;
			}
			if( y > maxY ) {
				maxY = y;
			}
		}
		estado = new int[maxX][maxY];
	}
	
	private void create() {
		for(int i = 0; i< s.size(); i++) {
			estado[s.get(i).getCoordX()][s.get(i).getCoordY()] = 1;
		}
	}
	
	public String to_String() {
		String string = "";
		for(int x = 0; x < estado.length; x++) {
			for(int y = 0; y < estado[x].length; y++) {
				string = string + " "+estado[x][y];
			}
			string = string + "\n";
		}
		
		return string;
	}
}
