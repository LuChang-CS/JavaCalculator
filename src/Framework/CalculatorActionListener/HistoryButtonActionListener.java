package Framework.CalculatorActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Framework.CalculatorForm;
import Framework.CalculatorTextField;
import Framework.HistoryForm;

public class HistoryButtonActionListener implements ActionListener {

	CalculatorForm calculatorForm;
	CalculatorTextField expressionField;
	
	public HistoryButtonActionListener(CalculatorForm calculatorForm, CalculatorTextField expressionField) {
		setReference(calculatorForm, expressionField);
	}
	
	public void setReference(CalculatorForm calculatorForm, CalculatorTextField expressionField) {
		this.calculatorForm = calculatorForm;
		this.expressionField = expressionField;
	}
	
	public void actionPerformed(ActionEvent e) {
		new HistoryForm(calculatorForm, expressionField);
	}

}
