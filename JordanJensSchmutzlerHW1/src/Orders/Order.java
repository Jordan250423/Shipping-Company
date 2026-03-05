package Orders;

import Tanker.Truck;
import Tanks.Item;
import city.City;

import java.util.ArrayList;
import java.util.List;

public class Order {

	private City cityToShip; 
	private int gallonAmmount;
	private Item itemToBeShipped;
	private double shipmentCost;
	private List<Truck> trucksUsed; 
	
	public double getShipmentCost() {
		return shipmentCost;
	}

	public void setShipmentCost(double shipmentCost) {
		this.shipmentCost = shipmentCost;
	}

	public Order(int gallonAmmount, Item item, City city) {
		this.gallonAmmount = gallonAmmount;
		this.itemToBeShipped = item;
		this.cityToShip = city;
		this.trucksUsed = new ArrayList<>();
		item.setGallons(gallonAmmount);
	}
	
	public int getGallonAmmount() {
		return gallonAmmount;
	}

	public City getCityToShip() {
		return cityToShip;
	}

	public Item getItemToBeShipped() {
		return itemToBeShipped;
	}
	
	//"3200 gallons of Oxygen to be shipped from Hamburg to Berlin"
	public void getOrderReport() {
		System.out.printf("%d gallons of %s is to be shipped from Hamburg to %s \n", 
				this.gallonAmmount, itemToBeShipped.getName(), this.cityToShip);

		System.out.println("Used trucks:" + trucksUsed.toString());
	}

    public List<Truck> getTrucksUsed() {
        return trucksUsed;
    }

    public void setTrucksUsed(List<Truck> trucksUsed) {
        this.trucksUsed = trucksUsed;
    }

}