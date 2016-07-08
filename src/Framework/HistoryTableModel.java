package Framework;

import javax.swing.table.DefaultTableModel;

public class HistoryTableModel extends DefaultTableModel {
	
	public HistoryTableModel(Object[][] tableContent, Object[] tableHeaders) {
		super(tableContent, tableHeaders);
	}

	public Class<?> getColumnClass(int col) {
		if(col==0) {
			return Boolean.class;
		}
		return Object.class;
	}
	
	public boolean isCellEditable(int row, int col) {
		if (col == 0) {
			return true;
		} else {
			return false;
		}
	}
}
