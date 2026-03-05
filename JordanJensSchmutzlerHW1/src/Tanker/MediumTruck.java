package Tanker;

public class MediumTruck extends Truck {

    public MediumTruck() {
        super("Medium", 420 * 0.0254, 102 * 0.0254, 38000);
    }

    @Override
    public void printTruckInfo() {
        System.out.println("Truck Type: Medium");
        super.printTruckInfo();
    }
}