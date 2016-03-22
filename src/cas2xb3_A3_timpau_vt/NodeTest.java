package cas2xb3_A3_timpau_vt;

import static org.junit.Assert.*;

import org.junit.Test;

public class NodeTest {

	@Test
	public void test() {
		Node newYork = new Node("New York");
		Node philadelphia = new Node("Philadelphia");
		Node pittsburg = new Node("Pittsburg");
		newYork.addConnection(philadelphia);
		newYork.addConnection(pittsburg);
		
		pass(newYork.toString().equals("New York is connected to Philadelphia, Pittsburg"));
	}

}
