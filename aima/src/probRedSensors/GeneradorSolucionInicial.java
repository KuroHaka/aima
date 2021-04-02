package probRedSensors;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
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
		
		int div = numSensores/numCentros;
		int mod = numSensores%numCentros;
		
		if (numSensores - 25*div > mod/numSensores/2) {
			System.out.print("No empezamos bien: Hay demasiados sensores, y no se pueden respetar las restricciones de conexion.");
		}
		else {
			
			for (int i = 0; i < numCentros; i++) {
				for (int j = 0; j < div; j++) {
					e.creaConexion(numSensores+i, j+div*i); //offset de sensores ya asignados a un centro de datos
					if(e.sumaConexiones(numSensores+i) > 25) {
						System.out.print("No vamos nada bien: los sensores no se estan repartiendo entre los centros de datos respetando la limitación de conexiones.");
						mod = 0;
						j = div;
						i = numCentros;
					}
				}
			}
			
			if (mod != 0) {
				//hay más de 25*numCentros sensores. Hay que repartirlos entre los sensores que ya estan conectados a algun centro (solo tendran una conexion)
				for (int i = 0; i < mod; i++) {
					for (int j = 0; 25*numCentros+1+i <= numSensores && j < 2; j++) {
						//los asignamos de dos en dos para llegar al tope de conexiones posibles con los sensores ya concectados
						e.creaConexion(25*numCentros+1+i, j+2*i);
						if (e.sumaConexiones(25*numCentros+1+i) > 3) {
							System.out.print("No vamos nada bien: los sensores restantes no se estan repartiendo entre los sensores asignados respetando la limitación de conexiones.");
							j = 2;
							i = mod;
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
		
		HashMap<Centro, ArrayList<Pair<Sensor,Integer>>> res = new HashMap<>();
		
		Iterator <Centro> itc = centroDatos.iterator();
		while(itc.hasNext()) {
			res.put(itc.next(), new ArrayList<Pair<Sensor,Integer>>());
		}
		
		Iterator<Sensor> its = sensores.iterator();
		
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
			res.get(minCent).add(new Pair<Sensor, Integer>(s, 3));
			
			if(res.get(minCent).size()>=25) {
				centroDatos.remove(minCent);
			}
			
			sensores.remove(s);
			its = sensores.iterator();
		}
		
		itc = res.keySet().iterator();
		while(itc.hasNext()) {
			Centro c = itc.next();
			ArrayList <Pair<Sensor,Integer>> as = res.get(c);
			as.sort(new Comparator<Pair<Sensor, Integer>>() {
				@Override
				public int compare(Pair<Sensor, Integer> o1, Pair<Sensor, Integer> o2) {
					return distance(o1.first, c).compareTo(distance(o1.first,c));
				}
			});
			
			ArrayList<Pair<Sensor,Integer>> connectados = new ArrayList<>();
			
			if(!as.isEmpty()) {
				e.creaConexion(as.get(0).first, c);
				as.get(0).second = as.get(0).second-1;
				connectados.add(as.get(0));
				as.remove(0);
				
				Iterator<Pair<Sensor,Integer>> sc = connectados.iterator();
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
	
	
	public DefinicionEstado generaSolucionInicial3(DefinicionEstado e) {
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
					return distance(o1.first, c).compareTo(distance(o1.first,c));
				}
			});
			
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
				as.addAll(cluster.getCoste2());
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
				as.addAll(cluster.getCoste1());
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
