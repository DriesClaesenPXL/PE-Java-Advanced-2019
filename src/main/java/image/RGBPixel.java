package image;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class RGBPixel implements PixelToInt {
    private int red;
    private int green;
    private int blue;

    public RGBPixel(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    @Override
    public int toRGB() {
        int rgb = red;
        rgb = (rgb << 8) + green;
        rgb = (rgb << 8) + blue;
        return rgb;
    }

    @Override
    public String toString() {
        return "(" + red + ", " + green + ", " + blue + ")";
    }

    private static int getAverage(RGBPixel pixel){
        return (pixel.red + pixel.blue + pixel.green)/3;
    }

    public static List<List<GrayscalePixel>> convertToGrayscale(List<List<RGBPixel>> list){
        List<List<GrayscalePixel>> grayImage = new ArrayList<>();
        for (List<RGBPixel> row : list) {
            List<GrayscalePixel> grayRow = new ArrayList<>();
            for (RGBPixel pixel : row) {
                GrayscalePixel grayPixel = new GrayscalePixel(getAverage(pixel));
                grayRow.add(grayPixel);
            }
            grayImage.add(grayRow);
        }
        return grayImage;
    }
}
