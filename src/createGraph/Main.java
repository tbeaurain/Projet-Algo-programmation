package createGraph;
import java.io.IOException;

import path.BFS;
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
		Path.LP(g, isWeightedGraph, "Bastille");
	}
}
