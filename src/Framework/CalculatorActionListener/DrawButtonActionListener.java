package Framework.CalculatorActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Framework.CalculatorForm;
import Framework.Draw2DForm;

public class DrawButtonActionListener implements ActionListener {

	CalculatorForm calculatorForm;
	
	public DrawButtonActionListener(CalculatorForm calculatorForm) {
		setReference(calculatorForm);
	}
	
	public void setReference(CalculatorForm calculatorForm) {
		this.calculatorForm = calculatorForm;
	}
	
	public void actionPerformed(ActionEvent e) {
		new Draw2DForm(calculatorForm);
	}

}
