import java.util.ArrayList;
import java.util.List;


public class UnweightedGraph {
	ArrayList<Stop> stops = new ArrayList<Stop>();
	
	public ArrayList<Stop> createListOfStations(List<String> lines) {
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
			
			stop.setId(Long.parseLong(parametersOfStation.get(0)));
			stop.setName(parametersOfStation.get(2).replace("\"", ""));
			stop.setDescription(parametersOfStation.get(3).replace("\"", ""));
			stop.setLatitude(Float.parseFloat(parametersOfStation.get(4)));
			stop.setLongitude(Float.parseFloat(parametersOfStation.get(5)));
			if(!getListStops().contains(stop))
				getListStops().add(stop);
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
