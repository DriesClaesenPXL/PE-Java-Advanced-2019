package image;

import common.DistanceFunction;

import java.awt.*;

public class GrayscalePixel implements PixelToInt, DistanceFunction<GrayscalePixel>, Comparable<GrayscalePixel> {
    private int greyscale;

    public GrayscalePixel(int greyscale) {
        this.greyscale = greyscale;
    }

    public int getGreyscale() {
        return greyscale;
    }

    @Override
    public int toRGB() {
        return new Color(greyscale, greyscale, greyscale).getRGB();
    }

    @Override
    public String toString() {
        return Integer.toString(greyscale);
    }

    @Override
    public double distance(GrayscalePixel value) {
        return Math.abs(this.greyscale - value.greyscale);
    }

    @Override
    public int compareTo(GrayscalePixel grayInt) {
        if(this.greyscale == grayInt.getGreyscale())
            return 0;
        else if (this.greyscale > grayInt.getGreyscale())
            return 1;
        else return -1;
    }
}
