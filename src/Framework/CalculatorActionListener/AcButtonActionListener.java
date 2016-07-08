package Framework.CalculatorActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Framework.CalculatorTextField;
import Records.GlobalConst;
import Records.ShouldClearExpressionField;

public class AcButtonActionListener implements ActionListener {

	CalculatorTextField expressionField;
	CalculatorTextField resultField;
	
	public AcButtonActionListener(CalculatorTextField expressionField, CalculatorTextField resultField) {
		setReference(expressionField, resultField);
	}

	public void setReference(CalculatorTextField expressionField, CalculatorTextField resultField) {
		this.expressionField = expressionField;
		this.resultField = resultField;
	}
	
	public void actionPerformed(ActionEvent e) {
		expressionField.clearText();
		resultField.clearText();
		expressionField.requestFocus();
		
		ShouldClearExpressionField shouldClearExpressionField = GlobalConst.getShouldClearExpressionField();
		shouldClearExpressionField.setShouldClearExpressionField(false);
	}
	
}
