package path;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import createGraph.Edge;
import createGraph.Graph;
import createGraph.Stop;

public class Dijkstra extends Path {
	
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
	
	public static void LP(List<Stop> neighbors, String currentVisit, List<String> visited, Graph g, Queue<String> q) {
		Double e0 = 0.0;
		for(Stop neighbor : neighbors) {
			if(!visited.contains(neighbor.getName())) {
				Edge e = g.getEdgeWithFromAndTo(currentVisit, neighbor.getName());
				if (getDistance().get(neighbor.getName()) == null || e.getWeight() > e0){
					getPrevious().put(neighbor.getName(), currentVisit);
					getDistance().put(neighbor.getName(), getDistance().get(currentVisit) + e.getWeight());
					q.offer(neighbor.getName());
				}
			}
		}
	}
	
	public static void SP(List<Stop> neighbors, String currentVisit, List<String> visited, Graph g, Queue<String> q) {
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
	
	public static void DijkstraPath(Graph g, String s, String path) {
		String maxOrMin = "";
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
					if(path.equals("LP")) {
						maxOrMin = "Maximum";
						LP(neighbors, currentVisit, visited, g, q);
					} else {
						maxOrMin = "Minimum";
						SP(neighbors, currentVisit, visited, g, q);
					}
				}
			}	
		}
		printPath("Bastille", s, maxOrMin, path);
	}
	
	public static void printPath(String v, String sommet, String minOrmax, String SpOrLp) {
		ArrayList<String> nodeListOfPath = new ArrayList<String>();
		String currentNode = v;  
		nodeListOfPath.add(currentNode);  
		
		if (hasPathTo(currentNode)) {
			while(!currentNode.equals(sommet)) {			
				currentNode = getPrevious().get(currentNode); 
				nodeListOfPath.add(currentNode); 			
			}
			System.out.println(SpOrLp + " de {" + v + " to " + currentNode + "} : " + nodeListOfPath);
			System.out.println( minOrmax + " distance between "  + v + " and " + currentNode + " : " + getDistance().get(v) + " meters");
		}
	}
}
