package createGraph;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import path.Cluster;
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
		Cluster c = new Cluster();
		c.init(g);
		Map<Edge,Integer> ef = c.getEdgesFrequency();
		
		Path.LP(g, isWeightedGraph, "Bastille");
		List<Entry<Edge,Integer>> edgesToExclude = c.getImportantEdge(ef, 60);
		System.out.println("Les liens trop visités sont : ");
		for(Entry<Edge,Integer> e : edgesToExclude) {
			System.out.println(e.getKey().getFrom() + " -> " + e.getKey().getTo() + ", nombre de visite : " + e.getValue());
		}
		
	}
}
