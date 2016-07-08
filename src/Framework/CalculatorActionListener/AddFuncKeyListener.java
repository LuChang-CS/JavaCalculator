package Framework.CalculatorActionListener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

import Expression.Exception.SyntaxErrorException;
import Framework.CoordinatePanel;

public class AddFuncKeyListener implements KeyListener {

	CoordinatePanel coordinatePanel;
	JTextField funcField;
	
	public AddFuncKeyListener(CoordinatePanel coordinatePanel, JTextField funcField) {
		setReference(coordinatePanel, funcField);
	}
	
	public void setReference(CoordinatePanel coordinatePanel, JTextField funcField) {
		this.coordinatePanel = coordinatePanel;
		this.funcField = funcField;
	}
	
	public void keyTyped(KeyEvent e) {
		if (e.getKeyChar() == KeyEvent.VK_ENTER) {
			try {
				coordinatePanel.addFunction(funcField.getText(), true);
			} catch (SyntaxErrorException e1) {
				return;
			}
		}
	}

	public void keyPressed(KeyEvent e) {
		
	}

	public void keyReleased(KeyEvent e) {
		
	}

}
