import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class UnweightedGraph {
	static ArrayList<Stop> stops = new ArrayList<Stop>();
	static ArrayList<Subway> subways = new ArrayList<Subway>();
	
	public static void createAllStop(String tab[]) throws IOException {
		ReadFile readFile = null;
		for(String str : tab) {
			readFile = new ReadFile();
			List<String> lines = readFile.readWithStop("documents/RATP_GTFS_METRO_" + str + "/stops.txt");
			ArrayList<Stop> listStopsBySubway = new ArrayList<Stop>();
			listStopsBySubway = readFile.parseStopsFile(lines);
			Subway subway = new Subway();
			subway.setName("Metro_"+str);
			subway.setStops(listStopsBySubway);	
			subway.setNumberOfStops(listStopsBySubway.size());
			subways.add(subway);
			stops.addAll(listStopsBySubway);
			System.out.println(" Première Station de " + subway.getName() + " : " + subway.getStops().get(0).getName());
		}
		System.out.println("Nombre de Stations : " + getNumberOfStopInParis());
	}
	
	public static List<Stop> getListStops() {
		return stops;
	}
	
	public static ArrayList<Subway> getListSubways() {
		return subways;
	}
	
	public static int getNumberOfStopInParis() {
		return getListStops().size();
	}

	
}
