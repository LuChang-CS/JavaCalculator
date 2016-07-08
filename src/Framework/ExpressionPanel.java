package Framework;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import Framework.CalculatorActionListener.AcButtonActionListener;
import Framework.CalculatorActionListener.CalcButtonActionListener;
import Framework.CalculatorActionListener.DelButtonActionListener;
import Framework.CalculatorActionListener.DrawButtonActionListener;
import Framework.CalculatorActionListener.EnterCalcKeyListener;
import Framework.CalculatorActionListener.HistoryButtonActionListener;
import Framework.CalculatorActionListener.MemButtonActionListener;
import Framework.CalculatorActionListener.SolveButtonActionListener;

public class ExpressionPanel extends JPanel {
	
	int WIDTH = 0;
	int HEIGHT = 0;
	
	CalculatorForm calculatorForm;
	
	JPanel fieldPanel;
	JPanel buttonPanel;
	
	CalculatorTextField expressionField;
	CalculatorTextField resultField;
	
	CalculatorButton historyButton;		// history
	CalculatorButton drawButton;		// draw
	CalculatorButton acButton;			// all clear
	CalculatorButton delButton;			// delete
	CalculatorButton[] memoryButtons;	// memory clear, memory read, memory plus, memory minus
	CalculatorButton[] funcButtons;		// sin, cos, tan, ln, abs
	CalculatorButton[] numberButtons;	// 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, '.', e, π, answer
	CalculatorButton[] operatorButtons;	// +, -, ×, ÷, ±, ^, √, 1/x, x², x³, (, )
	CalculatorButton solveButton;		// =
	CalculatorButton randomButton;		// random
	
	GridLayout fieldPanelLayout;
	GridBagLayout buttonPanelLayout;
	GridBagConstraints buttonPanelBagConstraints;
	
	public ExpressionPanel(CalculatorForm calculatorForm) {
		this.calculatorForm = calculatorForm;
	}
	
	public void display() {
		init();
		setStyle();
		addElements();
		addButtonActionListener();
	}
	
	public void setPanelSize(int width, int height) {
		WIDTH = width;
		HEIGHT = height;
	}
	
	private void init() {
		
		fieldPanel = new JPanel();
		
		fieldPanelLayout = new GridLayout(2, 1);
		
		expressionField = new CalculatorTextField();
		resultField = new CalculatorTextField();
		
		buttonPanel = new JPanel();
		
		buttonPanelLayout = new GridBagLayout();
		
		initHisoryButton();
		initDrawButton();
		initAcButton();
		initDelButton();
		initMenmoryButtons();
		initFuncButtons();
		initNumberButtons();
		initOperatorButtons();
		initSolveButton();
		initRandomButton();
		
		buttonPanelBagConstraints = new GridBagConstraints();
		
	}
	
	private void initButton(CalculatorButton button, String command, String text) {
		button.setCommand(command);
		button.setText(text);
	}
	
	private void initHisoryButton() {
		historyButton = new CalculatorButton();
		initButton(historyButton, "history", "History");
	}
	
	private void initDrawButton() {
		drawButton = new CalculatorButton();
		initButton(drawButton, "draw", "Draw");
	}
	
	private void initAcButton() {
		acButton = new CalculatorButton();
		initButton(acButton, "AC", "AC");
	}
	
	private void initDelButton() {
		delButton = new CalculatorButton();
		initButton(delButton, "delete", "Del");
	}
	
	private void initSolveButton() {
		solveButton = new CalculatorButton();
		initButton(solveButton, "solve", "=");
	}
	
	private void initRandomButton() {
		randomButton = new CalculatorButton();
		initButton(randomButton, "Ran#", "Rand");
	}
	
	private void initMenmoryButtons() {
		memoryButtons = new CalculatorButton[4];
		for (int i = 0; i < 4; ++i) {
			memoryButtons[i] = new CalculatorButton();
		}
		memoryButtons[0].setCommand("M");
		memoryButtons[0].setText("MR");
		memoryButtons[1].setCommand("MC");
		memoryButtons[1].setText("MC");
		memoryButtons[2].setCommand("M+");
		memoryButtons[2].setText("M+");
		memoryButtons[3].setCommand("M-");
		memoryButtons[3].setText("M-");
	}
	
