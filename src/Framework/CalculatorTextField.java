package Framework;

import javax.swing.JTextField;

public class CalculatorTextField extends JTextField {
	
	public void appendText(String str) {
			replaceSelection(str);
	}
	
	public void clearText() {
		setText("");
	}
}
