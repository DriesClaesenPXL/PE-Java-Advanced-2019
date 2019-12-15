package image;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class ImageArt {

    public static void main(String[] args) throws IOException {

        //cool new colors
        RGBPixel prussianBlue = new RGBPixel(245, 66, 153);
        RGBPixel desaturatedCyan = new RGBPixel(245, 66, 200);
        RGBPixel peachYellow = new RGBPixel(245, 66, 233);
        RGBPixel lava = new RGBPixel(221, 66, 245);
        //lame old colors
        //RGBPixel prussianBlue = new RGBPixel(0, 48, 80);
        //RGBPixel desaturatedCyan = new RGBPixel(112, 150, 160);
        //RGBPixel peachYellow = new RGBPixel(250, 227, 173);
        //RGBPixel lava = new RGBPixel(218, 20, 21);
        List<RGBPixel> faireyColors = Arrays.asList(prussianBlue, lava, desaturatedCyan, peachYellow);

        ImageReader.readImage(Paths.get("src/main/resources/tokio.jpg"));

        List<List<GrayscalePixel>> grayImage = RGBPixel.convertToGrayscale(Objects.requireNonNull(ImageReader.readImage(Paths.get("src/main/resources/tokio.jpg"))));
        List<GrayscalePixel> sortedGrayImage = RGBPixel.sortGrayscale(grayImage);
        TreeSet<GrayscalePixel> grayscalePixels = new TreeSet<>(sortedGrayImage);
        System.out.println(grayscalePixels);
        //ImageWriter.writeImage(Paths.get("src/main/resources/grayImage.jpg"), sortedGrayImage);
    }

    private static Map<GrayscalePixel, RGBPixel> createTranslationMap(List<RGBPixel> faireyColors, TreeSet<GrayscalePixel> allGreyscalePixels) {
        int size = allGreyscalePixels.size() / faireyColors.size();

        Map<GrayscalePixel, RGBPixel> translationMap = new HashMap<>();
        Iterator<GrayscalePixel> iterator = allGreyscalePixels.iterator();
        int startIndex = size / 2;
        List<Integer> preferedIndeces = new ArrayList<>();
        for (int group = 0; group < faireyColors.size(); group++) {
            preferedIndeces.add(startIndex);
            startIndex += size;
        }
        int index = 0;
        while (iterator.hasNext()) {
            GrayscalePixel grayscalePixel = iterator.next();
            if (preferedIndeces.contains(index)) {
                int position = preferedIndeces.indexOf(index);
                translationMap.put(grayscalePixel, faireyColors.get(position));
            }
            index++;
        }
        return translationMap;
    }
}
