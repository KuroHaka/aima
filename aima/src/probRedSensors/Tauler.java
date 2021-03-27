package probRedSensors;

import java.util.ArrayList;
import java.util.Iterator;

import IA.Red.Centro;
import IA.Red.Sensor;

public class Tauler {
	
	private int[][] estado;
	private ArrayList<Sensor> s;
	private ArrayList<Centro> c;
	
	public Tauler(ArrayList<Sensor> s, ArrayList<Centro> c) {
		this.s = s;
		this.c = c;
		cajaContenedora();
		addSensores();
		addCentros();
	}
	
	private void cajaContenedora() {
		int maxX = 0;
		int maxY = 0;
		for(int i = 0 ; i<s.size(); i++) {
			int x = s.get(i).getCoordX();
			int y = s.get(i).getCoordY();
			if( x > maxX ) {
				maxX = x;
			}
			if( y > maxY ) {
				maxY = y;
			}
		}
		for(int i = 0 ; i<c.size(); i++) {
			int x = c.get(i).getCoordX();
			int y = c.get(i).getCoordY();
			if( x > maxX ) {
				maxX = x;
			}
			if( y > maxY ) {
				maxY = y;
			}
		}
		
		System.out.println(maxX+" "+ maxY);
		estado = new int[maxY+1][maxX+1];
		System.out.println(estado[1].length+" "+ estado.length);
	}
	
	private void addSensores() {
		for(int i = 0 ; i<s.size(); i++) {
			estado[s.get(i).getCoordY()][s.get(i).getCoordX()] = 1;
		}
	}
	
	private void addCentros() {
		for(int i = 0 ; i<c.size(); i++) {
			estado[c.get(i).getCoordY()][c.get(i).getCoordX()] = 2;
		}
	}
	
	public String to_String() {
		String string = "|";
		for(int x = 0; x < estado[0].length; x++)
			string = string +"--";
		
		for(int y = 0; y < estado.length; y++) {
			string = string + "|";
			for(int x = 0; x < estado[0].length; x++) {
				switch(estado[y][x]) {
				case 0:
					string = string + "  ";
					break;
				case 1:
					string = string + " S";
					break;
				case 2:
					string = string + " C";
					break;
				}
				
			}
			string = string + "|\n";
		}
		string = string + "|";
		for(int x = 0; x < estado[0].length; x++)
			string = string +"--";
		return string+ "|";
	}
	
	public int[][] getEstado(){
		return estado;
	}
	
}
