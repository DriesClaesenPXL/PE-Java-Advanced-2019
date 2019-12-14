package common;
import city.City;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

public class DistanceUtil {
    public static <T extends City> T findClosest (Set<T> elements, T otherElement){
        ArrayList<Double> distances = new ArrayList<Double>();
        T shortestCity = null;

        for (T element : elements){
            distances.add(element.distance(otherElement));
        }

        Collections.sort(distances);

        for (T element : elements) {
            if (element.distance(otherElement) == distances.get(0)) {
                shortestCity = element;
            }
        }
        return shortestCity;
    }
}
