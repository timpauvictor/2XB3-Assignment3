package cas2xb3_A3_timpau_vt;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Vector;

public class helperFunctions {
	private fileIO io = new fileIO();
	
	private String startCity;
	private String destCity;
	private Vector<Node> allNodes = new Vector();
	
	public void getInputCities() {
		Path file = new File("../data/a3_in.txt").toPath();
		String[] stringArr = io.readFile(file); 
		setStartCity(stringArr[0]);
		setDestCity(stringArr[1]);
	}
	
	public String getStartCity() {
		return startCity;
	}


	public void setStartCity(String startCity) {
		this.startCity = startCity;
	}


	public String getDestCity() {
		return destCity;
	}


	public void setDestCity(String destCity) {
		this.destCity = destCity;
	}

	public void makeGraph() {
		Path file = 
	}
}
