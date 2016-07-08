package Framework.CalculatorActionListener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Framework.CalculatorButton;
import Framework.CalculatorTextField;

public class EnterCalcKeyListener implements KeyListener {
	
	CalculatorTextField expressionField;
	CalculatorButton solveButton;
	
	public EnterCalcKeyListener(CalculatorTextField expressionField, CalculatorButton solveButton) {
		setReference(expressionField, solveButton);
	}
	
	public void setReference(CalculatorTextField expressionField, CalculatorButton solveButton) {
		this.expressionField = expressionField;
		this.solveButton = solveButton;
	}

	public void keyTyped(KeyEvent e) {
		if (e.getKeyChar() == KeyEvent.VK_ENTER)
			solveButton.doClick();
	}

	public void keyPressed(KeyEvent e) {
		
	}

	public void keyReleased(KeyEvent e) {
		
	}

}
