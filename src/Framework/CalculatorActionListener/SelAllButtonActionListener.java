package Framework.CalculatorActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;

import Records.GlobalConst;

public class SelAllButtonActionListener implements ActionListener {

	JTable historyTable;
	
	public SelAllButtonActionListener(JTable historyTable) {
		setReference(historyTable);
	}
	
	public void setReference(JTable historyTable) {
		this.historyTable = historyTable;
	}
	
	public void actionPerformed(ActionEvent e) {
		int rowCount = historyTable.getRowCount();
		Boolean shouldSelectAll = new Boolean(!GlobalConst.getIsSelectedAll());
		
		for (int i = 0; i < rowCount; ++i) {
			historyTable.setValueAt(shouldSelectAll, i, 0);
		}
		GlobalConst.changeIsSelectAll();
	}

}
