package city;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CityTest {
    @Test
    void getDistanceBetweenTwoCitiesVersionOne() {
        City c1 = new City("c1", 0, 0);
        City c2 = new City("c2", 5, 5);
        double distance = c1.distance(c2);
        assertEquals(785.729416898367, distance);
    }

    @Test
    void getDistanceBetweenTwoCitiesVersionTwo(){
        City c1 = new City("c1", 42, 69);
        City c2 = new City("c2", 69, 42);
        double distance = c1.distance(c2);
        assertEquals(3386.882515861661, distance);
    }
}