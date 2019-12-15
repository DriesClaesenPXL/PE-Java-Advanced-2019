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
        /*RGBPixel prussianBlue = new RGBPixel(0, 48, 80);
        RGBPixel desaturatedCyan = new RGBPixel(112, 150, 160);
        RGBPixel peachYellow = new RGBPixel(250, 227, 173);
        RGBPixel lava = new RGBPixel(218, 20, 21);*/
        List<RGBPixel> faireyColors = Arrays.asList(prussianBlue, lava, desaturatedCyan, peachYellow);

        List<List<GrayscalePixel>> grayImage = RGBPixel.convertToGrayscale(ImageReader.readImage(Paths.get("src/main/resources/tokio.jpg")));

        List<GrayscalePixel> pixelList = new ArrayList<>();
        grayImage.forEach(pixelList::addAll);

        List<GrayscalePixel> sortedPixelList = RGBPixel.sortGrayscale(pixelList);

        ImageWriter.writeImage(Paths.get("src/main/resources/grayscale.jpg"), grayImage);

        TreeSet<GrayscalePixel> grayscalePixels = new TreeSet<>(sortedPixelList);

        Map <GrayscalePixel, RGBPixel> testMap = new HashMap<>();
        GrayscalePixel testPixel1 = new GrayscalePixel(32);
        GrayscalePixel testPixel3 = new GrayscalePixel(32);
        RGBPixel testPixel2 = new RGBPixel(15,15,15);
        testMap.put(testPixel1, testPixel2);


        //Map map = createTranslationMap(faireyColors, grayscalePixels);
        System.out.println(testMap);
        System.out.println(testMap.get(testPixel3));
        //ImageWriter.writeImage(Paths.get("src/main/resources/simpleGrayscale.jpg"), ConvertToSimpleGrayscale(grayImage, map));

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

    private static List<List<GrayscalePixel>> ConvertToSimpleGrayscale (List<List<GrayscalePixel>> grayscaleImage, Map map) {
        List<List<GrayscalePixel>> grayImage = new ArrayList<>();
        for (List<GrayscalePixel> row : grayscaleImage) {
            List<GrayscalePixel> grayRow = new ArrayList<>();
            for (GrayscalePixel pixel : row) {
                int greyScale = pixel.getGreyscale();
                if(greyScale >= 0 && greyScale < 64) {

                    pixel = new GrayscalePixel(32);

                } else if (greyScale >= 64 && greyScale < 128) {

                    pixel = new GrayscalePixel(96);

                } else if (greyScale >= 128 && greyScale < 192) {

                    pixel = new GrayscalePixel(160);

                } else {

                    pixel = new GrayscalePixel(224);

                }
                grayRow.add(pixel);
            }
            grayImage.add(grayRow);
        }
        return grayImage;
    }
}
