package probRedSensors;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import aima.search.framework.Problem;
import aima.search.framework.Search;
import aima.search.framework.SearchAgent;
import aima.search.informed.HillClimbingSearch;
import aima.search.informed.SimulatedAnnealingSearch;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
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
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import java.awt.SystemColor;
import javax.swing.Box;

public class RSJFrame extends JFrame {

	private JPanel contentPane;
	private GraphChartPanel redSensores;
	private int ncent = 1;
	private int cseed = 1234;
	private int nsens = 1;
	private int sseed = 4321;
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
	private JPanel graf;
	private DefinicionEstado e;
	private JTextField iteraciones;
	private JTextField temperatura;
	private JTextField k;
	private JTextField lambda;
	private JTextArea txtData;
	
	private Heuristica3 heu3;
	

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
		JPanel background = new JPanel();
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(background);
		background.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		background.add(contentPane);
		graf = new JPanel();
	    
	    
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
				mostrarGrafica();
			}
		});
		
		s2 = new JRadioButton("Soluci\u00F3n Inicial 2");
		top.add(s2);
		s2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				actualizar();
				mostrarGrafica();
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
				mostrarGrafica();
			}
		});
		
		h2 = new JRadioButton("Heur\u00EDstica 2");
		top.add(h2);
		h2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				actualizar();
				mostrarGrafica();
			}
		});
		h3 = new JRadioButton("Heur\u00EDstica 3");
		top.add(h3);
		h3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				actualizar();
				mostrarGrafica();
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
				mostrarGrafica();
			}
		});
		
		hill = new JRadioButton("Hill Climbing");
		top.add(hill);
		hill.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				actualizar();
				mostrarGrafica();
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
				mostrarGrafica();
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				actualizar();
				mostrarGrafica();
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
				mostrarGrafica();
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				actualizar();
				mostrarGrafica();
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
		scentre.setValue(1);
		scentre.setOrientation(SwingConstants.VERTICAL);
		scentre.setMinimum(1);
		scentre.setBorder(null);
		scentre.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				vcentres.setText("     "+scentre.getValue());
				actualizar();
				mostrarGrafica();
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
		sSensor.setMaximum(200);
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
				mostrarGrafica();
			}
		});
		panel_1.add(sSensor);
		
		JPanel data = new JPanel();
		background.add(data);
		data.setLayout(new BoxLayout(data, BoxLayout.Y_AXIS));
		
		JPanel panel_2 = new JPanel();
		data.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel labelit = new JLabel("    Iteraciones:");
		labelit.setHorizontalAlignment(SwingConstants.LEFT);
		panel_2.add(labelit);
		
		iteraciones = new JTextField();
		iteraciones.setText("450");
		iteraciones.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(iteraciones);
		iteraciones.setColumns(10);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		panel_2.add(horizontalStrut);
		
		JPanel panel_3 = new JPanel();
		data.add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel_1 = new JLabel("Temperatura:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		panel_3.add(lblNewLabel_1);
		
		temperatura = new JTextField();
		temperatura.setText("61");
		temperatura.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(temperatura);
		temperatura.setColumns(10);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		panel_3.add(horizontalStrut_1);
		
		JPanel panel_4 = new JPanel();
		data.add(panel_4);
		panel_4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel labelk = new JLabel("                      K:");
		labelk.setHorizontalAlignment(SwingConstants.LEFT);
		panel_4.add(labelk);
		
		k = new JTextField();
		k.setText("24");
		k.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(k);
		k.setColumns(10);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		panel_4.add(horizontalStrut_2);
		
		JPanel panel_5 = new JPanel();
		data.add(panel_5);
		panel_5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel labellamb = new JLabel("          Lambda:");
		labellamb.setHorizontalAlignment(SwingConstants.LEFT);
		panel_5.add(labellamb);
		
		lambda = new JTextField();
		lambda.setText("0.1");
		lambda.setHorizontalAlignment(SwingConstants.CENTER);
		panel_5.add(lambda);
		lambda.setColumns(10);
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		panel_5.add(horizontalStrut_3);
		
		Component verticalStrut = Box.createVerticalStrut(300);
		data.add(verticalStrut);
		
		Component verticalStrut_1 = Box.createVerticalStrut(300);
		data.add(verticalStrut_1);
		
		txtData = new JTextArea();
		txtData.setFont(new Font("Segoe UI", Font.BOLD, 13));
		txtData.setText("Coste transmisi\u00F3n:                              \r\nAprovechamiento total:                     \r\nPerdida total de datos:                        \r\nRatio:                                                     ");
		txtData.setTabSize(10);
		txtData.setRows(5);
		txtData.setBackground(SystemColor.menu);
		data.add(txtData);
		
		ejecutar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e1) {
				// TODO Auto-generated method stub
				ejecutar();
			}
			
		});
		mostrarGrafica();
		
		sSensor.setValue(100);
		scentre.setValue(4);
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
	
	private void mostrarGrafica() {
		graf.removeAll();
		RedSensor rd = new RedSensor(this.ncent, this.cseed, this.nsens, this.sseed);
		e = new DefinicionEstado(rd);
		GeneradorSolucionInicial gsi = new GeneradorSolucionInicial(e);
		if(ini == 1)
			gsi.generaSolucionInicial1(e);
		else
			gsi.generaSolucionInicial2(e);
		
		GraphChartPanel gcp = new GraphChartPanel(e);
		graf.add(gcp);
		graf.setVisible(false);
		graf.setVisible(true);
		printData();
	}
	
	private void RedSimulatedAnnealingSearch() {   
		Problem problem = null;
		switch(heu) {
		case 1: 
			problem  =  new Problem(e,new GeneradorSucesoresSimulatedAnnealing(), new RedGoalTest(),new Heuristica1());
			break;
		case 2: 
			problem =  new Problem(e,new GeneradorSucesoresSimulatedAnnealing(), new RedGoalTest(),new Heuristica2());
			break;
		default: 
			problem =  new Problem(e,new GeneradorSucesoresSimulatedAnnealing(), new RedGoalTest(),new Heuristica3());
			break;
		}
    	SimulatedAnnealingSearch search =  new SimulatedAnnealingSearch(Integer.parseInt(iteraciones.getText()),
    																	Integer.parseInt(temperatura.getText()),
    																	Integer.parseInt(k.getText()),
    																	Double.parseDouble(lambda.getText()));
        //search.traceOn();
        try {
			SearchAgent agent = new SearchAgent(problem,search);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
        e = (DefinicionEstado) search.getGoalState();   
    }
	
	private void RedHillClimbingSearch() {
		Problem problem = null;
		switch(heu) {
		case 1: 
			problem  =  new Problem(e,new GeneradorSucesoresHillClimbing(), new RedGoalTest(),new Heuristica1());
		break;
		case 2: 
			problem =  new Problem(e,new GeneradorSucesoresHillClimbing(), new RedGoalTest(),new Heuristica2());
		break;
		default: 
			problem =  new Problem(e,new GeneradorSucesoresHillClimbing(), new RedGoalTest(),new Heuristica3());
		break;
		}
        Search search =  new HillClimbingSearch();
        try {
			SearchAgent agent = new SearchAgent(problem,search);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
        e = (DefinicionEstado) search.getGoalState();
    }
	
	private void ejecutar() {
		actualizar();
		graf.removeAll();
		switch (algo) {
		case 1:
			RedSimulatedAnnealingSearch();
			break;
		default:
			RedHillClimbingSearch();
			break;
		}
		GraphChartPanel gcp = new GraphChartPanel(e);
		graf.add(gcp);
		graf.setVisible(false);
		graf.setVisible(true);
		printData();
	}
	
	private void printData() {
		Heuristica1 h1 = new Heuristica1();
		Heuristica2 h2 = new Heuristica2();
		heu3 = new Heuristica3();
		txtData.setText("Coste transmisi\u00F3n:  "+h1.getHeuristicValue(e)
						+"\r\nAprovechamiento total:  "+(e.getRedSensor().maxCapacidad()-h2.getHeuristicValue(e))
						+"\r\nPerdida total de datos:  "+h2.getHeuristicValue(e)
						+"\r\nRatio:  "+heu3.getHeuristicValue(e));
	}
	

}
