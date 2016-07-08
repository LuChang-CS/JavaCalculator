package Records;

import java.util.ArrayList;
import java.util.List;

public class ExpressionRecordsList {
	
	List<ExpressionRecord> expressionRecordsList;
	
	public ExpressionRecordsList() {
		expressionRecordsList = new ArrayList<ExpressionRecord>();
	}
	
	public void addRecord(ExpressionRecord expressionRecord) {
		expressionRecordsList.add(expressionRecord);
	}
	
	public void addRecord(String expression, String result) {
		expressionRecordsList.add(new ExpressionRecord(expression, result));
	}
	
	public void remove(int index) {
		expressionRecordsList.remove(index);
	}
	
	public void removeAll() {
		int length = expressionRecordsList.size();
		for (int i = 0; i < length; ++i) {
			remove(i);
		}
	}
	
	public ExpressionRecord getRecord(int index) {
		return expressionRecordsList.get(index);
	}
	
	public int size() {
		return expressionRecordsList.size();
	}
	
}
