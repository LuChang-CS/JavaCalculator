package Framework.CalculatorActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;

import Framework.HistoryTableModel;
import Records.ExpressionRecordsList;
import Records.GlobalConst;

public class DelHisButtonActionListener implements ActionListener {

	JTable historyTable;
	HistoryTableModel historyTableModel;
	
	public DelHisButtonActionListener(JTable historyTable, HistoryTableModel historyTableModel) {
		setReference(historyTable, historyTableModel);
	}
	
	public void setReference(JTable historyTable, HistoryTableModel historyTableModel) {
		this.historyTable = historyTable;
		this.historyTableModel = historyTableModel;
	}
	
	public void actionPerformed(ActionEvent e) {
		int rowCount = historyTable.getRowCount();
		ExpressionRecordsList expressionRecordsList = GlobalConst.getExpressionRecordsList();
		
		for (int i = 0; i < rowCount; ++i) {
			boolean isSelected = ((Boolean) historyTable.getValueAt(i, 0)).booleanValue();
			if (isSelected) {
				expressionRecordsList.remove(i);
				historyTableModel.removeRow(i);
				--rowCount;
				--i;
			}
		}
		
		GlobalConst.setIsSelectAll(false);
	}

}
