package city;

import java.util.Comparator;
import java.util.NavigableSet;
import java.util.TreeSet;

public class DistancesBetweenCities {

	public static void main(String[] args) {
		City leuven = new City("Leuven", 50.88151970000001, 4.6967578);
		City roermond = new City("Roermond", 51.19417, 5.9875);
		City maastricht = new City("Maastricht", 50.84833, 5.68889);
		City aken = new City("Aken", 50.77664, 6.08342);

		Comparator<City> comp = (o1, o2) -> Integer.compare(o1.getName().compareTo(o2.getName()), 0);

		NavigableSet<City> cities = new TreeSet<>(comp);
		cities.add(leuven);
		cities.add(roermond);
		cities.add(maastricht);
		cities.add(aken);

		System.out.println(cities);

		City Alken = new City("Alken", 50.87609, 5.30767);
		cities.add(Alken);
		System.out.println(cities);

		for (City city:cities) {
			//TODO
		}
	}

}
