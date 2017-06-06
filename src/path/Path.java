package path;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import createGraph.Graph;
import createGraph.Stop;

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
	
	/**
	 * Find the diameter of the Graph.
	 * @param g
	 * @param isWeighted
	 * @param endEdge
	 */
	public static void LP(Graph g, boolean isWeighted, String endEdge) {
		
		Double maxPathDistance = 0.0;
		String from ="";
		String to="";
		
		for(Stop stop : g.getListStops()) {
			if(isWeighted == false) {
				BFS.bfs(g, stop.getName(),endEdge);
			} else {
				Dijkstra.DijkstraSP(g, stop.getName(), endEdge);
			}
			for(Entry<String, Double> dist : getDistance().entrySet()) {
				if(dist.getValue() > maxPathDistance) {
					from = stop.getName();
					maxPathDistance = dist.getValue();
					to = dist.getKey();
				}
			}
		}
		
		String longestPath = "LongestPath : "  + from + " -> " + to;
		String longestDistance = "LongestDistance : " + maxPathDistance;
		
		System.out.println("----------------------------------------------------------");
		System.out.println(longestPath);
		System.out.println(longestDistance);
		
	}

	/**
	 * printSP()
	 * @param v
	 * @param sommetNode
	 */
	public static void printSP(String v, String sommetNode) {
		ArrayList<String> nodeListOfPath = new ArrayList<String>();
		String currentNode = v;  
		nodeListOfPath.add(currentNode);  
		
		if (hasPathTo(currentNode)) {
			while(!currentNode.equals(sommetNode)) {			
				currentNode = getPrevious().get(currentNode); 
				nodeListOfPath.add(currentNode); 			
			}
			System.out.println("SP de {" + currentNode + " to " + v + "} : " + nodeListOfPath);
			System.out.println("Minimum distance between "  + currentNode + " and " + v + " : " + getDistance().get(v) + " meters");
		}
	}
	
}
