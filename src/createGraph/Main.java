package createGraph;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import path.BFS;
import path.Cluster;
import path.Dijkstra;
import path.Path;


public class Main {
	public final static String  tab[] = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","3b","7b"};
	
	/**
	 * Main.
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		Boolean isWeightedGraph = true;
		Graph g = new Graph(tab,isWeightedGraph);
		System.out.println("----------------------------------------------------------");
<<<<<<< HEAD
		Cluster c = new Cluster();
		c.initialisation(g);
		Map<createGraph.Edge,Integer> ef = c.getedgesfrequency();
		
		if(isWeightedGraph == false) {
			BFS.bfs(g, "Nation");
		} else {
			Dijkstra.DijkstraLP(g, "Bastille");
		}
		for(Entry<Edge, Integer> dist : ef.entrySet()) {
			System.out.println(dist.getKey().getFrom()+"    " +dist.getKey().getTo()+ "    "+ dist.getValue());
		}
=======
		Path.LP(g, isWeightedGraph, "Bastille");
>>>>>>> 3768016c9640bfa3968164153d806ce4d2423681
	}
}
