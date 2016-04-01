package application;

import java.util.Vector;

public class Queue {
	private Vector<String> queue = new Vector<String>();
	
	public void enqueue(String str) {
		queue.add(str);
	}
	
	public String dequeue() {
		String temp = queue.get(0);
		queue.remove(0);
		return temp;
	}
	
	public boolean isEmpty() {
		return (queue.size() == 0);
	}
	
	public int size() {
		return queue.size();
	}
	
	public String toString() {
		String toReturn = "";
		for (int i = 0; i < this.queue.size(); i++) {
			toReturn += this.queue.get(i) + ", ";
		}
		return toReturn;
	}
}
