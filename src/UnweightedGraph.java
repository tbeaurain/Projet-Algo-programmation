import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class UnweightedGraph {
	ArrayList<Stop> stops = new ArrayList<Stop>();
	ArrayList<String> nameStations = new ArrayList<String>();
	Map<String,List<Long>> map = new HashMap<String, List<Long>>();
	
	public ArrayList<Stop> createListOfStations(List<String> lines) {
		ArrayList<Map<String,Long>> idList = new ArrayList<Map<String,Long>>();
		
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

			
			
			if(map.containsKey(parametersOfStation.get(2).replace("\"", ""))) {
				map.get(parametersOfStation.get(2).replace("\"", "")).add(Long.parseLong(parametersOfStation.get(0)));			
			} else {
				List<Long> test = new ArrayList<Long>();
				test.add(Long.parseLong(parametersOfStation.get(0)));
				map.put(parametersOfStation.get(2).replace("\"", ""),test);
			}

			if(!nameStations.contains(parametersOfStation.get(2).replace("\"", ""))) {
				nameStations.add(parametersOfStation.get(2).replace("\"", ""));	
				stop.setId(map.get(parametersOfStation.get(2).replace("\"", "")));
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
