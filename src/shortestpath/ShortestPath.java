package shortestpath;

import java.util.HashMap;

public class ShortestPath {
	
	private static HashMap<String, Boolean> marked;
	private static HashMap<String, String> previous;
	private static HashMap<String, Double> distance;
	
	public static Boolean hasPathTo(String v) {
        return getMarked().get(v);
    }
	
	public static Double distTo(String v) {
        return getDistance().get(v);
    }
	
	public static HashMap<String, Boolean> getMarked() {
		return marked;
	}
	public static void setMarked(HashMap<String, Boolean> marked) {
		ShortestPath.marked = marked;
	}
	public static HashMap<String, String> getPrevious() {
		return previous;
	}
	public static void setPrevious(HashMap<String, String> previous) {
		ShortestPath.previous = previous;
	}
	public static HashMap<String, Double> getDistance() {
		return distance;
	}
	public static void setDistance(HashMap<String, Double> distance) {
		ShortestPath.distance = distance;
	}
	
}
