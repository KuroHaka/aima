package probRedSensors;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.general.Dataset;
import org.jfree.data.general.DatasetChangeListener;
import org.jfree.data.general.DatasetGroup;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Tablero extends JFrame {

    //Initialize the values of the array
    

	private JPanel contentPane;
	private int aristas;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public Tablero(DefinicionEstado d) {
		aristas= 0;
		JPanel graph = new JPanel();
		getContentPane().add(graph, BorderLayout.CENTER);
	    
	    
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 900);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel plot = new JPanel();
		contentPane.add(plot, BorderLayout.CENTER);
		XYSeriesCollection dataset = getDatos(d);
		JFreeChart chart = ChartFactory.createXYLineChart("Sensores","","",dataset, PlotOrientation.HORIZONTAL, false, false, false);
		customizeChart(chart);
		
		ChartPanel panel = new ChartPanel(chart);
		panel.setMouseWheelEnabled(true);
		plot.setLayout(new java.awt.BorderLayout());
		plot.add(panel);
		plot.validate();
		plot.setVisible(true);
	}
	
	private XYSeriesCollection getDatos(DefinicionEstado d){
		XYSeriesCollection dataset = new XYSeriesCollection();
		for(int y = 0; y < d.size(); y++) {
			for(int x = y+1; x < d.size(); x++) {
				if(d.getConexion(x, y)==1) {
					aristas++;
					XYSeries series = new XYSeries("");
					if(x < d.numSensores()) 
						series.add(d.getRedSensor().getSensor().get(x).getCoordX(),d.getRedSensor().getSensor().get(x).getCoordY());
					else 
						series.add(d.getRedSensor().getCentros().get(x-d.numSensores()).getCoordX(),d.getRedSensor().getCentros().get(x-d.numSensores()).getCoordY());
					if(y < d.numSensores()) 
						series.add(d.getRedSensor().getSensor().get(y).getCoordX(),d.getRedSensor().getSensor().get(y).getCoordY());
					else 
						series.add(d.getRedSensor().getCentros().get(y-d.numSensores()).getCoordX(),d.getRedSensor().getCentros().get(y-d.numSensores()).getCoordY());
					dataset.addSeries(series);
					
				}
			}
		}
		return dataset;
	}
	private void customizeChart(JFreeChart chart) {   // here we make some customization
	    XYPlot plot = chart.getXYPlot();
	    XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

	    // sets paint color for each series
	    for(int i = 0; i<aristas; i++) 
	    	renderer.setSeriesPaint(i,Color.black);
	    plot.setRenderer(renderer);
	    
	}

}
