package Framework;

import javax.swing.JDialog;

public class HistoryForm {
	
	int WIDTH = 504;
	int HEIGHT = 480;

	CalculatorForm calculatorForm;
	CalculatorTextField expressionField;
	
	JDialog historyDialog;
	HistoryPanel historyPanel;
	
	public HistoryForm(CalculatorForm calculatorForm, CalculatorTextField expressionField) {
		this.calculatorForm = calculatorForm;
		this.expressionField = expressionField;
		init();
	}
	
	private void init() {
		
		historyDialog = new JDialog(calculatorForm, "History", true);
		historyDialog.setLocationRelativeTo(calculatorForm);
		
		HistoryPanel historyPanel = new HistoryPanel(expressionField);
		historyPanel.setBounds(0, 0, WIDTH, HEIGHT);
		historyPanel.setPanelSize(WIDTH, HEIGHT);
		historyPanel.display();
		
		historyDialog.add(historyPanel);
		
		historyDialog.setBounds(350, 150, WIDTH, HEIGHT);
		historyDialog.setTitle("History");
		historyDialog.setResizable(false);
		historyDialog.setVisible(true);
	}
}
