package btl;

import javax.swing.JFrame;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.*;
import java.io.*;

public class Main1 extends javax.swing.JFrame{
	public static final long serialVersionUID = 1L;
	Simulation sim;
	Simulation2DCircle sim2DC;

	Image initImg;
	Image finalImg;
	Graphics initDiag, finalDiag;

	private JFrame frame;
	private JTextField txtSoSensor;
	private JTextField textField;
	private JPanel panel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main1 window = new Main1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main1() {
		initialize();
		initComponents();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initComponents() {
		ImagePanel initialState = new ImagePanel();

		initImg = createImage(400, 400);

		initialState.setImage(initImg);

		initDiag = initImg.getGraphics();
		
		initialState.setBackground(new Color(255, 255, 255));
		
		panel_1.add(initialState);
		
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.ORANGE);
		panel.setBounds(0, 0, 688, 138);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("nut 1");
		btnNewButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jButton4MouseClicked(evt);
			}
		});
		btnNewButton.setBounds(32, 11, 89, 23);
		panel.add(btnNewButton);
		
		txtSoSensor = new JTextField();
		txtSoSensor.setText("so sensor");
		txtSoSensor.setBounds(189, 12, 57, 20);
		panel.add(txtSoSensor);
		txtSoSensor.setColumns(10);
		
		textField = new JTextField();
		textField.setBounds(256, 12, 96, 20);
		panel.add(textField);
		textField.setColumns(10);
		
        panel_1 = new JPanel();
		panel_1.setBounds(10, 146, 402, 408);
		frame.getContentPane().add(panel_1);
		pack();
		
	}
	private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jButton4MouseClicked
		int scale = 400 ;
		String numStr = textField.getText();
		int numSens = Integer.parseInt(numStr);
		int sensR = 400;
		sim2DC = new Simulation2DCircle(scale / 2, numSens, sensR, createImage(scale, scale), this);
		sim = sim2DC;
		sim2DC.draw(initDiag);
		sim2DC.logSim();
		this.repaint();
	}// GEN-LAST:event_jButton4MouseClicked
	public static void writeFile(String s) {
		try {
			// Create file
			FileWriter fstream = new FileWriter("log.txt", true);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(s);
			out.newLine();
			// Close the output stream
			out.close();
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
	}
}