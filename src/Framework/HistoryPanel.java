package Framework;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import Framework.CalculatorActionListener.DelHisButtonActionListener;
import Framework.CalculatorActionListener.InsertExpMouseListener;
import Framework.CalculatorActionListener.SelAllButtonActionListener;
import Records.ExpressionRecord;
import Records.ExpressionRecordsList;
import Records.GlobalConst;

public class HistoryPanel extends JPanel {

	int WIDTH = 0;
	int HEIGHT = 0;
	
	CalculatorTextField expressionField;

	JScrollPane historyScrollPane;
	JTable historyTable;
	HistoryTableModel historyTableModel;
	
	JPanel buttonPanel;
	JButton deleteButton;
	JButton selectAllButton;
	
	public HistoryPanel(CalculatorTextField expressionField) {
		setReference(expressionField);
	}
	
	public void display() {
		init();
		setStyle();
		addElements();
		addActionListener();
	}
	
	public void setReference(CalculatorTextField expressionField) {
		this.expressionField = expressionField;
	}
	
	public void setPanelSize(int width, int height) {
		WIDTH = width;
		HEIGHT = height;
	}
	
	private void init() {
		
		Object[][] tableContent;
		Object[] tableHeaders;
		
		ExpressionRecord expressionRecord;
		ExpressionRecordsList expressionRecordsList = GlobalConst.getExpressionRecordsList();
		int length = expressionRecordsList.size();
		
		tableContent = new Object[length][3];
		tableHeaders = new Object[3];
		
		tableHeaders[0] = "";
		tableHeaders[1] = "Expression";
		tableHeaders[2] = "Result";
		
		for (int i = 0; i < length; ++i) {
			expressionRecord = expressionRecordsList.getRecord(i);
			
			tableContent[i][0] = new Boolean(false);
			tableContent[i][1] = expressionRecord.getExpression();
			tableContent[i][2] = expressionRecord.getResult().toString();
		}
		
		historyTableModel = new HistoryTableModel(tableContent, tableHeaders);
		historyTable = new JTable(historyTableModel);
		historyScrollPane = new JScrollPane(historyTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		buttonPanel = new JPanel();

		selectAllButton = new JButton("Select All");
		deleteButton = new JButton("Delete");
	}
	
	private void setStyle() {
		setLayout(new BorderLayout());
		setPanelSize(WIDTH, HEIGHT);
		
		historyTable.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		historyTable.setAutoscrolls(true);
		historyTable.setIntercellSpacing(new Dimension(15, 5));
		historyTable.setFont(new Font("Microsoft Yahei", Font.PLAIN, 14));
		historyTable.setRowHeight(22);
		historyTable.setBackground(Color.WHITE);
		historyTable.setSelectionBackground(new Color(248, 248, 248));
		historyTable.setGridColor(Color.GRAY);
		
		JTableHeader historyTableHeader = historyTable.getTableHeader();
		historyTableHeader.setFont(new Font("Microsoft Yahei", Font.PLAIN, 16));
		historyTableHeader.setPreferredSize(new Dimension(historyTableHeader.getWidth(), 25));
		
		TableColumn checkBoxColumn = historyTable.getColumnModel().getColumn(0);
		checkBoxColumn.setPreferredWidth(40);
		
		TableColumn expressionColumn = historyTable.getColumnModel().getColumn(1);
		expressionColumn.setPreferredWidth(260);
		
		TableColumn resultColumn = historyTable.getColumnModel().getColumn(2);
		resultColumn.setPreferredWidth(170);
		
		historyScrollPane.setPreferredSize(new Dimension(WIDTH, 400));
		
		buttonPanel.setLayout(null);
		buttonPanel.setPreferredSize(new Dimension(WIDTH, 50));
		
		selectAllButton.setBounds(10, 10, 100, 25);
		deleteButton.setBounds(115, 10, 80, 25);
		
		selectAllButton.setFont(new Font("Microsoft Yahei", Font.PLAIN, 14));
		selectAllButton.setFocusPainted(false);
		deleteButton.setFont(new Font("Microsoft Yahei", Font.PLAIN, 14));
		deleteButton.setFocusPainted(false);
	}
	
	private void addElements() {
		add(historyScrollPane, BorderLayout.CENTER);
		
		buttonPanel.add(selectAllButton);
		buttonPanel.add(deleteButton);
		
		add(buttonPanel, BorderLayout.SOUTH);
	}
	
	private void addActionListener() {
		selectAllButton.addActionListener(new SelAllButtonActionListener(historyTable));
		deleteButton.addActionListener(new DelHisButtonActionListener(historyTable, historyTableModel));
		historyTable.addMouseListener(new InsertExpMouseListener(historyTable, expressionField));
	}
}
