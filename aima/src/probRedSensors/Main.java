package probRedSensors;

import java.awt.EventQueue;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import aima.search.framework.Problem;
import aima.search.framework.Search;
import aima.search.framework.SearchAgent;
import aima.search.informed.HillClimbingSearch;
import aima.search.informed.SimulatedAnnealingSearch;

//Author: Zhensheng Chen, Ginesta Basart

public class Main {
	public static void main(String[] args) {
		
		RedSensor rd = new RedSensor(4, 1234, 200, 4321);
		DefinicionEstado de = new DefinicionEstado(rd);
		GeneradorSolucionInicial gsi = new GeneradorSolucionInicial(de);
		gsi.generaSolucionInicial1(de);
	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RedSensorJFrame frame = new RedSensorJFrame(de); //<-----------------esta linia pinta el grafo :3
					frame.setTitle("Solucio Inicial");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		Heuristica1 h1 = new Heuristica1();
		System.out.println("Coste de transmisión total:  "+h1.getHeuristicValue(de));
		Heuristica2 h2 = new Heuristica2();
		System.out.println("Aprovechamos "+h2.getHeuristicValue(de)+"mb/s de "+rd.maxCapacidad()+"mb/s");
	}
	
	/*
	public static void main(String[] args){
		RedSensor rd = new RedSensor(4, 1234, 100, 4321);
        DefinicionEstado e = new DefinicionEstado(rd);
        GeneradorSolucionInicial gsi = new GeneradorSolucionInicial(e);
		gsi.generaSolucionInicial2(e); //<----------------------------Cambiar para otra generacion inicial(2)!!!!!!
        RedHillClimbingSearch(e);
        EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RedSensorJFrame frame = new RedSensorJFrame(de); //<-----------------esta linia pinta el grafo :3
					frame.setTitle("Solucion Hill Climbing");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
        RedSimulatedAnnealingSearch(e);
        EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RedSensorJFrame frame = new RedSensorJFrame(de); //<-----------------esta linia pinta el grafo :3
					frame.setTitle("Solucion Simulated Annealing");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
    }
	
	private static void RedHillClimbingSearch(DefinicionEstado e) {
        
        try {
            Problem problem =  new Problem(e,new GeneradorSucesoresHillClimbing(), new RedGoalTest(),new Heuristica1());
            //<----------------------------Cambiar ultimo parametro para otro heuristico(3)!!!!!!
            Search search =  new HillClimbingSearch();
            SearchAgent agent = new SearchAgent(problem,search);
            
            System.out.println();
            printActions(agent.getActions());
            printInstrumentation(agent.getInstrumentation());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private static void RedSimulatedAnnealingSearch(DefinicionEstado e) {
        
        try {
        	Problem problem =  new Problem(e,new GeneradorSucesoresSimulatedAnnealing(), new RedGoalTest(),new Heuristica1());
        	//<----------------------------Cambiar ultimo parametro para otro heuristico(3)!!!!!!
        	SimulatedAnnealingSearch search =  new SimulatedAnnealingSearch(2000,100,5,0.001);
            //search.traceOn();
            SearchAgent agent = new SearchAgent(problem,search);
            
            System.out.println();
            printActions(agent.getActions());
            printInstrumentation(agent.getInstrumentation());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private static void printInstrumentation(Properties properties) {
        Iterator keys = properties.keySet().iterator();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            String property = properties.getProperty(key);
            System.out.println(key + " : " + property);
        }
        
    }
    
    private static void printActions(List actions) {
        for (int i = 0; i < actions.size(); i++) {
            String action = (String) actions.get(i);
            System.out.println(action);
        }
    }
    */
}
