package application;

import java.util.Vector;

public class PriorityQueue {
	Vector<PriorityQueueItem> items = new Vector<PriorityQueueItem>();
	
	public void addWithPriority(String name, double dist) {
		items.add(new PriorityQueueItem(name, dist));
	}
	
	public String extractMin() {
		double min = items.get(0).getDistance();
		int minIndex = 0;
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getDistance() < min) {
				min = items.get(i).getDistance();
				minIndex = 0;
			}
		}
		String toReturn = items.get(minIndex).getCityName();
		items.remove(minIndex);
		return toReturn;
	}
	
	public void decreasePriority(String name, double dist) {
		int toChange = findCity(name);
		items.elementAt(toChange).setDistance(dist);
	}
	
	public int findCity(String name) {
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getCityName().equals(name)) {
				return i;
			}
		}
		return -1;
	}
	
	public boolean isEmpty() {
		return items.size() == 0;
	}
}
