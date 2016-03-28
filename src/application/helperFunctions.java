package application;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Vector;

public class helperFunctions {
	private fileIO io = new fileIO();
	private String[][] splitZip = getZips();
	private String startCity;
	private String destCity;
	private Vector<Edge> allEdges = new Vector<Edge>();
	
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
	 * reads the connected cities file and makes the graph necessary
	 */
	public void makeGraph() {
		int halfPoint = 0; //the index of the comma seperating two cities
		Path filePath = new File("./data/connectedCities.txt").toPath();
		String[] stringArr = io.readFile(filePath);
		String[][] splitArr = new String[stringArr.length][2];
		
		for (int i = 0; i < stringArr.length; i++) {
			halfPoint = findHalf(stringArr[i]);
			splitArr[i][0] = stringArr[i].substring(0, halfPoint);
			splitArr[i][1] = stringArr[i].substring(halfPoint + 2, stringArr[i].length()); //+2 in order to skip comma and space following it
			String fromCity = getCityName(splitArr[i][0]);
			String[] fromStates = getCityStates(splitArr[i][0]);
			String destCity = getCityName(splitArr[i][1]);
			String[] destStates = getCityStates(splitArr[i][1]);
			Node fromNode = new Node(fromCity, fromStates);
			Node destNode = new Node(destCity, destStates);
			
			getWeight(fromNode, destNode);
		}
	}
	
	
	
	/**
	 * function that returns a string[] of all the states 
	 * @param str
	 * @return string[] of all states belonging to a city
	 */
	public String[] getCityStates(String str) {
		int beginIndex = getFirstIndexOf(str, '(');
		int endIndex = getFirstIndexOf(str, ')');
		String newString = str.substring(beginIndex + 1, endIndex); //beginIndex + 1 to avoid first bracket
		String[] newStringArr = newString.split(",");
		for (int i = 0; i < newStringArr.length; i++) {
			newStringArr[i] = newStringArr[i].replaceAll("\\s+","");
		}
		return newStringArr;
	}
	
	/**
	 * function to make a 2d array of all the zip codes
	 * @return String[][] containing the data in zips1990.csv
	 */
	public String[][] getZips() {
		Path filePath = new File("./data/zips1990.csv").toPath();
		String[] zipArr = io.readFile(filePath);
		String[][] zipArrSplit = new String[zipArr.length][7];
		for (int i = 0; i < zipArrSplit.length; i++) {
			zipArrSplit[i] = zipArr[i].split(",");
		}
		
		return zipArrSplit;
	}
	
	public String getZip(Node node) {
		
		
		return "";
	}
	
	public void getLat(String zipcode) {
		
	}
	
	public void getLong(String zipcode) {
		
	}
	
	public void getWeight(Node from, Node dest) {
		String fromZip = "";
		String destZip = "";
		
		
	}
	
	
	
	/**
	 * function to get the city name from a string that includes the states in brackets
	 * @param str - string to work with
	 * @return the name of the city
	 */
	public String getCityName(String str) {
		String[] splitStr = str.split(" ");
		int openingBracketIndex = getFirstIndexOf(str, '(');
		return str.substring(0, openingBracketIndex - 1); //-1 to get rid of space before brackets
	}
	
	
	/**
	 * function to get the first index of a character c, in a string, str
	 * @param str - string to work with
	 * @param c - char to find
	 * @return int - first index occurrence of c
	 */
	public int getFirstIndexOf(String str, char c) {
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == c) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * function to find first occurence of a comma outside of a pair of brackets and returns the index
	 * @param str - string to work with
	 * @return int - index of comma occurence
	 */
	public int findHalf(String str) {
		int bracketCounter = 0; //counter variable to keep track of open and closed variables
		
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '(') {
				bracketCounter++;
			} else if (str.charAt(i) == ')') {
				bracketCounter--;
			}
			
			if (bracketCounter == 0 && str.charAt(i) == ',') {
				return i;
			}
		}
		return -1;
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
}
