import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class UnweightedGraph {
	ArrayList<Stop> stops = new ArrayList<Stop>();
	ArrayList<String> nameStations = new ArrayList<String>();
	//Map.Entry<String,List<Long>> map = (Entry<String, List<Long>>) new HashMap<String, List<Long>>();
	
	public ArrayList<Stop> createListOfStations(List<String> lines) {
		//List<Long> idList = new ArrayList<Long>();
		lines.remove(0);
		String newLine;
		for (String line : lines) {
			Stop stop = new Stop();
			newLine = line.replace(", ","-");
			String[] paramOfStations = newLine.split(",");
			List<String> parametersOfStation = new ArrayList<String>();
			for (String param : paramOfStations) {
				parametersOfStation.add(param);	
			}
			
			stop.setName(parametersOfStation.get(2).replace("\"", ""));
			stop.setDescription(parametersOfStation.get(3).replace("\"", ""));
			stop.setLatitude(Float.parseFloat(parametersOfStation.get(4)));
			stop.setLongitude(Float.parseFloat(parametersOfStation.get(5)));			
			//stop.setId(Long.parseLong(parametersOfStation.get(0)));
						
			if(!nameStations.contains(parametersOfStation.get(2).replace("\"", ""))) {
				nameStations.add(parametersOfStation.get(2).replace("\"", ""));
				getListStops().add(stop);
			}
	    }
		return getListStops();
	}
	
	public ArrayList<Stop> getListStops() {
		return stops;
	}
	
	public int getNumberOfStopInParis() {
		return getListStops().size();
	}
	
}
