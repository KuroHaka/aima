package probRedSensors;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JPanel;

import org.jfree.data.xy.XYSeries;

import IA.Red.Centro;
import IA.Red.Sensor;


//Author: Zhensheng Chen
public class GraphChartPanel extends JPanel{
	DefinicionEstado d;
	final int size = 8;
	final int offset = 15;
	final int windowSize = 820;
	final int centrosSize = 20;
	final int sensoresSize = 20;
	
	GraphChartPanel(DefinicionEstado e){
		this.setPreferredSize(new Dimension(windowSize,windowSize));
		this.d = e;
	}
	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		for(int y = 0; y < d.size(); y++) {
			for(int x = y+1; x < d.size(); x++) {
				if(d.getConexion(x, y)==1) {
					Pair<Pair<Integer,Integer>,Pair<Integer,Integer>> series = new Pair<>(null,null);
					if(x < d.numSensores()) 
						series.first = new Pair<>(d.getRedSensor().getSensor().get(x).getCoordX(),d.getRedSensor().getSensor().get(x).getCoordY());
					else 
						series.first = new Pair<>(d.getRedSensor().getCentros().get(x-d.numSensores()).getCoordX(),d.getRedSensor().getCentros().get(x-d.numSensores()).getCoordY());
					if(y < d.numSensores()) 
						series.second = new Pair<>(d.getRedSensor().getSensor().get(y).getCoordX(),d.getRedSensor().getSensor().get(y).getCoordY());
					else 
						series.second = new Pair<>(d.getRedSensor().getCentros().get(y-d.numSensores()).getCoordX(),d.getRedSensor().getCentros().get(y-d.numSensores()).getCoordY());
					
					g2.drawLine(offset+series.first.first*size, offset+series.first.second*size, offset+series.second.first*size, offset+series.second.second*size);
				}
			}
		}
		Iterator<Sensor> it = d.getRedSensor().getSensor().iterator();
		
		while(it.hasNext()){
			Sensor s = it.next();
			int x = offset+s.getCoordX()*size-centrosSize/2;
			int y = offset+s.getCoordY()*size-centrosSize/2;
			g2.setPaint(Color.BLACK);
			g2.fillOval(x, y, centrosSize, centrosSize);
			g2.setPaint(Color.WHITE);
			g2.drawString(Integer.toString((int)s.getCapacidad()), x+7, y+15);
		}
		Iterator<Centro> it2 = d.getRedSensor().getCentros().iterator();
		g2.setPaint(Color.RED);
		while(it2.hasNext()){
			Centro c = it2.next();
			g2.fillOval(offset+c.getCoordX()*size-sensoresSize/2, offset+c.getCoordY()*size-sensoresSize/2, sensoresSize, sensoresSize);
		}
	}

}
