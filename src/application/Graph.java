package application;

import java.util.Vector;

public class Graph {
	private Vector<Edge> allEdges = new Vector<Edge>();
	private Vector<Node> allNodes = new Vector<Node>();
	private Vector<String> currentPath = new Vector<String>();
	private Vector visited = new Vector();
	
	public Graph(Vector<Edge> allEdges) {
		this.allEdges = allEdges;
	}
	
	public void dfs(String startCity, String endCity) {
		System.out.println("Starting from " + startCity);
		Stack s = new Stack();
		s.add(startCity);
		String currentCity = startCity;
		while (s.size() > 0) {
			visited.add(currentCity);
			Vector posLocations = getEdges(currentCity);
			int nextLocation = (int) (Math.random() * posLocations.size() + 1); //generate a random number between the number of possible locations we have and 0
		}
		
		
	}
	
	public Vector getEdges(String startCity) {
		Vector toReturn = new Vector();
		for (int i = 0; i < allEdges.size(); i++) {
			if (allEdges.get(i).getFromNode().getName().equals(startCity)) {
				toReturn.add(allEdges.get(i).getToNode().getName());
			}
		}
		return toReturn;
	}
	
	public void bfs() {
		
	}
}
