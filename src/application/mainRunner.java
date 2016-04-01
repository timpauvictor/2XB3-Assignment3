package application;

public class mainRunner {
	public static void main(String[] args) { //main function
		helperFunctions helper = new helperFunctions();
		
		helper.makeGraph();
		helper.getInputCities();
//		helper.doDFS();
//		helper.doBFS();
		helper.doShortest();
	}
}

