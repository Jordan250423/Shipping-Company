package Tanker;

public class SmallTruck extends Truck {

    public SmallTruck() {
        super("Small", 300 * 0.0254, 69 * 0.0254, 30000);
    }

    @Override
    public void printTruckInfo() {
        System.out.println("Truck Type: Small");
        super.printTruckInfo();
    }
}