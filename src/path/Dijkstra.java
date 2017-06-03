package path;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;

import createGraph.Edge;
import createGraph.Graph;
import createGraph.Stop;

public class Dijkstra extends Path {
	
	/**
	 * verifyNonNegative()
	 * @param g
	 * @return
	 */
	public static boolean verifyNonNegative(Graph g) {
		boolean graphWithoutNegativeWeight = false;
		List<Double> weightList = g.getListOfWeight();
		for(double weight : weightList) {
			if (weight >= 0) {
				graphWithoutNegativeWeight = true;
			} else {
				graphWithoutNegativeWeight = false;
			}
		}
		return graphWithoutNegativeWeight;
		
	}
	
	/**
	 * DijkstraSP()
	 * @param g
	 * @param s
	 */
	public static void DijkstraSP(Graph g, String s) {
		if(verifyNonNegative(g) == true) {
			setMarked(new HashMap<String, Boolean>());
			setPrevious(new HashMap<String, String>());
			setDistance(new HashMap<String, Double>());
			getDistance().put(s, 0.0);
			
			Queue<String> q = new LinkedList<String>();
			List<String> visited = new ArrayList<String>();
			q.add(s);
			
			while(!q.isEmpty()) {
				String currentVisit = q.poll();
				List<Stop> neighbors = g.getStopByName(currentVisit).getNeighbors();
				visited.add(currentVisit);
				getMarked().put(currentVisit, true);
				if(neighbors != null) {
					for(Stop neighbor : neighbors) {
						if(!visited.contains(neighbor.getName())) {
							Edge e = g.getEdgeWithFromAndTo(currentVisit, neighbor.getName());
							if(getDistance().get(neighbor.getName()) == null || getDistance().get(neighbor.getName()) > getDistance().get(currentVisit) + e.getWeight()) {
								getPrevious().put(neighbor.getName(), currentVisit);
								getDistance().put(neighbor.getName(), getDistance().get(currentVisit) + e.getWeight());
								q.offer(neighbor.getName());
							}
						}
					}
				}	
			}
		}
		//printPath("Bastille", s);	
	}
	
	/**
	 * This method allows to have the diameter of the Graph.
	 * @param g
	 */
	public static void DijkstraLP(Graph g) {
		
		Double maxPathDistance = 0.0;
		String from ="";
		String to="";
		
		for(Stop stop : g.getListStops()) {
			DijkstraSP(g, stop.getName());
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
	 * printPath()
	 * @param v
	 * @param sommet
	 */
	public static void printPath(String v, String sommet) {
		ArrayList<String> nodeListOfPath = new ArrayList<String>();
		String currentNode = v;  
		nodeListOfPath.add(currentNode);  
		
		if (hasPathTo(currentNode)) {
			while(!currentNode.equals(sommet)) {			
				currentNode = getPrevious().get(currentNode); 
				nodeListOfPath.add(currentNode); 			
			}
			System.out.println("SP de {" + v + " to " + currentNode + "} : " + nodeListOfPath);
			System.out.println("Minimum distance between "  + v + " and " + currentNode + " : " + getDistance().get(v) + " meters");
		}
	}
}
