package probRedSensors;

import java.util.ArrayList;

public class Estado {
	
	private ArrayList<ArrayList<int>> estado;
	
	public Estado(ArrayList<Sensor> s) {
		
	}
	
	public void crateandprint(ArrayList<Sensor> s) {
		
		int sizeS = s.size();
		
		estado = new ArrayList<ArrayList> (sizeS);
		for(int i = 0; i< sizeS; i++) {
			estado.add(new ArrayList<ArrayList>(sizeS, 0));
			for (int j = 0; i < sizeS, j++) {
				System.out.print(estado.get(i).get(j) + " ");
			}
			System.out.println();
		}
		
	}
	
}
