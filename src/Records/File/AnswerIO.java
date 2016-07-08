package Records.File;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import Records.GlobalConst;
import Records.Answer;

public class AnswerIO {

	static String answerPath = SystemConst.userRecords + SystemConst.fileSeparator + "answer";
	
	public static void readAnswer() {
		File answerFile = new File(answerPath);
		FileReader answerReader;
		BufferedReader answerBufferedReader;
		
		try {
			answerReader = new FileReader(answerFile);
			answerBufferedReader = new BufferedReader(answerReader);
			Answer answer = GlobalConst.getAnswer();
			
			String answerString = answerBufferedReader.readLine();
			answer.setAnswer(answerString);
			answerBufferedReader.close();
			answerReader.close();
			
		} catch (Exception e) {
			return;
		}
	}
	
	public static void writeAnswer() {
		SystemConst.checkRecordsDirectory();
		File answerFile = new File(answerPath);
		FileWriter answerWriter;
		try {
			answerWriter = new FileWriter(answerFile);
			Answer answer = GlobalConst.getAnswer();
			
			answerWriter.write(String.valueOf(answer.getAnswer()));
			answerWriter.close();
			
		} catch (Exception e) {
			return;
		}
	}
}
