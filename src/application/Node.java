package application;

import java.util.Arrays;
import java.util.Vector;

public class Node {
	private String name;
	private String[] states;
	private String zipCode;
	private String latitude;
	private String longitude;
	private double gasPrice;
	
	public Node(String name, String[] state) {
		setName(name);
		setState(state);
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String setTo) {
		this.name = setTo;
	}
	
	public String toString() {
		String toReturn = "";
		toReturn += name + ", " + Arrays.toString(states);
		return toReturn;
	}

	public String[] getState() {
		return states;
	}

	public void setState(String[] states) {
		this.states = states;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public double getGasPrice() {
		return gasPrice;
	}

	public void setGasPrice(double gasPrice) {
		this.gasPrice = gasPrice;
	}
}
