import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UnweightedGraph {
	private static ArrayList<Stop> stops = new ArrayList<Stop>();
	private static ArrayList<Subway> subways = new ArrayList<Subway>();
	private Map<String, Subway> nameSubway = new HashMap<String, Subway>();
	private static List<Edge> edges = new ArrayList<Edge>();
	
	/**
	 * Constructor of UnweightedGraph
	 * @param tab
	 * @throws IOException
	 */
	public UnweightedGraph(String  tab[]) throws IOException {
		createAllStop(tab);
		addEdges(tab);
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
					String name = s.getName();
					Stop test = getStopByName(name);
					List<Stop> test2 = getListStops();
					if(stops.contains(test)) {
						getStopByName(name).getId().addAll(s.getId());
					}
					else {
						stops.add(s);
					}
				}
			}
			//stops.addAll(listStopsBySubway);
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
			readFile.parseStopTimesFile(lines);
		}
	}

	/**
	 * print()
	 */
	public void print() {
		for(Stop s : getListStops()) {
			System.out.println(s.getName() + ", ID : " + s.getId());
		}
		/*for(Subway sub : getListSubways()) {
			System.out.println("Nombre de Stations pour le " + sub.getName() + " : " + sub.getNumberOfStops());
			System.out.println("Les Stations de " +  sub.getName() + " sont : ");
			for(Stop stop : sub.getStops()) {
				System.out.println("##### " + stop.getName() + ", ID : " + stop.getId() + ", dans " + sub.getName());
				System.out.println("########## " + stop.getLatitude() + ", Long : " + stop.getLongitude());
			}
		}*/
		System.out.println(" ---------------------------------------------------------- ");
		System.out.println("Nombre total de Stations : " + getNumberOfStopInParis());
		System.out.println(" ---------------------------------------------------------- ");
		for(Edge e : getEdges()) {
			System.out.println( e.getFrom() + " -> " + e.getTo() + " with weight : "  + e.getWeight());
		}
		System.out.println(getEdges().size());
	}
	
	/**
	 * createAndgetListOfEdge()
	 * @param edge
	 * @param idFrom
	 * @param idTo
	 */
	public static void createAndgetListOfEdge(Edge edge, Long idFrom, Long idTo) {
		String nameStopFrom = getStopById(idFrom).getName();
		String nameStopTo = getStopById(idTo).getName();
		Double weight =  getWeightBetweenToStops(getStopById(idFrom),getStopById(idTo));
				
		if(nameStopFrom != nameStopTo) {
			edge.setFrom(nameStopFrom);
			edge.setTo(nameStopTo);
			edge.setWeight(weight);
		} 
		
		if(!getEdges().contains(edge)) {
			getEdges().add(edge);
		}
	}
	
	public static Double getWeightBetweenToStops(Stop from, Stop to) {
		Double lat1 = from.getLatitude();
		Double long1 = from.getLongitude();
		Double lat2 = to.getLatitude();
		Double long2 = to.getLongitude();
		Double weight = null;
		
		weight = Math.sqrt(((lat2-lat1)*(lat2-lat1))+((long2-long1)*(long2-long1)));
		
		return weight;
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
	 * @return getListStops().size()
	 */
	public static int getNumberOfStopInParis() {
		return getListStops().size();
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
}
