package application;

import java.util.Vector;

public class Stack {
	Vector<String> stack;
	public Stack() {
		this.stack = new Vector<String>();
	}
	
	public String pop() {
		String temp = stack.get(stack.size());
		stack.remove(stack.size());
		return temp;
	}
	
	public void add(String s) {
		this.stack.add(s);
	}
	
	public int size() {
		return this.stack.size();
	}
}
