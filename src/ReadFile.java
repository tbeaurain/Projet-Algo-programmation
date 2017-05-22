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
	String[] subwayWithoutLoop = {"Metro_1","Metro_2",
			"Metro_3","Metro_4","Metro_5","Metro_6",
			"Metro_8","Metro_9","Metro_11","Metro_12",
			"Metro_14","Metro_3b","Metro_Fun","Metro_Orv"};
	/**
	 * Read Files.
	 * @param filePath
	 * @return lines
	 * @throws IOException
	 */
	public List<String> read(String filePath) throws IOException {
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
	
	/**
	 * Parse the file stops.txt.
	 * @param lines
	 * @return stops
	 */
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
			stop.setLatitude(Double.parseDouble(parametersOfStation.get(4)));
			stop.setLongitude(Double.parseDouble(parametersOfStation.get(5)));

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
		return stops;
	}
	
	/**
	 * Parse the file stop_times.txt.
	 * @param lines
	 * @param sub
	 * @param stopBySubwayCount
	 */
	public void parseStopTimesFile(List<String> lines, Subway sub, int stopBySubwayCount) {
		
		List<String> lineWithoutLoop = new ArrayList<String>();
		for(int i = 0; i<subwayWithoutLoop.length; i++) {
			lineWithoutLoop.add(subwayWithoutLoop[i]);
		}
		
		if(lineWithoutLoop.contains(sub.getName())) {
			List<String> newLines = lines.subList(1, stopBySubwayCount+1);
			
			for (String  line : newLines) {
				String[] params = line.split(",");
				List<String> parametersOfStation = new ArrayList<String>();
				for (String param : params) {
					parametersOfStation.add(param);	
				}
				
				for(Stop stop : sub.getStops()) {
					if(stop.getId().contains(Long.parseLong(parametersOfStation.get(3)))) {
						stop.setPositionInSubway(Integer.parseInt(parametersOfStation.get(4)));
					}
				}
			}
		}
		
	}
}
