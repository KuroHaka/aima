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

public class GraphChartPanel extends JPanel{
	DefinicionEstado d;
	
	GraphChartPanel(DefinicionEstado e){
		this.setPreferredSize(new Dimension(500,500));
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
					
					Random rand = new Random();
					float r = rand.nextFloat();
					float g1 = rand.nextFloat();
					float b = rand.nextFloat();
					Color color = new Color(r,g1,b);
					g2.setPaint(color);
					g2.drawLine(series.first.first*5, series.first.second*5, series.second.first*5, series.second.second*5);
					
				}
			}
		}
		Iterator<Sensor> it = d.getRedSensor().getSensor().iterator();
		g2.setPaint(Color.BLACK);
		while(it.hasNext()){
			Sensor s = it.next();
			g2.fillOval(s.getCoordX()*5-5, s.getCoordY()*5-5, 10, 10);
		}
		Iterator<Centro> it2 = d.getRedSensor().getCentros().iterator();
		g2.setPaint(Color.RED);
		while(it2.hasNext()){
			Centro c = it2.next();
			g2.fillOval(c.getCoordX()*5-10, c.getCoordY()*5-10, 20, 20);
		}
	}

}
