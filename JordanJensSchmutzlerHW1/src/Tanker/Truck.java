package Tanker;

public class Truck {
	private String name;
    private double length;
	private double radius;
	private double volumeInCubicMeter;
	private double maxWeight;
	
	
	// Method to calculate the volume of the cylindrical tank
	public void calculateVolume() {
		double volume = Math.PI * Math.pow(this.radius, 2) * this.length;
		this.volumeInCubicMeter = volume;
	}
	
	// Constructor to initialize a truck with given parameters
	public Truck(String name, double length, double radius, double maxWeight) {
	    this.name = name;
	    this.length = length;
	    this.radius = radius;
	    this.maxWeight = maxWeight;
	}
	
	public Truck() {
	    // empty constructor, for cloneTruck 
	}
	
	
	public String toString() {
	    return name;
	}
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// Returns the truck's volume; calculates it if not already done
	public double getTruckVolumeInCubicMeter() {
		if (this.volumeInCubicMeter == 0.0) {
       		calculateVolume();
    	}
    	return this.volumeInCubicMeter;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

    public double getMaxWeight() {
		return maxWeight;
	}

	public void setMaxWeight(double maxWeight) {
		this.maxWeight = maxWeight;
	}

	// Prints important truck information to the console
	public void printTruckInfo() {
        System.out.printf("Truck length: %.1f m \n", length);
		System.out.printf("Truck Radius: %.1f m \n", radius);
        System.out.printf("Volume in gallons: %.2f m³\n", getTruckVolumeInCubicMeter());
    }

}