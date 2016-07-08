package Records;

public final class GlobalConst {
	
	static Answer answer = new Answer();
	static ExpressionRecordsList expressionRecordsList = new ExpressionRecordsList();
	static Memory memory = new Memory();
	static ShouldClearExpressionField shouldClearExpressionField = new ShouldClearExpressionField();
	static boolean isSelectAll = false;
	
	public static Answer getAnswer() {
		return answer;
	}
	
	public static ExpressionRecordsList getExpressionRecordsList() {
		return expressionRecordsList;
	}
	
	public static Memory getMemory() {
		return memory;
	}
	
	public static ShouldClearExpressionField getShouldClearExpressionField() {
		return shouldClearExpressionField;
	}
	
	public static boolean getIsSelectedAll() {
		return isSelectAll;
	}
	
	public static void changeIsSelectAll() {
		isSelectAll = !isSelectAll;
	}
	
	public static void setIsSelectAll(boolean selectAll) {
		isSelectAll = selectAll;
	}
}
