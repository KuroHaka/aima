package probRedSensors;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Component;
import javax.swing.JTextField;

public class RSJFrame extends JFrame {

	private JPanel contentPane;
	private GraphChartPanel redSensores;
	private int ncent = 0;
	private int cseed = 0;
	private int nsens = 0;
	private int sseed = 0;
	private int ini = 1;
	private int heu = 1;
	private int algo = 1;
	private JTextField tsCentros;
	private JTextField tsSensores;
	private JRadioButton h1;
	private JRadioButton h3;
	private JRadioButton h2;
	private JRadioButton s1;
	private JRadioButton s2;
	private JRadioButton hill;
	private JRadioButton simulated;
	private JSlider sSensor;
	private JSlider scentre;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					RSJFrame frame = new RSJFrame();
					frame.setVisible(true);
					frame.pack();
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RSJFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel graf = new JPanel();
		
		contentPane.add(graf);
		
		JPanel top = new JPanel();
		contentPane.add(top, BorderLayout.NORTH);
		top.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		s1 = new JRadioButton("Soluci\u00F3n Inicial 1");
		s1.setSelected(true);
		top.add(s1);
		s1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				actualizar();
				mostrarGrafica(graf);
			}
		});
		
		s2 = new JRadioButton("Soluci\u00F3n Inicial 2");
		top.add(s2);
		s2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				actualizar();
				mostrarGrafica(graf);
			}
		});
		
		ButtonGroup bg1 = new ButtonGroup();
		bg1.add(s2);
		bg1.add(s1);
		
		JLabel none_1 = new JLabel("|");
		none_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		top.add(none_1);
		
		h1 = new JRadioButton("Heur\u00EDstica 1");
		h1.setSelected(true);
		top.add(h1);
		h1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				actualizar();
				mostrarGrafica(graf);
			}
		});
		
		h2 = new JRadioButton("Heur\u00EDstica 2");
		top.add(h2);
		h2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				actualizar();
				mostrarGrafica(graf);
			}
		});
		h3 = new JRadioButton("Heur\u00EDstica 3");
		top.add(h3);
		h3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				actualizar();
				mostrarGrafica(graf);
			}
		});
		
		ButtonGroup bg2 = new ButtonGroup();
		bg2.add(h3);
		bg2.add(h2);
		bg2.add(h1);
		
		JLabel none = new JLabel("|");
		none.setFont(new Font("Tahoma", Font.PLAIN, 15));
		top.add(none);
		
		simulated = new JRadioButton("Simulated Annealing");
		simulated.setSelected(true);
		top.add(simulated);
		simulated.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				actualizar();
				mostrarGrafica(graf);
			}
		});
		
		hill = new JRadioButton("Hill Climbing");
		top.add(hill);
		hill.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				actualizar();
				mostrarGrafica(graf);
			}
		});
		
		ButtonGroup bg3 = new ButtonGroup();
		bg3.add(simulated);
		bg3.add(hill);
		
		JPanel bot = new JPanel();
		contentPane.add(bot, BorderLayout.SOUTH);
		bot.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblSemillaCentroDatos = new JLabel("Semilla Centro Datos:");
		lblSemillaCentroDatos.setHorizontalAlignment(SwingConstants.LEFT);
		lblSemillaCentroDatos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		bot.add(lblSemillaCentroDatos);
		
		tsCentros = new JTextField();
		tsCentros.setHorizontalAlignment(SwingConstants.CENTER);
		tsCentros.setText("1234");
		bot.add(tsCentros);
		tsCentros.setColumns(10);
		tsCentros.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				actualizar();
				mostrarGrafica(graf);
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				actualizar();
				mostrarGrafica(graf);
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		JButton ejecutar = new JButton("EJECUTAR");
		ejecutar.setVerticalAlignment(SwingConstants.BOTTOM);
		bot.add(ejecutar);
		
		tsSensores = new JTextField();
		tsSensores.setHorizontalAlignment(SwingConstants.CENTER);
		tsSensores.setText("4321");
		bot.add(tsSensores);
		tsSensores.setColumns(10);
		tsSensores.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				actualizar();
				mostrarGrafica(graf);
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				actualizar();
				mostrarGrafica(graf);
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		JLabel lblsemillaSensores = new JLabel(":Semilla Sensores       ");
		lblsemillaSensores.setHorizontalAlignment(SwingConstants.LEFT);
		lblsemillaSensores.setFont(new Font("Tahoma", Font.PLAIN, 12));
		bot.add(lblsemillaSensores);
		
		JPanel left = new JPanel();
		contentPane.add(left, BorderLayout.WEST);
		left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
		
		JLabel nCentres = new JLabel("Centro Datos");
		nCentres.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nCentres.setHorizontalAlignment(SwingConstants.LEFT);
		left.add(nCentres);
		
		JPanel panel = new JPanel();
		left.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel vcentres = new JLabel("     1");
		vcentres.setFont(new Font("Tahoma", Font.PLAIN, 15));
		vcentres.setHorizontalAlignment(SwingConstants.CENTER);
		left.add(vcentres);
		
		scentre = new JSlider();
		scentre.setMaximum(200);
		scentre.setValue(1);
		scentre.setOrientation(SwingConstants.VERTICAL);
		scentre.setMinimum(1);
		scentre.setBorder(null);
		scentre.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				vcentres.setText("     "+scentre.getValue());
				actualizar();
				mostrarGrafica(graf);
			}
		});
		panel.add(scentre);
		
		JPanel right = new JPanel();
		contentPane.add(right, BorderLayout.EAST);
		right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
		
		JLabel nSensors = new JLabel(" Sensores   ");
		nSensors.setHorizontalAlignment(SwingConstants.LEFT);
		nSensors.setFont(new Font("Tahoma", Font.PLAIN, 14));
		right.add(nSensors);
		
		JPanel panel_1 = new JPanel();
		right.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel vSensors = new JLabel("    1");
		vSensors.setHorizontalAlignment(SwingConstants.CENTER);
		vSensors.setFont(new Font("Tahoma", Font.PLAIN, 15));
		right.add(vSensors);
		
		sSensor = new JSlider();
		sSensor.setMaximum(500);
		sSensor.setValue(1);
		sSensor.setPaintLabels(true);
		sSensor.setOrientation(SwingConstants.VERTICAL);
		sSensor.setMinimum(1);
		sSensor.setBorder(null);
		sSensor.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				vSensors.setText("   "+sSensor.getValue());
				actualizar();
				mostrarGrafica(graf);
			}
		});
		panel_1.add(sSensor);
		
		ejecutar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				actualizar();
				mostrarGrafica(graf);
			}
			
		});
		mostrarGrafica(graf);
	}
	
	private void actualizar() {
		if(this.h1.hasFocus())
			this.heu = 1;
		else if(this.h2.isSelected())
			this.heu = 2;
		else
			this.heu = 3;
		if(this.hill.isSelected())
			this.algo = 2;
		else
			this.algo = 1;
		if(this.s1.isSelected())
			this.ini = 1;
		else
			this.ini = 2;
		this.ncent = this.scentre.getValue();
		this.nsens = this.sSensor.getValue();
		this.sseed = Integer.parseInt(this.tsSensores.getText());
		this.cseed = Integer.parseInt(this.tsCentros.getText());
	}
	
	
	private void mostrarGrafica(JPanel p) {
		p.removeAll();
		RedSensor rd = new RedSensor(this.ncent, this.cseed, this.nsens, this.sseed);
		DefinicionEstado de = new DefinicionEstado(rd);
		GeneradorSolucionInicial gsi = new GeneradorSolucionInicial(de);
		if(ini == 1)
			gsi.generaSolucionInicial1(de);
		else
			gsi.generaSolucionInicial2(de);
		GraphChartPanel gcp = new GraphChartPanel(de);
		p.add(gcp);
		p.setVisible(false);
		p.setVisible(true);
	}

}
