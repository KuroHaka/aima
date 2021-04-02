package probRedSensors;

import java.awt.BorderLayout;
import java.awt.EventQueue;

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
		redSensores = new GraphChartPanel(de);
		this.add(redSensores);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

}
