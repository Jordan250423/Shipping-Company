package Tanker;

public class LargeTruck extends Truck {

    public LargeTruck() {
        super("Large", 162 * 0.0254, 102 * 0.0254, 86000);
    }

    @Override
    public void printTruckInfo() {
        System.out.println("Truck Type: Large");
        super.printTruckInfo();
    }
}