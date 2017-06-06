package path;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import createGraph.Edge;
import createGraph.Subway;

import java.util.ArrayList;

public class Cluster {
	

	public static Map<createGraph.Edge, Integer> edgesfrequency ;
	
	public Cluster(){
		
		edgesfrequency = new HashMap<createGraph.Edge, Integer>();
		
	}
	
	public static void initialisation(createGraph.Graph g){
		List<Edge> allEdges = g.getEdges();
		for (Edge e : allEdges){
			edgesfrequency.put(e,0);
		}
		
	}
	
	public static Map<createGraph.Edge, Integer> refreshfrequency(ArrayList<String> nodeListOfPath){
		createGraph.Edge e1; 
		createGraph.Edge e2;
		for (int i=0; i<nodeListOfPath.size()-1;i++){
			e1 = createGraph.Graph.getEdgeWithFromAndTo(nodeListOfPath.get(i), nodeListOfPath.get(i+1));
			e2 = createGraph.Graph.getEdgeWithFromAndTo(nodeListOfPath.get(i+1), nodeListOfPath.get(i));
			if (edgesfrequency.get(e1) != null){
				int ind = edgesfrequency.get(e1) +1;
				edgesfrequency.put(e1, ind);
				edgesfrequency.put(e2, ind);
			}
		}
		return edgesfrequency;
		
	}
	
	public Map<createGraph.Edge, Integer> getedgesfrequency() {
		return edgesfrequency;
	}
	
}
