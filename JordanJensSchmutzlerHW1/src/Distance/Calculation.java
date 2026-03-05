package Distance;

import Orders.Order;
import Tanker.Truck;
import Tanks.Item;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Calculation {

    private final double RETURN_COST_PER_KM = 0.6;

    private List<Item> items = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();
    private List<Truck> availableTrucks = new ArrayList<>();

    public Calculation() {
    }

    public void addItem(Item... newItems) {
        this.items.addAll(Arrays.asList(newItems));
    }

    public void addTruck(Truck... newTrucks) {
        this.availableTrucks.addAll(Arrays.asList(newTrucks));
    }

    /**
     * Calculate the volume of a truck tank in cubic meters.
     */
    public double totalVolumeOfTruck(Truck tanker) {
        double radiusMeter = tanker.getRadius();
        double lengthMeter = tanker.getLength();
        return Math.PI * Math.pow(radiusMeter, 2) * lengthMeter;
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }

    public List<Item> getItems() {
        return this.items;
    }

    /**
     * Find the best combination of trucks to fulfill all orders.
     * Sort trucks by volume and try to fill volume and weight requirements.
     */
    public void findBestTruckCombo() {
        for (Order order : this.orders) {
            double itemTotalVolume = order.getItemToBeShipped().getVolumeInCubicMeter();
            double itemTotalWeight = order.getItemToBeShipped().getDensityPerGallon() * order.getGallonAmmount();

            availableTrucks.forEach(Truck::calculateVolume);

            availableTrucks.sort((a, b) -> Double.compare(b.getTruckVolumeInCubicMeter(), a.getTruckVolumeInCubicMeter()));

            System.out.println("\nTruck Distribution Plan for order: " + order.getItemToBeShipped().getName());

            double remainingVolume = itemTotalVolume;
            double remainingWeight = itemTotalWeight;

            order.getTrucksUsed().clear();
            List<String> truckSummary = new ArrayList<>();

            for (Truck truck : availableTrucks) {
                double truckVolume = truck.getTruckVolumeInCubicMeter();
                double truckWeight = truck.getMaxWeight();
                int count = 0;

                while (remainingVolume >= truckVolume || remainingWeight >= truckWeight) {
                    remainingVolume -= truckVolume;
                    remainingWeight -= truckWeight;
                    count++;

                    Truck usedTruck = cloneTruck(truck);
                    order.getTrucksUsed().add(usedTruck);
                }

                if (count > 0) {
                    truckSummary.add(String.format("%s x%d (%.2f m³, %.0f kg)", truck.getName(), count, truckVolume, truckWeight));
                }
            }

            if (remainingVolume > 0 || remainingWeight > 0) {
                availableTrucks.sort((a, b) -> Double.compare(a.getTruckVolumeInCubicMeter(), b.getTruckVolumeInCubicMeter()));

                boolean foundTruckForRemainder = false;
                for (Truck truck : availableTrucks) {
                    double truckVolume = truck.getTruckVolumeInCubicMeter();
                    double truckWeight = truck.getMaxWeight();

                    if (truckVolume >= remainingVolume && truckWeight >= remainingWeight) {
                        Truck usedTruck = cloneTruck(truck);
                        order.getTrucksUsed().add(usedTruck);
                        truckSummary.add(String.format("%s x1 (%.2f m³, %.0f kg)", truck.getName(), truckVolume, truckWeight));
                        remainingVolume = 0;
                        remainingWeight = 0;
                        foundTruckForRemainder = true;
                        break;
                    }
                }
                if (!foundTruckForRemainder) {
                    System.out.println("Warning: No single truck can carry the remaining cargo volume and weight.");
                }
            }

            truckSummary.forEach(System.out::println);

            if (remainingVolume > 0 || remainingWeight > 0) {
                System.out.printf("Remaining Volume: %.2f m³, Remaining Weight: %.2f kg\n", remainingVolume, remainingWeight);
            } else {
                System.out.println("All cargo allocated successfully.");
            }
        }
    }

    /**
     * Calculate the total shipping price for an order including return cost.
     */
    public double shippingPrice(Order order) {
        double risk = order.getItemToBeShipped().getRiskFactor() / 100.0;
        double cost = order.getGallonAmmount() * 0.01 * (1 + risk) * order.getCityToShip().getDistance();
        double returnCost = order.getCityToShip().getDistance() * RETURN_COST_PER_KM;
        return cost + returnCost;
    }

    /**
     * Print reports for all orders.
     */
    public void printOrder() {
        for (Order order : this.orders) {
            order.getOrderReport();
        }
    }

    /**
     * Create a new instance (clone) of a truck to avoid modifying the original.
     */
    private Truck cloneTruck(Truck truck) {
        Truck usedTruck = new Truck();
        usedTruck.setName(truck.getName());
        usedTruck.setLength(truck.getLength());
        usedTruck.setRadius(truck.getRadius());
        usedTruck.setMaxWeight(truck.getMaxWeight());
        usedTruck.calculateVolume();
        return usedTruck;
    }
}
