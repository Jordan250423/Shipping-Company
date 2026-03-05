package Tanks;

public class Item {
	
	public Item() {
		
	}
	
    private String name;
	private double riskFactor;
	private double densityPerGallon; // kg per gallon
	private double volumeInCubicMeter;
	private double gallons;
	
	public void setGallons(double gallons) {
		this.gallons = gallons;
		calculateVolumeInCubicMeters();
	}

	// Converts gallons to cubic meters using conversion factor (1 gallon ≈ 0.00378541 m³)
	public void calculateVolumeInCubicMeters() {
		this.volumeInCubicMeter = this.gallons * 0.00378541;
	}

	// Getter for volume in cubic meters
    public double getVolumeInCubicMeter() {
		return this.volumeInCubicMeter;
	}
    
    // Setter for item name
	public void setName(String name) {
		this.name = name;
	}
	
	// Setter for risk factor
	public void setRiskFactor(double riskFactor) {
		this.riskFactor = riskFactor;
	}

	// Setter for density (kg per gallon)
	public void setDensityPerGallon(double densityPerGallon) {
		this.densityPerGallon = densityPerGallon;
	}

    public double getRiskFactor() { return riskFactor; }
    public String getName() { return name; }
    public double getDensityPerGallon() { return densityPerGallon; }

	public void printItemInfo() {
	System.out.println("Item: " + name);
	System.out.println("Risk Factor: " + riskFactor);
	System.out.println("Density (kg/gal): " + densityPerGallon);

    }
}