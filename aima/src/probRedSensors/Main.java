package probRedSensors;

import java.awt.EventQueue;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import javax.swing.plaf.metal.*;

import javax.swing.UIManager;

import aima.search.framework.Problem;
import aima.search.framework.Search;
import aima.search.framework.SearchAgent;
import aima.search.informed.HillClimbingSearch;
import aima.search.informed.SimulatedAnnealingSearch;

//Author: Zhensheng Chen, Ginesta Basart

public class Main {

	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch(Exception e) {
            e.printStackTrace();
        }
		
		RedSensor rd = new RedSensor(4, 1234, 100, 4321);
		DefinicionEstado de = new DefinicionEstado(rd);
		GeneradorSolucionInicial gsi = new GeneradorSolucionInicial(de);
		gsi.generaSolucionInicial2(de);
		
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
		Heuristica3 h3 = new Heuristica3();
		System.out.println("Ratio: "+h3.getHeuristicValue(de));
	}
	
	/*
	public static void main(String[] args){
		RedSensor rd = new RedSensor(4, 1234, 10, 4321);
        DefinicionEstado e = new DefinicionEstado(rd);
        GeneradorSolucionInicial gsi = new GeneradorSolucionInicial(e);
		gsi.generaSolucionInicial2(e); //<----------------------------Cambiar para otra generacion inicial(2)!!!!!!
		
        RedHillClimbingSearch(e);
        EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RedSensorJFrame frame = new RedSensorJFrame(e); //<-----------------esta linia pinta el grafo :3
					frame.setTitle("Solucion Hill Climbing");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
        
        DefinicionEstado e2 = new DefinicionEstado(rd);
		gsi.generaSolucionInicial2(e2); //<----------------------------Cambiar para otra generacion inicial(2)!!!!!!
        RedSimulatedAnnealingSearch(e2);
        EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RedSensorJFrame frame = new RedSensorJFrame(e2); //<-----------------esta linia pinta el grafo :3
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
            Problem problem =  new Problem(e,new GeneradorSucesoresHillClimbing(), new RedGoalTest(),new Heuristica2());
            //<----------------------------Cambiar ultimo parametro para otro heuristico(3)!!!!!!
            Search search =  new HillClimbingSearch();
            SearchAgent agent = new SearchAgent(problem,search);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private static void RedSimulatedAnnealingSearch(DefinicionEstado e) {
        
        try {
        	Problem problem =  new Problem(e,new GeneradorSucesoresSimulatedAnnealing(), new RedGoalTest(),new Heuristica1());
        	//<----------------------------Cambiar ultimo parametro para otro heuristico(3)!!!!!!
        	SimulatedAnnealingSearch search =  new SimulatedAnnealingSearch(20,100,5,0.001);
            //search.traceOn();
            SearchAgent agent = new SearchAgent(problem,search);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
    */
    
}
