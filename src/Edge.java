public class Edge 
{
	private String from;
	private String to;
	private Double weight;

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

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}
}
