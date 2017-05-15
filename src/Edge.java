public class Edge 
{
	private String v;
	private String w;
	
	public Edge(String v, String w) {
		this.v = v;
		this.w = w;
	}

	public String either() {
		return v;
	}
	
	public String other() {
		return w;
	}
}
