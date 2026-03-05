package Validation;

import Exceptions.CityNotFound;
import Exceptions.ItemNotFound;
import Tanks.Item;
import city.City;

import java.util.List;

public class Validation {

	public static City checkIfCityExist(String cityName) throws CityNotFound {
    try {
        return City.valueOf(cityName.trim().toUpperCase());
    } catch (IllegalArgumentException e) {
        throw new CityNotFound("Invalid city name: " + cityName, e);
    }
}
	public static Item checkIfItemExist(String inputName, List<Item> allItems) throws ItemNotFound {
    for (Item item : allItems) {
        if (item.getName().equalsIgnoreCase(inputName.trim())) {
            return item;
        }
    }
    throw new ItemNotFound("Invalid item name: " + inputName);
}

}