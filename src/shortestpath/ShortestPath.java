package shortestpath;

public class ShortestPath {
	
	private static boolean[] marked;
	private static int[] previous;
	private static int[] distance;
	
	public static boolean hasPathTo(int v) {
        return getMarked()[v];
    }
	
	public static int distTo(int v) {
        return getDistance()[v];
    }
	
	public static boolean[] getMarked() {
		return marked;
	}

	public static void setMarked(boolean[] mark) {
		marked = mark;
	}

	public static int[] getPrevious() {
		return previous;
	}

	public  static void setPrevious(int[] prev) {
		previous = prev;
	}

	public static int[] getDistance() {
		return distance;
	}

	public static void setDistance(int[] dist) {
		distance = dist;
	}
}
