import java.io.IOException;


public class Main {

	public static void main(String[] args) throws IOException {
		ReadFile readFile = new ReadFile();
		readFile.read("documents/RATP_GTFS_METRO_1/stops.txt");

	}

}
