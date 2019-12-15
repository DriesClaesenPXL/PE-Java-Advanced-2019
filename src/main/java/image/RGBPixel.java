package image;

import java.util.ArrayList;
import java.util.Collections;
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
                int greyScale = grayPixel.getGreyscale();
                if(greyScale >= 0 && greyScale < 64) {
                    grayPixel = new GrayscalePixel(32);
                } else if (greyScale >= 64 && greyScale < 128) {
                    grayPixel = new GrayscalePixel(96);
                } else if (greyScale >= 128 && greyScale < 192) {
                    grayPixel = new GrayscalePixel(160);
                } else {
                    grayPixel = new GrayscalePixel(224);
                }
                grayRow.add(grayPixel);
            }
            grayImage.add(grayRow);
        }
        return grayImage;
    }

    public static List<GrayscalePixel> sortGrayscale(List<List<GrayscalePixel>> list){
        /*List<List<GrayscalePixel>> sortedImage = new ArrayList<>();
        for (List<GrayscalePixel> row : list) {
            List<Integer> sortedIntegerRow = new ArrayList<>();
            for (GrayscalePixel pixel : row) {
                sortedIntegerRow.add(pixel.getGreyscale());
            }
            Collections.sort(sortedIntegerRow);
            Collections.reverse(sortedIntegerRow);

            List<GrayscalePixel> sortedGrayRow = new ArrayList<>();
            for (Integer sortedInt : sortedIntegerRow) {
                GrayscalePixel sortedGrayPixel = new GrayscalePixel(sortedInt);
                sortedGrayRow.add(sortedGrayPixel);
            }
            sortedImage.add(sortedGrayRow);
        }*/

        List<GrayscalePixel> pixelList = new ArrayList<>();
        list.forEach(pixelList::addAll);

        List<Integer> sortedIntegers = new ArrayList<>();
        for (GrayscalePixel pixel : pixelList) {
            sortedIntegers.add(pixel.getGreyscale());
        }
        Collections.sort(sortedIntegers);

        pixelList = new ArrayList<>();
        for (Integer integer : sortedIntegers) {
            GrayscalePixel pixel = new GrayscalePixel(integer);
            pixelList.add(pixel);
        }
        return pixelList;
    }
}
