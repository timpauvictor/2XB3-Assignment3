package cas2xb3_A3_timpau_vt;

import java.util.Vector;

public class Node {
	private String name;
	private Vector<Node> connectedTo = new Vector();
	
	public Node(String name) {
		setName(name);
	}
	
	public void addConnection(Node toAdd) {
		connectedTo.addElement(toAdd);
	}
	
	public Vector<Node> getConnections() {
		return connectedTo;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String setTo) {
		this.name = setTo;
	}
	
	public String toString() {
		String toReturn = "";
		toReturn += name + " is connected to "; 
		for (int i = 0; i < connectedTo.size(); i++) {
			toReturn += connectedTo.get(i).getName() + ", ";
		}
		return toReturn;
	}
}
