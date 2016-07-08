package Framework.CalculatorActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Framework.CoordinatePanel;

public class FuncImgActionListener implements ActionListener {

	CoordinatePanel coordinatePanel;
	
	public FuncImgActionListener(CoordinatePanel coordinatePanel) {
		setReference(coordinatePanel);
	}
	
	public void setReference(CoordinatePanel coordinatePanel) {
		this.coordinatePanel = coordinatePanel;
	}
	
	public void actionPerformed(ActionEvent e) {
		coordinatePanel.exportImage();
	}
}
