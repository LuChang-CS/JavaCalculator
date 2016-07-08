package Framework.CalculatorActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Framework.CalculatorButton;
import Records.GlobalConst;
import Records.Memory;

public class MemButtonActionListener implements ActionListener{

	CalculatorButton memButton;
	
	public MemButtonActionListener(CalculatorButton memButton) {
		setReference(memButton);
	}
	
	public void setReference(CalculatorButton memButton) {
		this.memButton = memButton;
	}
	
	public void actionPerformed(ActionEvent e) {
		String command = memButton.getCommand();
		Memory memory = GlobalConst.getMemory();
		
		if (command.equals("MC")) {
			memory.clear();
		}
		else if (command.equals("M+")) {
			memory.plus(Double.parseDouble(GlobalConst.getAnswer().getAnswer()));
		}
		else {
			memory.minus(Double.parseDouble(GlobalConst.getAnswer().getAnswer()));
		}
	}
}
