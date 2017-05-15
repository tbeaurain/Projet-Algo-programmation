import java.io.IOException;


public class Main {
	public final static String  tab[] = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","3b","7b","Fun","Orv"};
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws IOException {
		ReadFile readFile = new ReadFile();
		UnweightedGraph graph = new UnweightedGraph();
		graph.createAllStop(tab);
		System.out.println();
		System.out.println(" ----------------------------- ");
		System.out.println();
		for(Subway sub : graph.getListSubways()) {
			System.out.println("Nombre de Stations pour le " + sub.getName() + " : " + sub.getNumberOfStops());
			System.out.println("Les Stations de " +  sub.getName() + " sont : ");
			for(Stop stop : sub.getStops()) {
				System.out.println("Station : " + stop.getName() + " ID : " + stop.getId());
			}
		}
	}
}
