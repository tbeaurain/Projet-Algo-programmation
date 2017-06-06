package path;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Map.Entry;

import createGraph.Graph;
import createGraph.Stop;

public class BFS extends Path {
	
	/**
	 * bfs()
	 * @param g
	 * @param firstNode
	 */
	public static void bfs(Graph g, String firstNode, String endEdge) {
		setMarked(new HashMap<String, Boolean>());
		setPrevious(new HashMap<String, String>());
		setDistance(new HashMap<String, Double>());
		getMarked().put(firstNode, true);
		getPrevious().put(firstNode, null);
		getDistance().put(firstNode, 0.0);
		
		Queue<String> q = new LinkedList<String>();
		List<String> visited = new ArrayList<String>();
		Double count = 1.0;
		String prevs = firstNode;
		q.add(firstNode);
		while(!q.isEmpty()) {
			String currentVisit = q.poll();
			List<Stop> neighbors = g.getStopByName(currentVisit).getNeighbors();
			visited.add(currentVisit);
			getMarked().put(currentVisit, true);
			if(neighbors != null) {
				for(Stop neighbor : neighbors) {
					if(!visited.contains(neighbor.getName())) {
						if (!prevs.equals(getPrevious().get(neighbor.getName())) ) {
							count= getDistance().get(currentVisit)+1;					
						}
						if(getDistance().get(neighbor.getName()) == null) {
							getPrevious().put(neighbor.getName(), currentVisit);
							getDistance().put(neighbor.getName(), count);
							System.out.println(neighbor.getName() + " : " + count);
							prevs = currentVisit;
						}
						q.offer(neighbor.getName());
						
					}
				}
			}
		}
		printSP(endEdge, firstNode);
	} 
}
