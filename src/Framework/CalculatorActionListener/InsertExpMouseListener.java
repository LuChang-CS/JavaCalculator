package Framework.CalculatorActionListener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;

import Framework.CalculatorTextField;

public class InsertExpMouseListener implements MouseListener {

	JTable historyTable;
	CalculatorTextField expressionField;
	
	public InsertExpMouseListener(JTable historyTable, CalculatorTextField expressionField) {
		setReference(historyTable, expressionField);
	}
	
	public void setReference(JTable historyTable, CalculatorTextField expressionField) {
		this.historyTable = historyTable;
		this.expressionField = expressionField;
	}
	
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() >= 2) {
			int col = historyTable.getSelectedColumn();
			if (col == 0)
				return;
			int row = historyTable.getSelectedRow();
			String cellContent = (String) historyTable.getValueAt(row, col);
			expressionField.setText(cellContent);
		}
	}

	public void mousePressed(MouseEvent e) {
		
	}

	public void mouseReleased(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {
		
	}
	
	public void mouseExited(MouseEvent e) {
		
	}

}