	private void initFuncButtons() {
		funcButtons = new CalculatorButton[5];
		for (int i = 0; i < 5; ++i) {
			funcButtons[i] = new CalculatorButton();
		}
		funcButtons[0].setCommand("sin");
		funcButtons[0].setText("sin");
		funcButtons[1].setCommand("cos");
		funcButtons[1].setText("cos");
		funcButtons[2].setCommand("tan");
		funcButtons[2].setText("tan");
		funcButtons[3].setCommand("ln");
		funcButtons[3].setText("ln");
		funcButtons[4].setCommand("abs");
		funcButtons[4].setText("abs");
	}
	
	private void initNumberButtons() {
		numberButtons = new CalculatorButton[14];
		for (int i = 0; i < 10; ++i) {
			numberButtons[i] = new CalculatorButton();
			numberButtons[i].setCommand(Integer.toString(i));
			numberButtons[i].setText(Integer.toString(i));
		}
		numberButtons[10] = new CalculatorButton();
		numberButtons[10].setCommand(".");
		numberButtons[10].setText(".");
		numberButtons[11] = new CalculatorButton();
		numberButtons[11].setCommand("e");
		numberButtons[11].setText("e");
		numberButtons[12] = new CalculatorButton();
		numberButtons[12].setCommand("π");
		numberButtons[12].setText("π");
		numberButtons[13] = new CalculatorButton();
		numberButtons[13].setCommand("Ans");
		numberButtons[13].setText("Ans");
	}
	
	private void initOperatorButtons() {
		operatorButtons = new CalculatorButton[12];
		for (int i = 0; i < 12; ++i) {
			operatorButtons[i] = new CalculatorButton();
		}
		operatorButtons[0].setCommand("^(-1)");
		operatorButtons[0].setText("1/x");
		operatorButtons[1].setCommand("^2");
		operatorButtons[1].setText("x²");
		operatorButtons[2].setCommand("^3");
		operatorButtons[2].setText("x³");
		operatorButtons[3].setCommand("(");
		operatorButtons[3].setText("(");
		operatorButtons[4].setCommand(")");
		operatorButtons[4].setText(")");
		operatorButtons[5].setCommand("√(");
		operatorButtons[5].setText("√");
		operatorButtons[6].setCommand("^");
		operatorButtons[6].setText("^");
		operatorButtons[7].setCommand("-");
		operatorButtons[7].setText("±");
		operatorButtons[8].setCommand("+");
		operatorButtons[8].setText("+");
		operatorButtons[9].setCommand("-");
		operatorButtons[9].setText("-");
		operatorButtons[10].setCommand("×");
		operatorButtons[10].setText("×");
		operatorButtons[11].setCommand("÷");
		operatorButtons[11].setText("÷");
	}
	
	private void addElements() {
		add(fieldPanel, BorderLayout.CENTER);
		
		fieldPanel.add(expressionField);
		fieldPanel.add(resultField);
		
		add(buttonPanel, BorderLayout.SOUTH);
		
		buttonPanel.add(historyButton);
		buttonPanel.add(drawButton);
		buttonPanel.add(memoryButtons[CalculatorButton.MEMORY_READ]);
		buttonPanel.add(memoryButtons[CalculatorButton.MEMORY_CLEAR]);
		buttonPanel.add(memoryButtons[CalculatorButton.MEMORY_PLUS]);
		buttonPanel.add(memoryButtons[CalculatorButton.MEMORY_MINUS]);
		
		buttonPanel.add(operatorButtons[CalculatorButton.OPEN_BRACKET]);
		buttonPanel.add(operatorButtons[CalculatorButton.CLOSE_BRACKET]);
		buttonPanel.add(operatorButtons[CalculatorButton.SQUARE_ROOT]);
		buttonPanel.add(acButton);
		buttonPanel.add(delButton);
		buttonPanel.add(operatorButtons[CalculatorButton.NEGATIVE]);
		buttonPanel.add(operatorButtons[CalculatorButton.PLUS]);
		
		buttonPanel.add(operatorButtons[CalculatorButton.RECIPROCAL]);
		buttonPanel.add(operatorButtons[CalculatorButton.SQUARE]);
		buttonPanel.add(operatorButtons[CalculatorButton.CUBE]);
		buttonPanel.add(numberButtons[CalculatorButton.SEVEN]);
		buttonPanel.add(numberButtons[CalculatorButton.EIGHT]);
		buttonPanel.add(numberButtons[CalculatorButton.NINE]);
		buttonPanel.add(operatorButtons[CalculatorButton.MINUS]);
		
		buttonPanel.add(funcButtons[CalculatorButton.LN]);
		buttonPanel.add(funcButtons[CalculatorButton.ABS]);
		buttonPanel.add(operatorButtons[CalculatorButton.POWER]);
		buttonPanel.add(numberButtons[CalculatorButton.FOUR]);
		buttonPanel.add(numberButtons[CalculatorButton.FIVE]);
		buttonPanel.add(numberButtons[CalculatorButton.SIX]);
		buttonPanel.add(operatorButtons[CalculatorButton.MULTIPLY]);
		
		buttonPanel.add(funcButtons[CalculatorButton.SIN]);
		buttonPanel.add(funcButtons[CalculatorButton.COS]);
		buttonPanel.add(funcButtons[CalculatorButton.TAN]);
		buttonPanel.add(numberButtons[CalculatorButton.ONE]);
		buttonPanel.add(numberButtons[CalculatorButton.TWO]);
		buttonPanel.add(numberButtons[CalculatorButton.THREE]);
		buttonPanel.add(operatorButtons[CalculatorButton.DIVIDE]);
		
		buttonPanel.add(randomButton);
		buttonPanel.add(numberButtons[CalculatorButton.PI]);
		buttonPanel.add(numberButtons[CalculatorButton.E]);
		buttonPanel.add(numberButtons[CalculatorButton.ZERO]);
		buttonPanel.add(numberButtons[CalculatorButton.POINT]);
		buttonPanel.add(numberButtons[CalculatorButton.ANS]);
		buttonPanel.add(solveButton);
	}
	
