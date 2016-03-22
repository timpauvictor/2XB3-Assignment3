package cas2xb3_A3_timpau_vt;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class fileIO {
	public String[] readFile(Path filePath) {
		String[] stringArr = null;
		try { 
			stringArr = (String[]) Files.readAllLines(filePath).toArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stringArr;
	}
}
