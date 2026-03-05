package city;

public enum City {
	HAMBURG(0),
	BERLIN(289),
	MUNICH(778),
	LEIPZIG(396),
	DRESDEN(499),
	COLOGNE(425),
	ROME(1684),
	PARIS(929),
	WIEN(966),
	MADRID(2333);

	private final int distance;

	private City(int distance) {
		this.distance = distance;
	}

	/**
     * Get the distance from Hamburg to this city in kilometers.
     */	
	public int getDistance() {
		return distance;
	}
}