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
	private String[][] gasPrices = getPriceData();
	private String startCity;
	private String destCity;
	private Vector<Edge> allEdges = new Vector<Edge>();
	
	/**
	 * gets and sets the input cities defined by the user in /data/a3_in.txt
	 */
	public void getInputCities() {
		Path filePath = new File("./data/a3_in.txt").toPath();
		String[] stringArr = io.readFile(filePath); 
		setStartCity(stringArr[0]);
		setDestCity(stringArr[1]);
	}
	
	public void doDFS() {
		Graph g = new Graph(allEdges);
		g.dfs(startCity, destCity);
		
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
			Edge newEdge = new Edge(fromNode, destNode, getWeight(fromNode, destNode));
			allEdges.add(newEdge);
			newEdge = new Edge(destNode, fromNode, getWeight(destNode, fromNode));
			allEdges.add(newEdge);
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
	
	/**
	 * function to return all usable data contained in a 
	 * @param node
	 * @return
	 */
	public String[] getZipData(Node node) {
		String toFind = node.getState()[0] + "," + node.getName().toUpperCase();
		for (int i = 0; i < splitZip.length; i++) {
			String currentElement = splitZip[i][2] + "," + splitZip[i][3];
			if (toFind.equals(currentElement)) {
				return new String[]{splitZip[i][1], splitZip[i][4], splitZip[i][5]};
			}
		}
		
		return new String[]{""}; //a match is always found so this will never get returned
	}
	
	/**
	 * function to calculate the weight for the path between two nodes
	 * @param from the node we're coming from
	 * @param dest the node we're going to
	 * @return the weight as a double
	 */
	public double getWeight(Node from, Node dest) {
		String[] returnData = getZipData(from);
		from.setZipCode(returnData[0]);
		from.setLatitude(returnData[1]);
		from.setLongitude(returnData[2]);
		
		returnData = getZipData(dest);
		dest.setZipCode(returnData[0]);
		dest.setLatitude(returnData[1]);
		dest.setLongitude(returnData[2]);
		
		double weight = 0;
		double distance = getDistance(from, dest);
		from.setGasPrice(getGas(from));
		dest.setGasPrice(getGas(dest));
		
		distance = (distance / 100) * 8.2;
		weight = distance * (from.getGasPrice() / 100);
		return weight;
	}
	
	/**
	 * function to get the gas price for a node
	 * @param node for which we are searching got gas prices
	 * @return a double containing the average price
	 */
	public double getGas(Node node) {
		String[] states = node.getState();
		double avgPrice = 0;
		for (int i = 0; i < states.length; i++) {
			avgPrice += getPrice(states[i]);
		}
		return avgPrice / states.length;
	}
	
	/**
	 * reads the gas prices csv and prepares it as a nice 2d array
	 * @return a String[][] containing the data in the files
	 */
	public String[][] getPriceData() {
		Path filePath = new File("./data/StateGasPrice.csv").toPath();
		String[] rawData = io.readFile(filePath);
		String[][] splitData = new String[rawData.length][2];
		for (int i = 0; i < rawData.length; i++) {
			splitData[i] = rawData[i].split(",");
		}
		return splitData;
	}
	
	/**
	 * function get the gas price for a certain state
	 * @param state as a string
	 * @return return an integer containing the gas price
	 */
	public int getPrice(String state) {
		for (int i = 0; i < gasPrices.length; i++) {
			if (state.equals(gasPrices[i][0])) {
//				System.out.println(gasPrices[i][1]);
				return Integer.parseInt(gasPrices[i][1].trim());
			}
		}
		return 0;
	}
	
	
	/**
	 * function to find the haversine distance between two coordinates
	 * @param from - the node we're coming from
	 * @param dest - the node we're going to
	 * @return the distance in kms as a double
	 */
	private double getDistance(Node from, Node dest) {
		double toReturn = 0;
		double earthRadius = 6372.8;
		
		double lat1 = Math.toRadians(Double.parseDouble(from.getLatitude()));
		double lat2 = Math.toRadians(Double.parseDouble(dest.getLatitude()));
		double deltaLatitude = Math.toRadians( Double.parseDouble(dest.getLatitude()) - Double.parseDouble(from.getLatitude()));
		double deltaLongitude = Math.toRadians(Double.parseDouble(dest.getLongitude()) - Double.parseDouble(from.getLongitude()));
		
		double a = (Math.sin(deltaLatitude/2)) * (Math.sin(deltaLatitude/2) + (Math.cos(lat1) * Math.cos(lat2)) * 
				Math.sin(deltaLongitude/2) * Math.sin(deltaLongitude/2));
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		toReturn = earthRadius * c;
		return toReturn; 
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

	/**
	 * set the destination city 
	 * @param destCity
	 */
	public void setDestCity(String destCity) {
		this.destCity = destCity;
	}
}
