import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Main {
	public final static String  tab[] = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","3b","7b","Fun","Orv"};
	
	public static void main(String[] args) throws IOException {
		ReadFile readFile = new ReadFile();
		UnweightedGraph graph = new UnweightedGraph();
		
		for(String str : tab) {
			List<String> lines = readFile.readWithStop("documents/RATP_GTFS_METRO_" + str + "/stops.txt");
			ArrayList<Stop> listStops = graph.createListOfStations(lines);
			for(Stop stop : listStops) {
				System.out.println("Station : " + stop.getName());
			}
		}
		System.out.println("Nombre de Stations : " + graph.getNumberOfStopInParis());
	}

}
