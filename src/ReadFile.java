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
	List<Map<Long, Long>> allIdToId = new ArrayList<Map<Long,Long>>();
	
	public final static String  tab[] = {"1","2","4","6","9"};
	
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

	public void parseStopTimesFile(List<String> lines, String strSubway, Subway sub) {
		List<String> test = new ArrayList<String>();
		for(int i =0; i<tab.length;i++) {
			test.add(tab[i]);
		}
			
		int previousPos = 0;
		Long previousIdStop = null;
		
		if(test.contains(strSubway)) {
			List<String> newLines = lines.subList(1, sub.getNumberOfStops()+1);
			for (String  line : newLines) {
				Map<Long, Long> idToId = new HashMap<Long, Long>();
				List<String> parameters = new ArrayList<String>();
				String[] param = line.split(",");
				for(String str : param) {
					parameters.add(str);
				}
				int pos = Integer.parseInt(parameters.get(4));
				Long idStop = Long.parseLong(parameters.get(3));
				idToId.put(previousIdStop, idStop);
				
				if(pos == previousPos+1 && previousIdStop != null) {
					if(!allIdToId.contains(idToId)) {
						Edge edge = new Edge();
						Edge edge2 = new Edge();
						UnweightedGraph.createAndgetListOfEdge(edge, previousIdStop, idStop);
						UnweightedGraph.createAndgetListOfEdge(edge2, idStop, previousIdStop);
						allIdToId.add(idToId);
					}	
				}
				previousPos = pos;
				previousIdStop = idStop;
			}
			
		} else {
			lines.remove(0);			
			for (String  line :lines ) {
				Map<Long, Long> idToId2 = new HashMap<Long, Long>();
				List<String> parameters = new ArrayList<String>();
				String[] param = line.split(",");
				for(String str : param) {
					parameters.add(str);
				}
				
				int pos = Integer.parseInt(parameters.get(4));
				Long idStop = Long.parseLong(parameters.get(3));
				idToId2.put(previousIdStop, idStop);
				
				if(pos == previousPos+1 && previousIdStop != null) {
					if(!allIdToId.contains(idToId2)) {
						Edge edge = new Edge();
						UnweightedGraph.createAndgetListOfEdge(edge, previousIdStop, idStop);
						allIdToId.add(idToId2);
					}	
				}
				previousPos = pos;
				previousIdStop = idStop;
			}	
		}
	}
}
