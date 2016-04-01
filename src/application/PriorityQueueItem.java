package application;

public class PriorityQueueItem {
	String cityName;
	double weight;
	
	public PriorityQueueItem(String city, double dist) {
		this.cityName = city;
		this.weight = dist;
	}
	
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public double getDistance() {
		return weight;
	}

	public void setDistance(double distance) {
		this.weight = distance;
	}
	
}
