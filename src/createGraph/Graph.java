package createGraph;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Graph {
	private static int nodesCount;
	private static int edgesCount;
	
	private static ArrayList<Stop> stops = new ArrayList<Stop>();
	private static ArrayList<Subway> subways = new ArrayList<Subway>();
	private Map<String, Subway> nameSubway = new HashMap<String, Subway>();
	private static List<Edge> edges = new ArrayList<Edge>();
	private static List<Double> listOfWeight = new ArrayList<Double>();
	
	private static boolean isWeigtedGraph;
	
	/**
	 * Constructor of UnweightedGraph
	 * @param tab
	 * @throws IOException
	 */
	public Graph(String  tab[], boolean isWeighted) throws IOException {
		setWeigtedGraph(isWeighted);
		createAllStop(tab);
		addEdges(tab);
		addNeighborsToStop();
		print();
	}
	
	/**
	 * Create All Stops.
	 * @param tab
	 * @throws IOException
	 */
	public void createAllStop(String tab[]) throws IOException {
		for(String str : tab) {
			ReadFile readFile = new ReadFile();
			List<String> lines = readFile.read("documents/RATP_GTFS_METRO_" + str + "/stops.txt");
			ArrayList<Stop> listStopsBySubway = new ArrayList<Stop>();
			listStopsBySubway = readFile.parseStopsFile(lines);
			Subway subway = new Subway();
			subway.setName("Metro_"+ str);
			subway.setStops(listStopsBySubway);	
			subway.setNumberOfStops(listStopsBySubway.size());
			nameSubway.put(subway.getName(), subway);
			subways.add(subway);
			if(stops.isEmpty()) {
				stops.addAll(listStopsBySubway);
			} else {
				for(Stop s : listStopsBySubway) {
					if(stops.contains(getStopByName(s.getName()))) {
						getStopByName(s.getName()).getId().addAll(s.getId());
					}
					else {
						stops.add(s);
					}
				}
			}
		}
	}
	
	/**
	 * Add the position of each stop inside subways.
	 * @param tab
	 * @throws IOException
	 */
	public void addEdges(String tab[]) throws IOException {
		for(String str : tab) {
			ReadFile readFile = new ReadFile();
			List<String> lines = readFile.read("documents/RATP_GTFS_METRO_" + str + "/stop_times.txt");
			Subway sub = getSubwayByName(nameSubway, "Metro_"+str);
			readFile.parseStopTimesFile(lines,str,sub);
		}
	}

	/**
	 * print()
	 */
	public void print() {
		for(Stop s : getListStops()) {
			System.out.println("-------------------------------------------");
			System.out.println(s.getName() + ", ID : " + s.getId());
			System.out.println("Voisins de " + s.getName() + " : ");
			for(Stop st : s.getNeighbors()) {
				System.out.println(st.getName());
			}
		}
		System.out.println(" ---------------------------------------------------------- ");
		System.out.println("Nombre total de Stations : " + getNumberOfStopInParis());
		System.out.println(" ---------------------------------------------------------- ");
		for(Edge e : getEdges()) {
			System.out.println( e.getFrom() + " -> " + e.getTo() + " with weight : "  + e.getWeight());
		}
		System.out.println("-------------------------------------------");
		System.out.println("Nombre de Edges : " + getNumberOfEdges());
	}
	
	/**
	 * createAndgetListOfEdge()
	 * @param edge
	 * @param idFrom
	 * @param idTo
	 */
	public static void createEdges(Long idFrom, Long idTo) {
		Edge edge = new Edge();
		String nameStopFrom = getStopById(idFrom).getName();
		String nameStopTo = getStopById(idTo).getName();
		Double weight =  getWeightBetweenToStops(getStopById(idFrom),getStopById(idTo));
				
		if(nameStopFrom != nameStopTo) {
			edge.setFrom(nameStopFrom);
			edge.setTo(nameStopTo);
			edge.setWeight(weight);
			getListOfWeight().add(weight);
		} 
		
		if(!getEdges().contains(edge)) {
			getEdges().add(edge);
		}
	}
	
	/**
	 * getWeightBetweenToStops()
	 * @param from
	 * @param to
	 * @return dist
	 */
	public static Double getWeightBetweenToStops(Stop from, Stop to) {
		Double dist = null;
		Double lat1 = from.getLatitude();
		Double lng1 = from.getLongitude();
		Double lat2 = to.getLatitude();
		Double lng2 = to.getLongitude();
		
		if(isWeigtedGraph() == true) {
			double earthRadius = 6371000; //meters
			double dLat = Math.toRadians(lat2-lat1);
			double dLng = Math.toRadians(lng2-lng1);
			double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
			           Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
			           Math.sin(dLng/2) * Math.sin(dLng/2);
			double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
			dist = earthRadius * c;
		} else {
			dist = 0.0;
		}
		return dist;
	}
	
	/**
	 * addNeighborsToStop()
	 */
	public static void addNeighborsToStop() {
		List<Edge> allEdges = getEdges();
		for(Edge e : allEdges) {
			Stop stopFrom = getStopByName(e.getFrom());
			Stop stopTo = getStopByName(e.getTo());
	
			
			if(stopFrom.getNeighbors().isEmpty() || !stopFrom.getNeighbors().contains(stopTo))  {
				stopFrom.getNeighbors().add(stopTo);
			}
		}
	}
	
	/**
	 * getSubwayByName()
	 * @param map
	 * @param name
	 * @return
	 */
	public Subway getSubwayByName(Map<String, Subway> map, String name) {
		return map.get(name);
	}
	
	/**
	 * getListStops()
	 * @return stops
	 */
	public static List<Stop> getListStops() {
		return stops;
	}
	
	/**
	 * getListSubways()
	 * @return subways
	 */
	public static ArrayList<Subway> getListSubways() {
		return subways;
	}
	
	/**
	 * getNumberOfStopInParis()
	 * @return nodesCount
	 */
	public static int getNumberOfStopInParis() {
		nodesCount = getListStops().size();
		return nodesCount;
	}
	
	/**
	 * getNumberOfEdges()
	 * @return edgesCount
	 */
	public static int getNumberOfEdges() {
		edgesCount = getEdges().size();
		return edgesCount;
	}

	/**
	 * getEdges()
	 * @return edges
	 */
	public static List<Edge> getEdges() {
		return edges;
	}

	/**
	 * setEdges()
	 * @param edges
	 */
	public void setEdges(List<Edge> edges) {
		this.edges = edges;
	}
	
	/**
	 * getStopById()
	 * @param id
	 * @return
	 */
	
	public static Stop getStopById(Long id) {
		Stop stop = null;
		List<Stop> allStops = getListStops();
		for(Stop s : allStops) {
			if(s.getId().contains(id)) {
				stop = s;
			}
		}
		return stop;
	}
	
	/**
	 * getStopByName()
	 * @param name
	 * @return
	 */
	public static Stop getStopByName(String name) {
 		Stop stop = null;
		List<Stop> allStops = getListStops();
		for(Stop s : allStops) {
			String name2 = s.getName();
			if(name2.equals(name)) {
				stop = s;
			}
		}
		return stop;
	}

	/**
	 * isWeigtedGraph()
	 * @return
	 */
	public static boolean isWeigtedGraph() {
		return isWeigtedGraph;
	}

	/**
	 * setWeigtedGraph()
	 * @param isWeigtedGraph
	 */
	public static void setWeigtedGraph(boolean isWeigtedGraph) {
		Graph.isWeigtedGraph = isWeigtedGraph;
	}

	public static List<Double> getListOfWeight() {
		return listOfWeight;
	}
	
	public Edge getEdgeWithFromAndTo(String from, String to) {
		Edge edge = null;
		for(Edge e : getEdges()) {
			if(e.getFrom().equals(from) && e.getTo().equals(to)) {
				edge = e;
			}
		}
		return edge;
	}
}
