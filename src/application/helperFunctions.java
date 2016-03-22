package application;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Vector;

public class helperFunctions {
	private fileIO io = new fileIO();
	
	private String startCity;
	private String destCity;
	private Vector<Node> allNodes = new Vector();
	
	
	/**
	 * gets and sets the input cities defined by the user in /data/a3_in.txt
	 */
	public void getInputCities() {
		Path filePath = new File("../data/a3_in.txt").toPath();
		String[] stringArr = io.readFile(filePath); 
		setStartCity(stringArr[0]);
		setDestCity(stringArr[1]);
	}
	
	
	/**
	 * reads the connected cities file and makes nodes for each unique city
	 */
	public void makeNodes() {
		Path filePath = new File("./data/connectedCities.txt").toPath();
		String[] stringArr = io.readFile(filePath);
		System.out.println(stringArr[0]);
	}
	
	/**
	 * function to get the starting location
	 * @return the starting city 
	 */
	public String getStartCity() {
		return startCity;
	}


	/**
	 * function to set the starting location
	 * @param startCity - the location we start pathfinding from
	 */
	public void setStartCity(String startCity) {
		this.startCity = startCity;
	}

	/**
	 * function to get the destination city
	 * @return the destination city
	 */
	public String getDestCity() {
		return destCity;
	}


	public void setDestCity(String destCity) {
		this.destCity = destCity;
	}

	public void makeGraph() {
//		Path file = 
	}
}