	private void setStyle() {
		setLayout(new BorderLayout());
		setBackground(Color.BLACK);
		
		fieldPanel.setPreferredSize(new Dimension(WIDTH, 150));
		fieldPanel.setLayout(fieldPanelLayout);
		fieldPanel.setBackground(Color.BLACK);
		
		expressionField.setPreferredSize(new Dimension(WIDTH, 60));
		expressionField.setFont(new Font("Microsoft Yahei light", Font.PLAIN, 40));
		expressionField.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		expressionField.setBackground(new Color(56, 56, 56));
		expressionField.setForeground(new Color(240, 240, 240));
		expressionField.setCaretColor(Color.WHITE);
		expressionField.setSelectionColor(new Color(128, 128, 128));
		
		resultField.setPreferredSize(new Dimension(WIDTH, 40));
		resultField.setFont(new Font("Microsoft Yahei light", Font.PLAIN, 28));
		resultField.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		resultField.setBackground(new Color(56, 56, 56));
		resultField.setForeground(new Color(240, 240, 240));
		resultField.setEditable(false);
		resultField.setSelectionColor(new Color(128, 128, 128));
		
		buttonPanel.setPreferredSize(new Dimension(WIDTH, 248));
		buttonPanel.setLayout(buttonPanelLayout);
		buttonPanel.setBackground(Color.BLACK);
		
		buttonPanelBagConstraints.fill = GridBagConstraints.BOTH;
		buttonPanelBagConstraints.weightx = 0;
		buttonPanelBagConstraints.weighty = 0;
		
		buttonPanelBagConstraints.insets = new Insets(0, 0, 0, 0);
		buttonPanelBagConstraints.gridwidth = 2;
		buttonPanelLayout.setConstraints(historyButton, buttonPanelBagConstraints);
		buttonPanelBagConstraints.insets = new Insets(0, 1, 0, 0);
		buttonPanelBagConstraints.gridwidth = 1;
		buttonPanelLayout.setConstraints(drawButton, buttonPanelBagConstraints);
		buttonPanelLayout.setConstraints(memoryButtons[CalculatorButton.MEMORY_CLEAR], buttonPanelBagConstraints);
		buttonPanelLayout.setConstraints(memoryButtons[CalculatorButton.MEMORY_READ], buttonPanelBagConstraints);
		buttonPanelLayout.setConstraints(memoryButtons[CalculatorButton.MEMORY_PLUS], buttonPanelBagConstraints);
		buttonPanelBagConstraints.gridwidth = 0;
		buttonPanelLayout.setConstraints(memoryButtons[CalculatorButton.MEMORY_MINUS], buttonPanelBagConstraints);
		
		buttonPanelBagConstraints.insets = new Insets(1, 0, 0, 0);
		buttonPanelBagConstraints.gridwidth = 1;
		buttonPanelLayout.setConstraints(operatorButtons[CalculatorButton.OPEN_BRACKET], buttonPanelBagConstraints);
		buttonPanelBagConstraints.insets = new Insets(1, 1, 0, 0);
		buttonPanelLayout.setConstraints(operatorButtons[CalculatorButton.CLOSE_BRACKET], buttonPanelBagConstraints);
		buttonPanelLayout.setConstraints(operatorButtons[CalculatorButton.SQUARE_ROOT], buttonPanelBagConstraints);
		buttonPanelLayout.setConstraints(acButton, buttonPanelBagConstraints);
		buttonPanelLayout.setConstraints(delButton, buttonPanelBagConstraints);
		buttonPanelLayout.setConstraints(operatorButtons[CalculatorButton.NEGATIVE], buttonPanelBagConstraints);
		buttonPanelBagConstraints.gridwidth = 0;
		buttonPanelLayout.setConstraints(operatorButtons[CalculatorButton.PLUS], buttonPanelBagConstraints);

		buttonPanelBagConstraints.insets = new Insets(1, 0, 0, 0);
		buttonPanelBagConstraints.gridwidth = 1;
		buttonPanelLayout.setConstraints(operatorButtons[CalculatorButton.RECIPROCAL], buttonPanelBagConstraints);
		buttonPanelBagConstraints.insets = new Insets(1, 1, 0, 0);
		buttonPanelLayout.setConstraints(operatorButtons[CalculatorButton.SQUARE], buttonPanelBagConstraints);
		buttonPanelLayout.setConstraints(operatorButtons[CalculatorButton.CUBE], buttonPanelBagConstraints);
		buttonPanelLayout.setConstraints(numberButtons[CalculatorButton.SEVEN], buttonPanelBagConstraints);
		buttonPanelLayout.setConstraints(numberButtons[CalculatorButton.EIGHT], buttonPanelBagConstraints);
		buttonPanelLayout.setConstraints(numberButtons[CalculatorButton.NINE], buttonPanelBagConstraints);
		buttonPanelBagConstraints.gridwidth = 0;
		buttonPanelLayout.setConstraints(operatorButtons[CalculatorButton.MINUS], buttonPanelBagConstraints);

		buttonPanelBagConstraints.insets = new Insets(1, 0, 0, 0);
		buttonPanelBagConstraints.gridwidth = 1;
		buttonPanelLayout.setConstraints(funcButtons[CalculatorButton.LN], buttonPanelBagConstraints);
		buttonPanelBagConstraints.insets = new Insets(1, 1, 0, 0);
		buttonPanelLayout.setConstraints(funcButtons[CalculatorButton.ABS], buttonPanelBagConstraints);
		buttonPanelLayout.setConstraints(operatorButtons[CalculatorButton.POWER], buttonPanelBagConstraints);
		buttonPanelLayout.setConstraints(numberButtons[CalculatorButton.FOUR], buttonPanelBagConstraints);
		buttonPanelLayout.setConstraints(numberButtons[CalculatorButton.FIVE], buttonPanelBagConstraints);
		buttonPanelLayout.setConstraints(numberButtons[CalculatorButton.SIX], buttonPanelBagConstraints);
		buttonPanelBagConstraints.gridwidth = 0;
		buttonPanelLayout.setConstraints(operatorButtons[CalculatorButton.MULTIPLY], buttonPanelBagConstraints);

		buttonPanelBagConstraints.insets = new Insets(1, 0, 0, 0);
		buttonPanelBagConstraints.gridwidth = 1;
		buttonPanelLayout.setConstraints(funcButtons[CalculatorButton.SIN], buttonPanelBagConstraints);
		buttonPanelBagConstraints.insets = new Insets(1, 1, 0, 0);
		buttonPanelLayout.setConstraints(funcButtons[CalculatorButton.COS], buttonPanelBagConstraints);
		buttonPanelLayout.setConstraints(funcButtons[CalculatorButton.TAN], buttonPanelBagConstraints);
		buttonPanelLayout.setConstraints(numberButtons[CalculatorButton.ONE], buttonPanelBagConstraints);
		buttonPanelLayout.setConstraints(numberButtons[CalculatorButton.TWO], buttonPanelBagConstraints);
		buttonPanelLayout.setConstraints(numberButtons[CalculatorButton.THREE], buttonPanelBagConstraints);
		buttonPanelBagConstraints.gridwidth = 0;
		buttonPanelLayout.setConstraints(operatorButtons[CalculatorButton.DIVIDE], buttonPanelBagConstraints);

		buttonPanelBagConstraints.insets = new Insets(1, 0, 0, 0);
		buttonPanelBagConstraints.gridwidth = 1;
		buttonPanelLayout.setConstraints(randomButton, buttonPanelBagConstraints);
		buttonPanelBagConstraints.insets = new Insets(1, 1, 0, 0);
		buttonPanelLayout.setConstraints(numberButtons[CalculatorButton.PI], buttonPanelBagConstraints);
		buttonPanelLayout.setConstraints(numberButtons[CalculatorButton.E], buttonPanelBagConstraints);
		buttonPanelLayout.setConstraints(numberButtons[CalculatorButton.ZERO], buttonPanelBagConstraints);
		buttonPanelLayout.setConstraints(numberButtons[CalculatorButton.POINT], buttonPanelBagConstraints);
		buttonPanelLayout.setConstraints(numberButtons[CalculatorButton.ANS], buttonPanelBagConstraints);
		buttonPanelBagConstraints.gridwidth = 0;
		buttonPanelLayout.setConstraints(solveButton, buttonPanelBagConstraints);
		
		acButton.setBackground(new Color(56, 56, 56));
		delButton.setBackground(new Color(56, 56, 56));
		operatorButtons[CalculatorButton.NEGATIVE].setBackground(new Color(56, 56, 56));
		operatorButtons[CalculatorButton.PLUS].setBackground(new Color(56, 56, 56));
		
		numberButtons[CalculatorButton.SEVEN].setBackground(new Color(40, 40, 40));
		numberButtons[CalculatorButton.EIGHT].setBackground(new Color(40, 40, 40));
		numberButtons[CalculatorButton.NINE].setBackground(new Color(40, 40, 40));
		operatorButtons[CalculatorButton.MINUS].setBackground(new Color(56, 56, 56));
		
		numberButtons[CalculatorButton.FOUR].setBackground(new Color(40, 40, 40));
		numberButtons[CalculatorButton.FIVE].setBackground(new Color(40, 40, 40));
		numberButtons[CalculatorButton.SIX].setBackground(new Color(40, 40, 40));
		operatorButtons[CalculatorButton.MULTIPLY].setBackground(new Color(56, 56, 56));
		
		numberButtons[CalculatorButton.ONE].setBackground(new Color(40, 40, 40));
		numberButtons[CalculatorButton.TWO].setBackground(new Color(40, 40, 40));
		numberButtons[CalculatorButton.THREE].setBackground(new Color(40, 40, 40));
		operatorButtons[CalculatorButton.DIVIDE].setBackground(new Color(56, 56, 56));
		
		numberButtons[CalculatorButton.ZERO].setBackground(new Color(40, 40, 40));
		numberButtons[CalculatorButton.POINT].setBackground(new Color(40, 40, 40));
		numberButtons[CalculatorButton.ANS].setBackground(new Color(40, 40, 40));
		solveButton.setBackground(new Color(255, 32, 32));
	}
	
