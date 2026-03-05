package Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Distance.Calculation;
import Exceptions.CityNotFound;
import Exceptions.ItemNotFound;
import Orders.Order;
import Tanker.SmallTruck;
import Tanker.MediumTruck;
import Tanker.LargeTruck;
import Tanker.Truck;
import Tanks.Item;
import Tanks.Liquid;
import Validation.Validation;
import city.City;

public class main {

    public static void main(String[] args) {

    	// Create Scanner object to read user input
        Scanner scanner = new Scanner(System.in);

         // List to store all available items (liquids, gases, etc.)
        List<Item> allItems = new ArrayList<>();

        // Create Item objects and set their properties
        Item oxygen = new Item();
        oxygen.setName("Oxygen");
        oxygen.setRiskFactor(17);
        oxygen.setDensityPerGallon(0.00438); // Bitte anpassen, ich nehme Platzhalter

        Item hydrogen = new Item();
        hydrogen.setName("Hydrogen");
        hydrogen.setRiskFactor(18);
        hydrogen.setDensityPerGallon(0.000071);

        Item nitrogen = new Item();
        nitrogen.setName("Nitrogen");
        nitrogen.setRiskFactor(2);
        nitrogen.setDensityPerGallon(0.00303);

        Item propene = new Item();
        propene.setName("Propene");
        propene.setRiskFactor(20);
        propene.setDensityPerGallon(0.00431);

        Item carbonDioxide = new Item();
        carbonDioxide.setName("Carbon Dioxide");
        carbonDioxide.setRiskFactor(10);
        carbonDioxide.setDensityPerGallon(0.00410);

        Item methane = new Item();
        methane.setName("Methane");
        methane.setRiskFactor(18);
        methane.setDensityPerGallon(0.00190);

        // Create Liquid objects and set their properties
        Liquid benzeneSuperE95 = new Liquid();
        benzeneSuperE95.setName("Benzene Super E95");
        benzeneSuperE95.setRiskFactor(20);
        benzeneSuperE95.setDensityPerGallon(0.00313);

        Liquid benzeneSuper = new Liquid();
        benzeneSuper.setName("Benzene Super");
        benzeneSuper.setRiskFactor(20);
        benzeneSuper.setDensityPerGallon(0.00313);

        Liquid water = new Liquid();
        water.setName("Water");
        water.setRiskFactor(0);
        water.setDensityPerGallon(0.003785);

        Liquid milk = new Liquid();
        milk.setName("Milk");
        milk.setRiskFactor(0);
        milk.setDensityPerGallon(0.00388);

        // Add all items to the allItems list
        allItems.add(oxygen);
        allItems.add(hydrogen);
        allItems.add(nitrogen);
        allItems.add(propene);
        allItems.add(carbonDioxide);
        allItems.add(methane);
        allItems.add(benzeneSuperE95);
        allItems.add(benzeneSuper);
        allItems.add(water);
        allItems.add(milk);

        // Create list to hold trucks of different sizes
        List<Truck> trucks = new ArrayList<>();

        // Instantiate trucks of various sizes
        Truck smallTruck = new SmallTruck();
        Truck mediumTruck = new MediumTruck();
        Truck largeTruck = new LargeTruck();

        // Add trucks to the trucks list
        trucks.add(smallTruck);
        trucks.add(mediumTruck);
        trucks.add(largeTruck);

        
        try {
        	// Ask user for product name input
            System.out.print("Enter product name (Oxygen, Hydrogen, Nitrogen, Propene, Carbon Dioxide, Methane, Benzene Super E95, Benzene Super, Water, Milk): ");
            String productName = scanner.nextLine();

            // Validate the product exists in the list
            Item selectedItem = Validation.checkIfItemExist(productName, allItems);

            // Ask user for quantity in gallons
            System.out.print("Enter quantity in gallons: ");
            int gallons = Integer.parseInt(scanner.nextLine());

            // Ask user for destination city
            System.out.print("Enter destination city (Berlin, Munich, Leipzig, Dresden, Koeln, Rome, Paris, Wien, Madrid): ");
            String cityName = scanner.nextLine();

            // Validate city exists 
            City destinationCity = Validation.checkIfCityExist(cityName);

            // Create a new Order with the user inputs
            Order order = new Order(gallons, selectedItem, destinationCity);

            // Create Calculation object and add items, trucks, and order
            Calculation calculation = new Calculation();
            calculation.addItem(allItems.toArray(new Item[0]));
            calculation.addTruck(trucks.toArray(new Truck[0]));
            calculation.addOrder(order);

            // Find the best truck combination for the order
            calculation.findBestTruckCombo();

            // Calculate shipping cost for the order
            double shippingCost = calculation.shippingPrice(order);
            order.setShipmentCost(shippingCost);

            // Print order report and total shipping cost
            order.getOrderReport();
            System.out.printf("Total shipping cost: %.2f EUR\n", shippingCost);

        } catch (ItemNotFound | CityNotFound e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input for gallons. Please enter a valid number.");
        } finally {
            scanner.close();
        }
    }
}
