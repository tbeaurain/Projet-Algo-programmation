import java.util.List;


public class Stop {

	private List<Long> id;
	private String name;
	private String description;
	private Double latitude;
	private Double longitude;
	private List<Stop> neighbors;
	private int positionInSubway;
	
	/**
	 * getId()
	 * @return id
	 */
	public List<Long> getId() {
		return id;
	}
	
	/**
	 * setId()
	 * @param id
	 */
	public void setId(List<Long> id) {
		this.id = id;
	}
	
	/**
	 * getName()
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * setName()
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * getDescription()
	 * @return description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 *  setDescription()
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * getLongitude()
	 * @return longitude
	 */
	public Double getLongitude() {
		return longitude;
	}
	
	/**
	 * setLongitude()
	 * @param longitude
	 */
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
	/**
	 * getLatitude()
	 * @return latitude
	 */
	public Double getLatitude() {
		return latitude;
	}
	
	/**
	 * setLatitude()
	 * @param latitude
	 */
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	
	/**
	 * getNeighbors()
	 * @return neighbors
	 */
	public List<Stop> getNeighbors() {
		return neighbors;
	}
	
	/**
	 * setNeighbors()
	 * @param neighbors
	 */
	public void setNeighbors(List<Stop> neighbors) {
		this.neighbors = neighbors;
	}
	
	/**
	 * getPositionInSubway()
	 * @return positionInSubway
	 */
	public int getPositionInSubway() {
		return positionInSubway;
	}
	
	/**
	 * setPositionInSubway()
	 * @param positionInSubway
	 */
	public void setPositionInSubway(int positionInSubway) {
		this.positionInSubway = positionInSubway;
	}
	
}