	private void addButtonActionListener() {
		acButton.addActionListener(new AcButtonActionListener(expressionField, resultField));
		delButton.addActionListener(new DelButtonActionListener(expressionField));
		randomButton.addActionListener(new CalcButtonActionListener(randomButton, expressionField));
		solveButton.addActionListener(new SolveButtonActionListener(expressionField, resultField));
		historyButton.addActionListener(new HistoryButtonActionListener(calculatorForm, expressionField));
		memoryButtons[CalculatorButton.MEMORY_READ].addActionListener(new CalcButtonActionListener(memoryButtons[CalculatorButton.MEMORY_READ], expressionField));
		memoryButtons[CalculatorButton.MEMORY_CLEAR].addActionListener(new MemButtonActionListener(memoryButtons[CalculatorButton.MEMORY_CLEAR]));
		memoryButtons[CalculatorButton.MEMORY_PLUS].addActionListener(new MemButtonActionListener(memoryButtons[CalculatorButton.MEMORY_PLUS]));
		memoryButtons[CalculatorButton.MEMORY_MINUS].addActionListener(new MemButtonActionListener(memoryButtons[CalculatorButton.MEMORY_MINUS]));
		expressionField.addKeyListener(new EnterCalcKeyListener(expressionField, solveButton));
		drawButton.addActionListener(new DrawButtonActionListener(calculatorForm));
		
		for (int i = 0; i < 14; ++i) {
			numberButtons[i].addActionListener(new CalcButtonActionListener(numberButtons[i], expressionField));
		}
		
		for (int i = 0; i < 12; ++i) {
			operatorButtons[i].addActionListener(new CalcButtonActionListener(operatorButtons[i], expressionField));
		}
		
		for (int i = 0; i < 5; ++i) {
			funcButtons[i].addActionListener(new CalcButtonActionListener(funcButtons[i], expressionField));
		}
	}
}


