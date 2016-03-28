package application;

import java.util.Arrays;
import java.util.Vector;

public class Node {
	private String name;
	private String[] states;
	private String zipCode;
	private String latitude;
	private String longitude;
	
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
}
