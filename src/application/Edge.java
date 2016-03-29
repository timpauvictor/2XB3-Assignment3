package application;

public class Edge {
	private Node fromNode;
	private Node toNode;
	private double weight;
	
	public Edge(Node from, Node to, double weight) {
		this.fromNode = from;
		this.toNode = to;
		this.weight = weight;
	}
	
	public Node getFromNode() {
		return this.fromNode;
	}
	
	public Node getToNode() {
		return this.toNode;
	}
	
	public String toString() {
		return fromNode + "-" + toNode + " " + weight;
	}
}
