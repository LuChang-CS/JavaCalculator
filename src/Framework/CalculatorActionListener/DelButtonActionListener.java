package Framework.CalculatorActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Framework.CalculatorTextField;

public class DelButtonActionListener implements ActionListener {

	CalculatorTextField expressionField;
	
	public DelButtonActionListener(CalculatorTextField expressionField) {
		setReference(expressionField);
	}

	public void setReference(CalculatorTextField expressionField) {
		this.expressionField = expressionField;
	}
	
	public void actionPerformed(ActionEvent e) {
		String text = expressionField.getText();
		if (text == null || text.equals(""))
			return;
		int pos = expressionField.getCaretPosition();
		if (pos == 0)
			return;
		expressionField.setSelectionStart(pos - 1);
		expressionField.setSelectionEnd(pos);
		expressionField.replaceSelection("");
		expressionField.requestFocus();
	}
}
