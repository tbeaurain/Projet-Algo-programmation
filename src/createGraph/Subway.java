package createGraph;
import java.util.ArrayList;


public class Subway {
	private String name;
	private ArrayList<Stop> stops;
	private int numberOfStops;
	
	/**
	 * getName()
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * setNames()
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * getStops()
	 * @return stops
	 */
	public ArrayList<Stop> getStops() {
		return stops;
	}
	
	/**
	 * setStops()
	 * @param stops
	 */
	public void setStops(ArrayList<Stop> stops) {
		this.stops = stops;
	}
	
	/**
	 * getNumberOfStops()
	 * @return numberOfStops
	 */
	public int getNumberOfStops() {
		return numberOfStops;
	}
	
	/**
	 * setNumberOfStops()
	 * @param numberOfStops
	 */
	public void setNumberOfStops(int numberOfStops) {
		this.numberOfStops = numberOfStops;
	}
}
