import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ReadFile {
	ArrayList<Stop> stops = new ArrayList<Stop>();
	ArrayList<String> nameStations = new ArrayList<String>();
	Map<String,List<Long>> map = new HashMap<String, List<Long>>();
	
	public List<String> readWithStop(String filePath) throws IOException {
		List<String> lines = new ArrayList<String>();
		try{	
	        BufferedReader buf = new BufferedReader(new FileReader(filePath));	
	        String line;
			while ((line=buf.readLine()) != null) {
				lines.add(line);
			}
			buf.close();
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
		return lines;
	}
	
	public ArrayList<Stop> parseStopsFile(List<String> lines) {
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
				List<Long> idList = new ArrayList<Long>();
				idList.add(Long.parseLong(parametersOfStation.get(0)));
				map.put(parametersOfStation.get(2).replace("\"", ""),idList);
			}

			if(!nameStations.contains(parametersOfStation.get(2).replace("\"", ""))) {
				nameStations.add(parametersOfStation.get(2).replace("\"", ""));	
				stop.setId(map.get(parametersOfStation.get(2).replace("\"", "")));
				stops.add(stop);
			}
	    }
		nameStations.clear();
		return stops;
	}
}
