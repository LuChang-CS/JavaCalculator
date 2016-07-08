package Framework.CalculatorActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Framework.CalculatorButton;
import Framework.CalculatorTextField;
import Records.GlobalConst;
import Records.ShouldClearExpressionField;

public class CalcButtonActionListener implements ActionListener {
	
	CalculatorButton button;
	CalculatorTextField expressionField;
	
	public CalcButtonActionListener(CalculatorButton button, CalculatorTextField expressionField) {
		setReference(button, expressionField);
	}
	
	public void setReference(CalculatorButton button, CalculatorTextField expressionField) {
		this.button = button;
		this.expressionField = expressionField;
	}
	
	public void actionPerformed(ActionEvent e) {
		
		String command = button.getCommand();
		if (GlobalConst.getShouldClearExpressionField().getShouldClearExpressionField()) {
			if (command.equals("+") || command.equals("-") 
					|| command.equals("×") || command.equals("÷") 
					|| command.equals("^") || command.equals("^(-1)")
					|| command.equals("^2") || command.equals("^(3)")) {
				expressionField.setText("Ans");
			}
			else {
				expressionField.clearText();
			}
		}
		expressionField.appendText(button.getCommand());
		expressionField.requestFocus();
		
		ShouldClearExpressionField shouldClearExpressionField = GlobalConst.getShouldClearExpressionField();
		shouldClearExpressionField.setShouldClearExpressionField(false);
	}

}
