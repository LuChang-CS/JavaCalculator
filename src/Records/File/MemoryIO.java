package Records.File;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import Records.GlobalConst;
import Records.Memory;

public class MemoryIO {
	
	static String memoryPath = SystemConst.userRecords + SystemConst.fileSeparator + "memory";
	
	public static void readMemory() {
		File memoryFile = new File(memoryPath);
		FileReader memoryReader;
		BufferedReader memoryBufferedReader;
		
		try {
			memoryReader = new FileReader(memoryFile);
			memoryBufferedReader = new BufferedReader(memoryReader);
			Memory memory = GlobalConst.getMemory();
			
			String memoryString = memoryBufferedReader.readLine();
			memory.setMemory(Double.parseDouble(memoryString));
			memoryBufferedReader.close();
			memoryReader.close();
			
		} catch (Exception e) {
			return;
		}
	}
	
	public static void writeMemory() {
		SystemConst.checkRecordsDirectory();
		File memoryFile = new File(memoryPath);
		FileWriter memoryWriter;
		try {
			memoryWriter = new FileWriter(memoryFile);
			Memory memory = GlobalConst.getMemory();
			
			memoryWriter.write(String.valueOf(memory.read()));
			memoryWriter.close();
			
		} catch (Exception e) {
			return;
		}
	}
}
