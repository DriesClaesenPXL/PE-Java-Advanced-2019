package image;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrayscalePixelTest {

    @Test
    void getDifferenceBetweenTwoGrayscalePixelsVersionOne() {
        GrayscalePixel pix1 = new GrayscalePixel(50);
        GrayscalePixel pix2 = new GrayscalePixel(20);
        double distance = pix1.distance(pix2);
        assertEquals(30, distance);
    }

    @Test
    void getDifferenceBetweenTwoGrayscalePixelsVersionTwo() {
        GrayscalePixel pix1 = new GrayscalePixel(69);
        GrayscalePixel pix2 = new GrayscalePixel(420);
        double distance = pix1.distance(pix2);
        assertEquals(351, distance);
    }
}