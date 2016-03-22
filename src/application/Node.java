package application;

import java.util.Vector;

public class Node {
	private String name;
	private String state;
	
	public Node(String name, String state) {
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
		toReturn += name + "," + state;
		return toReturn;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
