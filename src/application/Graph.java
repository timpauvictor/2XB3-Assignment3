package application;

import java.util.Vector;

public class Graph {
	private Vector<Edge> allEdges = new Vector<Edge>(); //store all edges
	private Vector<String> visited = new Vector<String>(); //store all visited locations
	
	/**
	 * graph constructor, takes all edges
	 * @param allEdges - vector of edges to create a graph from
	 */
	public Graph(Vector<Edge> allEdges) {
		this.allEdges = allEdges;
	}
	
	/**
	 * function to run dfs on the graph
	 * @param startCity - starting vertex
	 * @param endCity - end vertex
	 * @return a stack containing all the visited locations
	 */
	public Stack dfs(String startCity, String endCity) {
		visited = new Vector<String>();
		Stack s = new Stack();
		s.push(startCity);
		while (s.size() != 0) {
			String currentCity = s.pop();
			if (!visited.contains(currentCity)) {
				visited.add(currentCity);
				Vector<String> unvisitedNeighbours = getEdges(currentCity);
				for (int i = 0; i < unvisitedNeighbours.size(); i++) {
					if (unvisitedNeighbours.get(i).equals(endCity)) {
						s.push(endCity);
						return s;
					}
					
					if (!visited.contains(unvisitedNeighbours.get(i))) {
						s.push(unvisitedNeighbours.get(i));
					}
				}
			}
		}
		return s;
	}
	
	/**
	 * get all edges that start from out starting vertex
	 * @param startCity
	 * @return a Vector containing all edges of a certain point
	 */
	public Vector<String> getEdges(String startCity) {
		Vector<String> toReturn = new Vector<String>();
		for (int i = 0; i < allEdges.size(); i++) {
			if (allEdges.get(i).getFromNode().getName().equals(startCity)) {
				toReturn.add(allEdges.get(i).getToNode().getName());
			}
		}
		return toReturn;
	}
	
	public Vector findShortestPath(String startCity, String endCity) { //doesn't work
		Vector<String> path = new Vector<String>();
		Stack s = new Stack();
		visited = new Vector<String>();
		Queue q = new Queue();
		q.enqueue(startCity);
		s.push(startCity);
		while (!q.isEmpty()) {
			String currentCity = q.dequeue();
			Vector<String> unvisitedNeighbours = getEdges(currentCity);
			for (int i = 0; i < unvisitedNeighbours.size(); i++) {
				if (unvisitedNeighbours.get(i).equals(endCity)) {
					q.enqueue(endCity);
					s.push(endCity);
					return path;
				}
				
				if (!visited.contains(unvisitedNeighbours.get(i))) {
					visited.add(unvisitedNeighbours.get(i));
					path.add(unvisitedNeighbours.get(i));
					q.enqueue(unvisitedNeighbours.get(i));
					s.push(unvisitedNeighbours.get(i));
				}
			}
		}
		return path;
	}
	
	public Node getNode(String city) {
		Node toReturn = new Node(null, null);
		for (int i = 0; i < allEdges.size(); i++) {
			if (allEdges.get(i).getFromNode().getName().equals(city)) {
				toReturn = allEdges.get(i).getFromNode();
				return toReturn;
			}
		}
		return toReturn;
	}
	
	/**
	 * function to run bfs on the graph
	 * @param startCity - vertex we start from
	 * @param endCity - vertex we want to end at
	 * @return a stack containing visited locations on our path
	 */
	public Stack bfs(String startCity, String endCity) {
		Stack s = new Stack();
		visited = new Vector<String>();
		Queue q = new Queue();
		q.enqueue(startCity);
		s.push(startCity);
		while (!q.isEmpty()) {
			String currentCity = q.dequeue();
			Vector<String> unvisitedNeighbours = getEdges(currentCity);
			for (int i = 0; i < unvisitedNeighbours.size(); i++) {
				if (unvisitedNeighbours.get(i).equals(endCity)) {
					q.enqueue(endCity);
					s.push(endCity);
					return s;
				}
				
				if (!visited.contains(unvisitedNeighbours.get(i))) {
					visited.add(unvisitedNeighbours.get(i));
					q.enqueue(unvisitedNeighbours.get(i));
					s.push(unvisitedNeighbours.get(i));
				}
			}
		}
		return s;
	}
}
