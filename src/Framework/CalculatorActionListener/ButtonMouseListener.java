package Framework.CalculatorActionListener;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Framework.CalculatorButton;

public class ButtonMouseListener implements MouseListener {

	CalculatorButton button;
	Color oldColor;
	Color pressColor = new Color(0, 0, 0);
	Color enterColor = new Color(24, 24, 24);
	
	public ButtonMouseListener(CalculatorButton btn) {
		setReference(btn);
	}
	
	public void setReference(CalculatorButton btn) {
		button = btn;
		oldColor = button.getBackground();
	}
	
	public void mouseClicked(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		button.setBackground(Color.black);
	}

	public void mouseReleased(MouseEvent e) {
		button.setBackground(oldColor);
	}

	public void mouseEntered(MouseEvent e) {
		oldColor = button.getBackground();
		button.setBackground(enterColor);
	}

	public void mouseExited(MouseEvent e) {
		button.setBackground(oldColor);
	}

}
