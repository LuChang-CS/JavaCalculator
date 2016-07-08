package Framework;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Framework.CalculatorActionListener.AddFuncActionListener;
import Framework.CalculatorActionListener.AddFuncKeyListener;
import Framework.CalculatorActionListener.FuncImgActionListener;

public class Draw2DPanel extends JPanel {
	
	int WIDTH = 0;
	int HEIGHT = 0;
	
	CoordinatePanel drawPanel;
	
	JPanel funcPanel;
	JLabel funcLabel;
	JTextField funcField;
	JButton addFuncButton;
	JButton exportImageButton;
	//JButton draw3DButton;
	JLabel xPosLabel;
	JLabel yPosLabel;

	CalculatorForm calculatorForm;
	
	public Draw2DPanel(CalculatorForm calculatorForm) {
		 setReference(calculatorForm);
	}
	
	public void display() {
		init();
		setStyle();
		addElements();
		addActionListener();
	}
	
	public void setReference(CalculatorForm calculatorForm) {
		this.calculatorForm = calculatorForm;
	}
	
	public void setPanelSize(int width, int height) {
		WIDTH = width;
		HEIGHT = height;
	}
	
	private void init() {
		funcPanel = new JPanel();
		funcLabel = new JLabel("f(x)=");
		funcField = new JTextField();
		addFuncButton = new JButton("Add");
		exportImageButton = new JButton("Export Image");
		//draw3DButton = new JButton("Draw 3D");
		
		xPosLabel = new JLabel("x=");
		yPosLabel = new JLabel("y=");
		
		drawPanel = new CoordinatePanel(xPosLabel, yPosLabel);
	}
	
	private void setStyle() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setLayout(new BorderLayout());
		
		drawPanel.setPreferredSize(new Dimension(WIDTH, 500));
		drawPanel.setBackground(Color.WHITE);
		drawPanel.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		
		funcPanel.setPreferredSize(new Dimension(WIDTH, 80));
		funcPanel.setLayout(null);
		funcPanel.setFont(new Font("Microsoft Yahei", Font.PLAIN, 14));
		
		funcLabel.setBounds(10, 10, 35, 25);
		funcLabel.setFont(new Font("Microsoft Yahei", Font.PLAIN, 14));
		funcField.setBounds(45, 10, 255, 25);
		funcField.setFont(new Font("Microsoft Yahei", Font.PLAIN, 14));
		addFuncButton.setBounds(305, 10, 80, 25);
		addFuncButton.setFont(new Font("Microsoft Yahei", Font.PLAIN, 14));
		addFuncButton.setFocusPainted(false);
		exportImageButton.setBounds(390, 10, 140, 25);
		exportImageButton.setFont(new Font("Microsoft Yahei", Font.PLAIN, 14));
		exportImageButton.setFocusPainted(false);
		//draw3DButton.setBounds(535, 10, 100, 25);
		//draw3DButton.setFont(new Font("Microsoft Yahei", Font.PLAIN, 14));
		//draw3DButton.setFocusPainted(false);
		
		xPosLabel.setBounds(10, 45, 100, 25);
		xPosLabel.setFont(new Font("Microsoft Yahei", Font.PLAIN, 12));
		yPosLabel.setBounds(115, 45, 100, 25);
		yPosLabel.setFont(new Font("Microsoft Yahei", Font.PLAIN, 12));
	}
	
	private void addElements() {
		funcPanel.add(funcLabel);
		funcPanel.add(funcField);
		funcPanel.add(addFuncButton);
		funcPanel.add(exportImageButton);
		//funcPanel.add(draw3DButton);
		funcPanel.add(xPosLabel);
		funcPanel.add(yPosLabel);
		
		add(drawPanel, BorderLayout.CENTER);
		add(funcPanel, BorderLayout.SOUTH);
	}
	
	private void addActionListener() {
		funcField.addKeyListener((KeyListener) new AddFuncKeyListener(drawPanel, funcField));
		addFuncButton.addActionListener(new AddFuncActionListener(drawPanel, funcField));
		exportImageButton.addActionListener(new FuncImgActionListener(drawPanel));
	}
}
