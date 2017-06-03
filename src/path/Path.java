package path;

import java.util.HashMap;

public class Path {
	
	private static HashMap<String, Boolean> marked;
	private static HashMap<String, String> previous;
	private static HashMap<String, Double> distance;
	
	/**
	 * hasPathTo()
	 * @param v
	 * @return getMarked().get(v)
	 */
	public static Boolean hasPathTo(String v) {
        return getMarked().get(v);
    }
	
	/**
	 * distTo()
	 * @param v
	 * @return getDistance().get(v)
	 */
	public static Double distTo(String v) {
        return getDistance().get(v);
    }
	
	/**
	 * getMarket()
	 * @return marked
	 */
	public static HashMap<String, Boolean> getMarked() {
		return marked;
	}
	
	/**
	 * setMarked()
	 * @param marked
	 */
	public static void setMarked(HashMap<String, Boolean> marked) {
		Path.marked = marked;
	}
	
	/**
	 * getPrevious()
	 * @return previous
	 */
	public static HashMap<String, String> getPrevious() {
		return previous;
	}
	
	/**
	 * setPrevious()
	 * @param previous
	 */
	
	public static void setPrevious(HashMap<String, String> previous) {
		Path.previous = previous;
	}
	
	/**
	 * getDistance()
	 * @return distance
	 */
	public static HashMap<String, Double> getDistance() {
		return distance;
	}
	
	/**
	 * setDistance()
	 * @param distance
	 */
	public static void setDistance(HashMap<String, Double> distance) {
		Path.distance = distance;
	}
	
}
