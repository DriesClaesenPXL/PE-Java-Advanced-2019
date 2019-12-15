package image;

import java.util.List;

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

    private int getAverage(RGBPixel pixel){
        return (pixel.red + pixel.blue + pixel.green)/3;
    }

    public List<GrayscalePixel> convertToGrayscale(List<List<RGBPixel>> list){
        List<RGBPixel> grayPixelList;
        List<List<RGBPixel>> returnImage = new List<List<RGBPixel>>();
        for (List pixelList : list) {
            grayPixelList = new List<RGBPixel>();
            for (RGBPixel pixel : pixelList) {
                grayPixelList.add(getAverage(pixel));
            }
            returnImage.add(grayPixelList);
        }
        return returnImage;
    }
}
