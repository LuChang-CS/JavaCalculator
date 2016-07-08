package Records.File;

import java.io.File;

public class SystemConst {

	public static final String fileSeparator = File.separator;
	public static final String rootDirectory = System.getProperty("user.dir");
	public static final String userRecords = rootDirectory + fileSeparator + "User";
	
	public static void checkRecordsDirectory() {
		File recordsDir = new File(userRecords);
		if (!recordsDir.exists() || !recordsDir.isDirectory()) {
			recordsDir.mkdir();
		}
	}
	
}
