package testers;

import static org.junit.Assert.*;

import org.junit.Test;

import application.Edge;
import application.Node;

public class NodeTest {

	@Test
	public void test() {
		//I guess this also counts as testing the edges
		Node newYork = new Node("New York", "NY");
		Node philadelphia = new Node("Philadelphia", "PA");
		Node pittsburg = new Node("Pittsburg", "PA");
		Edge newEdge = new Edge(newYork, pittsburg, 15);
		assert(newEdge.toString().equals("New York,NY-Pittsburg,PA 15"));
		newEdge = new Edge(newYork, philadelphia, 12);
		assert(newEdge.toString().equals("New York,NY-Philadelphia,PA 15"));
	}

}