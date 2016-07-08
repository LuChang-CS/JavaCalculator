package Records.File;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import Records.ExpressionRecord;
import Records.ExpressionRecordsList;
import Records.GlobalConst;

public class HistoryIO {

	static String historyPath = SystemConst.userRecords + SystemConst.fileSeparator + "history";
	
	public static void readHistory() {
		File historyFile = new File(historyPath);
		FileReader historyReader;
		BufferedReader historyBufferedReader;
		
		try {
			System.out.println(SystemConst.userRecords);
			historyReader = new FileReader(historyFile);
			historyBufferedReader = new BufferedReader(historyReader);
			ExpressionRecordsList expressionRecordsList = GlobalConst.getExpressionRecordsList();
			
			String historyString = historyBufferedReader.readLine();
			String[] records = historyString.split("@");
			int length = records.length;
			
			for (int i = 0; i < length; ++i) {
				String[] record = records[i].split(":");
				expressionRecordsList.addRecord(record[0], record[1]);
			}
			historyBufferedReader.close();
			historyReader.close();
			
		} catch (Exception e) {
			return;
		}
	}
	
	public static void writeHistory() {
		SystemConst.checkRecordsDirectory();
		File historyFile = new File(historyPath);
		FileWriter historyWriter;
		try {
			historyWriter = new FileWriter(historyFile);
			ExpressionRecordsList expressionRecordsList = GlobalConst.getExpressionRecordsList();
			ExpressionRecord expressionRecord;
			int length = expressionRecordsList.size();
			
			for (int i = 0; i < length; ++i) {
				expressionRecord = expressionRecordsList.getRecord(i);
				historyWriter.write(expressionRecord.getExpression() + ":" + expressionRecord.getResult() + "@");
			}
			historyWriter.close();
			
		} catch (Exception e) {
			return;
		}
	}
}
