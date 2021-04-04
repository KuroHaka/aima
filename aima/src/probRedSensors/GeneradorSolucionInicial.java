package probRedSensors;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import IA.Red.Centro;
import IA.Red.Sensor;

//Author: Ginesta Basart i  Zhensheng Chen

public class GeneradorSolucionInicial {

	
	public GeneradorSolucionInicial(DefinicionEstado e) {
		
	}
	
	//Implementa la generacion de la solucion de partida para hill climbing
	
	public void generaSolucionInicial1(DefinicionEstado e) {
		//Reparte los sensores entre los centros de datos
		
		int numCentros = e.numCentros();
		int numSensores = e.numSensores();
		
		int SxC = 0; //representa la cantidad de sensores que se pueden conectar a un centro de forma equilibrada, o la cantidad maxima por restriccion del problema
		int sobran = 0;
		if(numSensores < 25*numCentros) {
			//Primer caso.
			SxC = numSensores/numCentros;
			sobran = numSensores%numCentros;
		}
		else if(numSensores == 25*numCentros) {
			SxC = 25;
			sobran = 0;
		}
		else {
			//Tercer caso.
			SxC = 25;
			sobran = numSensores - 25*numCentros;
		}
					
		for (int i = 0; i < numCentros; i++) {
			for (int j = 0; j < SxC; j++) {
				//Conecta el centro de datos con los primeros SxC sensores libres
				e.creaConexion(numSensores+i, j+SxC*i); //offset de sensores ya asignados a un centro de datos
			}
		}
		
		if (sobran != 0) {
			
			if (SxC < 25) {
				//Primer caso. Hay que repartir los sobrantes entre las conexiones libres de los centros
				int asignados = SxC*numCentros;
				int j = 0;
				int centroAdjudicado = numSensores+j;
				for(int i = 0; i < sobran; i++) {
					if (centroAdjudicado > e.size()) j = 0;
					e.creaConexion(asignados+i, centroAdjudicado);
					j++;
				}	
			}
			else {
				//Tercer caso. Hay que repartir los sobrantes entre los sensores que ya estan conectados a algun centro (ya tendran una conexion)
				for (int i = 0; i < sobran; i++) {
					int sensorAsignado = 25*numCentros+1+i;
					for (int j = 0; sensorAsignado < numSensores && j < 2; j++) {
						//los asignamos de dos en dos para llegar al tope de conexiones posibles con los sensores ya concectados
						e.creaConexion(sensorAsignado, j+2*i);
						if (e.sumaConexiones(25*numCentros+1+i) > 3) {
							j = 2;
							i = sobran;
						}
					}
				}
			}
		}
		
		
	}
	
	public DefinicionEstado generaSolucionInicial2(DefinicionEstado e) {
		RedSensor rs = e.getRedSensor();
		ArrayList<Centro> centroDatos = new ArrayList<> (rs.getCentros());
		ArrayList<Sensor> sensores = new ArrayList<> (rs.getSensor());
		
		ArrayList<Cluster> res = new ArrayList<>();
		
		Iterator <Centro> itc = centroDatos.iterator();
		while(itc.hasNext()) {
			res.add(new Cluster(itc.next()));
		}
		
		Iterator<Sensor> its = sensores.iterator();
		
		//centro más cercano de cada sensor
		while(its.hasNext()) {
			Sensor s = its.next();
			
			itc = centroDatos.iterator();
			Centro c = itc.next();
			Centro minCent = c;
			double distMin = Math.sqrt(Math.pow(s.getCoordX()-c.getCoordX(), 2) + Math.pow(s.getCoordY()-c.getCoordY(), 2));
			while(itc.hasNext()) {
				c = itc.next();
				double distance = Math.sqrt(Math.pow(s.getCoordX()-c.getCoordX(), 2) + Math.pow(s.getCoordY()-c.getCoordY(), 2));
				if(distMin > distance) {
					distMin = distance;
					minCent = c;
				}
			}
			res.get(centroDatos.indexOf(minCent)).addSensor(s);
		}
		
		
		Iterator <Cluster>it = res.iterator();
		
		//por cada cluster
		while(it.hasNext()) {
			Cluster cluster = it.next();
			Centro c = cluster.getCentro();
			ArrayList <Pair<Sensor,Integer>> as = cluster.getCoste5();
			//ordenamos de cerca a lejos
			as.sort(new Comparator<Pair<Sensor, Integer>>() {
				@Override
				public int compare(Pair<Sensor, Integer> o1, Pair<Sensor, Integer> o2) {
					return distance(o1.first, c).compareTo(distance(o2.first,c));
				}
			});
			
			cluster.getCoste2().sort(new Comparator<Pair<Sensor, Integer>>() {
				@Override
				public int compare(Pair<Sensor, Integer> o1, Pair<Sensor, Integer> o2) {
					return distance(o1.first, c).compareTo(distance(o2.first,c));
				}
			});
			
			cluster.getCoste1().sort(new Comparator<Pair<Sensor, Integer>>() {
				@Override
				public int compare(Pair<Sensor, Integer> o1, Pair<Sensor, Integer> o2) {
					return distance(o1.first, c).compareTo(distance(o2.first,c));
				}
			});
			
			as.addAll(cluster.getCoste2());
			as.addAll(cluster.getCoste1());
			
			
			ArrayList<Pair<Sensor,Integer>> connectados = new ArrayList<>();
			
			if(!as.isEmpty()) {
				while(cluster.connexionesRestantes()>0 && !as.isEmpty()) {
					e.creaConexion(as.get(0).first, c);
					cluster.CentroDec();
					as.get(0).second--;
					connectados.add(as.get(0));
					as.remove(0);
				}
				//iterator sensores conectados
				Iterator<Pair<Sensor,Integer>> sc = connectados.iterator();
				//mientras array sensores no esté vacío
				while(sc.hasNext() && !as.isEmpty()) {
					Pair<Sensor,Integer> s= sc.next();
					as.sort(new Comparator<Pair<Sensor, Integer>>() {
						@Override
						public int compare(Pair<Sensor, Integer> o1, Pair<Sensor, Integer> o2) {
							return distance(o1.first, s.first).compareTo(distance(o1.first,s.first));
						}
					});
					
					if(distance(as.get(0).first, s.first) <distance(as.get(0).first, c)) {
						e.creaConexion(as.get(0).first, s.first);
						s.second=s.second-1;
						if(s.second<=0) 
							connectados.remove(s);
					}
					else {
						e.creaConexion(as.get(0).first, c);
					}
					as.get(0).second = as.get(0).second-1;
					connectados.add(as.get(0));
					as.remove(0);
					sc = connectados.iterator();
				}
			}
		}
		return e;
	}
	
	private Double distance(Sensor s, Centro c) {
		return Math.sqrt(Math.pow(s.getCoordX()-c.getCoordX(), 2) + Math.pow(s.getCoordY()-c.getCoordY(), 2));
	}
	private Double distance(Sensor s1, Sensor s2) {
		return Math.sqrt(Math.pow(s1.getCoordX()-s2.getCoordX(), 2) + Math.pow(s1.getCoordY()-s2.getCoordY(), 2));
	}
	
}
