public class Edge 
{
	private String v;
	private String w;
	private Double weight;

	/**
	 * Constructor of Edge.
	 * @param v
	 * @param w
	 * @param weight
	 */
	public Edge(String v, String w, Double weight) {
		this.v = v;
		this.w = w;
		this.weight = weight;
	}

	/**
	 * from()
	 * @return v
	 */
	public String from() {
		return v;
	}

	/**
	 * to()
	 * @return w
	 */
	public String to() {
		return w;
	}

	/**
	 * getWeight()
	 * @return weight
	 */
	public Double getWeight() {
		return weight;
	}

	/**
	 * setWeight()
	 * @param weight
	 */
	public void setWeight(Double weight) {
		this.weight = weight;
	}
}
