import java.util.ArrayList;


public class Subway {
	private String name;
	private ArrayList<Stop> stops;
	private int numberOfStops;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Stop> getStops() {
		return stops;
	}
	public void setStops(ArrayList<Stop> stops) {
		this.stops = stops;
	}
	public int getNumberOfStops() {
		return numberOfStops;
	}
	public void setNumberOfStops(int numberOfStops) {
		this.numberOfStops = numberOfStops;
	}
	

}
