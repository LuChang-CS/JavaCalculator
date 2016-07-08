package Framework;

import javax.swing.JDialog;

public class Draw2DForm {

	int WIDTH = 800;
	int HEIGHT = 580;
	
	CalculatorForm calculatorForm;

	JDialog draw2DDialog;
	Draw2DPanel draw2DPanel;
	
	public Draw2DForm(CalculatorForm calculatorForm) {
		this.calculatorForm = calculatorForm;
		init();
	}
	
	private void init() {

		draw2DDialog = new JDialog(calculatorForm, "Draw", true);
		draw2DDialog.setLocationRelativeTo(calculatorForm);
		
		draw2DPanel = new Draw2DPanel(calculatorForm);
		draw2DPanel.setBounds(0, 0, WIDTH, HEIGHT);
		draw2DPanel.setPanelSize(WIDTH, HEIGHT);
		draw2DPanel.display();
		
		draw2DDialog.add(draw2DPanel);
		
		draw2DDialog.setBounds(250, 50, WIDTH, HEIGHT);
		draw2DDialog.setTitle("Draw");
		draw2DDialog.setResizable(false);
		draw2DDialog.setVisible(true);
	}

}
