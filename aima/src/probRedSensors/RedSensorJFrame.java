package probRedSensors;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

//Author: Zhensheng Chen
public class RedSensorJFrame extends JFrame {
	
	private GraphChartPanel redSensores;

	/**
	 * Create the frame.
	 */
	public RedSensorJFrame(DefinicionEstado de) {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel fondo = new JPanel(new BorderLayout());
		redSensores = new GraphChartPanel(de);
		fondo.add(redSensores,BorderLayout.CENTER);
		JPanel barraInferior = new JPanel(new FlowLayout());
		Button ejecutar = new Button("Ejecutar");
		ejecutar.setPreferredSize(new Dimension(200,70));
		barraInferior.add(ejecutar);
		fondo.add(barraInferior,BorderLayout.SOUTH);
		this.add(fondo);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

}
