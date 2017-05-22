import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UnweightedGraph {
	private static ArrayList<Stop> stops = new ArrayList<Stop>();
	private static ArrayList<Subway> subways = new ArrayList<Subway>();
	private Map<String, Subway> nameSubway = new HashMap<String, Subway>();
	private List<Edge> edges = new ArrayList<Edge>();
	
	/**
	 * Constructor of UnweightedGraph
	 * @param tab
	 * @throws IOException
	 */
	public UnweightedGraph(String  tab[]) throws IOException {
		createAllStop(tab);
		addPositionToStop(tab);
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
			subway.setName("Metro_"+str);
			subway.setStops(listStopsBySubway);	
			subway.setNumberOfStops(listStopsBySubway.size());
			nameSubway.put(subway.getName(), subway);
			subways.add(subway);
			stops.addAll(listStopsBySubway);
		}
	}
	
	/**
	 * Add the position of each stop inside subways.
	 * @param tab
	 * @throws IOException
	 */
	public void addPositionToStop(String tab[]) throws IOException {
		for(String str : tab) {
			ReadFile readFile = new ReadFile();
			List<String> lines = readFile.read("documents/RATP_GTFS_METRO_" + str + "/stop_times.txt");
			Subway sub = getSubwayByName(nameSubway,"Metro_"+str);
			readFile.parseStopTimesFile(lines, sub,sub.getNumberOfStops());
		}
	}

	/**
	 * print()
	 */
	public void print() {
		for(Subway sub : getListSubways()) {
			System.out.println("Nombre de Stations pour le " + sub.getName() + " : " + sub.getNumberOfStops());
			System.out.println("Les Stations de " +  sub.getName() + " sont : ");
			for(Stop stop : sub.getStops()) {
				System.out.println("##### " + stop.getName() + ", ID : " + stop.getId() + ", Position dans " + sub.getName() + " : " + stop.getPositionInSubway());
				System.out.println("########## " + stop.getLatitude() + ", Long : " + stop.getLongitude());
			}
		}
		System.out.println(" ---------------------------------------------------------- ");
		System.out.println("Nombre total de Stations : " + getNumberOfStopInParis());
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
	public List<Edge> getEdges() {
		return edges;
	}

	/**
	 * setEdges()
	 * @param edges
	 */
	public void setEdges(List<Edge> edges) {
		this.edges = edges;
	}
}
