package application;

import java.util.Vector;

public class Stack {
	private Vector<String> stack;
	public Stack() {
		this.stack = new Vector<String>();
	}
	
	public String pop() {
		String temp = stack.get(stack.size() - 1);
		stack.remove(stack.size() - 1);
		return temp;
	}
	
	public void push(String s) {
		this.stack.add(s);
	}
	
	public int size() {
		return this.stack.size();
	}
	
	public String toString() {
		String toReturn = "";
		for (int i = 0; i < this.stack.size(); i++) {
			toReturn += this.stack.get(i) + ", ";
		}
		return toReturn;
	}
}
