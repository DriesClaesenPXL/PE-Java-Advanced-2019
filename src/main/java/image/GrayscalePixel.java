package image;

import common.DistanceFunction;

import java.awt.*;

public class GrayscalePixel implements PixelToInt, DistanceFunction<GrayscalePixel> {
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
        int diff = this.greyscale - value.greyscale;
        if (diff < 0){
            diff = (2*diff);
        }
        return diff;
    }
}
