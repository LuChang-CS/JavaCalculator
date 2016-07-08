package Framework.CalculatorActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import Expression.Exception.SyntaxErrorException;
import Framework.CoordinatePanel;

public class AddFuncActionListener implements ActionListener {

	CoordinatePanel coordinatePanel;
	JTextField funcField;
	
	public AddFuncActionListener(CoordinatePanel coordinatePanel, JTextField funcField) {
		setReference(coordinatePanel, funcField);
	}
	
	public void setReference(CoordinatePanel coordinatePanel, JTextField funcField) {
		this.coordinatePanel = coordinatePanel;
		this.funcField = funcField;
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			coordinatePanel.addFunction(funcField.getText());
		} catch (SyntaxErrorException e1) {
			return;
		}
	}

}
