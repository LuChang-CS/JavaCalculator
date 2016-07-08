package Framework.CalculatorActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EmptyStackException;

import Expression.Calculate;
import Expression.Expression;
import Expression.Exception.MathErrorException;
import Expression.Exception.SyntaxErrorException;
import Framework.CalculatorTextField;
import Records.Answer;
import Records.ExpressionRecordsList;
import Records.GlobalConst;
import Records.ShouldClearExpressionField;

public class SolveButtonActionListener implements ActionListener {

	CalculatorTextField expressionField;
	CalculatorTextField resultField;
	
	public SolveButtonActionListener(CalculatorTextField expressionField, CalculatorTextField resultField) {
		setReference(expressionField, resultField);
	}
	
	public void setReference(CalculatorTextField expressionField, CalculatorTextField resultField) {
		this.expressionField = expressionField;
		this.resultField = resultField;
	}
	
	public void actionPerformed(ActionEvent e) {
		String expression = expressionField.getText();
		String result;
		
		if (expression.equals("")) {
			return;
		}
		
		try {
			result = Calculate.format(Expression.calculateExpression(expression));
			resultField.setText(result.toString());
		} catch (MathErrorException mathErrorException) {
			resultField.setText(mathErrorException.toString());
			return;
		} catch (SyntaxErrorException syntaxErrorException) {
			resultField.setText(syntaxErrorException.toString());
			return;
		} catch (NumberFormatException numberFormatException) {
			resultField.setText("Syntax Error!");
			return;
		} catch (EmptyStackException emptyStackException) {
			resultField.setText("Syntax Error!");
			return;
		}
		expressionField.setCaretPosition(expression.length());
		
		Answer answer = GlobalConst.getAnswer();
		answer.setAnswer(result);
		
		ExpressionRecordsList expressionRecordsList = GlobalConst.getExpressionRecordsList();
		expressionRecordsList.addRecord(expression, result);
		
		ShouldClearExpressionField shouldClearExpressionField = GlobalConst.getShouldClearExpressionField();
		shouldClearExpressionField.setShouldClearExpressionField(true);
	}

}
