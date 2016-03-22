package application;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class fileIO {
	public String[] readFile(Path filePath) {
		String[] stringArr = null;
		try { 
			stringArr = Files.readAllLines(filePath).toArray(new String[]{});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stringArr;
	}
}
