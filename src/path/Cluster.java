package path;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import createGraph.Edge;
import createGraph.Graph;


public class Cluster {
	

	public static Map<Edge, Integer> edgesfrequency ;
	
	/**
	 * Constructor Cluster.
	 */
	public Cluster(){
		edgesfrequency = new HashMap<Edge, Integer>();
	}
	
	/**
	 * init()
	 * @param g
	 */
	public static void init(Graph g){
		List<Edge> allEdges = g.getEdges();
		for (Edge e : allEdges){
			edgesfrequency.put(e,0);
		}
		
	}
	
	/**
	 * refreshFrequency()
	 * @param nodeListOfPath
	 * @return edgesfrequency
	 */
	public static Map<Edge, Integer> refreshFrequency(ArrayList<String> nodeListOfPath){
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
	
	/**
	 * getEdgesFrequency()
	 * @return edgesfrequency
	 */
	public Map<Edge, Integer> getEdgesFrequency() {
		return edgesfrequency;
	}
	
	/**
	 * getImportantEdge()
	 * @param ef
	 * @param maxVisit
	 * @return myList
	 */
	public List<Entry<Edge,Integer>> getImportantEdge(Map<Edge, Integer> ef, Integer maxVisit) {
		Map<Edge, Integer> edgesToExclude = new HashMap<Edge, Integer>();
		List<Entry<Edge,Integer>> myList = new ArrayList<Entry<Edge,Integer>>();
		
		for(Entry<Edge, Integer> dist : ef.entrySet()) {
			if(dist.getValue() > maxVisit) {
				myList.add(dist);			
			}
		}
		return myList;
	}
	
}
