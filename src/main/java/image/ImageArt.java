package image;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class ImageArt {

    public static void main(String[] args) throws IOException {

        RGBPixel prussianBlue = new RGBPixel(0, 48, 80);
        RGBPixel desaturatedCyan = new RGBPixel(112, 150, 160);
        RGBPixel peachYellow = new RGBPixel(250, 227, 173);
        RGBPixel lava = new RGBPixel(218, 20, 21);
        List<RGBPixel> faireyColors = Arrays.asList(prussianBlue, lava, desaturatedCyan, peachYellow);

        List<List<GrayscalePixel>> grayImage = RGBPixel.convertToGrayscale(ImageReader.readImage(Paths.get("src/main/resources/tokio.jpg")));

        List<GrayscalePixel> pixelList = new ArrayList<>();
        grayImage.forEach(pixelList::addAll);

        List<GrayscalePixel> sortedPixelList = RGBPixel.sortGrayscale(pixelList);

        ImageWriter.writeImage(Paths.get("src/main/resources/grayscale.jpg"), grayImage);

        TreeSet<GrayscalePixel> grayscalePixels = new TreeSet<>(sortedPixelList);

        Map map = createTranslationMap(faireyColors, grayscalePixels);
        System.out.println(map);
        System.out.println();
        ImageWriter.writeImage(Paths.get("src/main/resources/fairey.jpg"), ConvertToFairey(grayImage, sortedPixelList));

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

    private static List<List<RGBPixel>> ConvertToFairey (List<List<GrayscalePixel>> grayscaleImage, List<GrayscalePixel> sortedPixelList) {
        List<List<RGBPixel>> grayImage = new ArrayList<>();
        for (List<GrayscalePixel> row : grayscaleImage) {
            List<RGBPixel> grayRow = new ArrayList<>();
            RGBPixel colorPixel = new RGBPixel(0,0,0);
            for (GrayscalePixel pixel : row) {
                int greyScale = pixel.getGreyscale();
                if(greyScale >= 0 && greyScale < 64) {

                    //pixel = new GrayscalePixel(32);
                    colorPixel = new RGBPixel(0, 48, 80);

                } else if (greyScale >= 64 && greyScale < 128) {

                    //pixel = new GrayscalePixel(96);
                    colorPixel = new RGBPixel(218, 20, 21);

                } else if (greyScale >= 128 && greyScale < 192) {

                    //pixel = new GrayscalePixel(160);
                    colorPixel = new RGBPixel(112, 150, 160);

                } else {

                    //pixel = new GrayscalePixel(224);
                    colorPixel = new RGBPixel(250, 227, 173);

                }
                grayRow.add(colorPixel);
            }
            grayImage.add(grayRow);
        }
        return grayImage;
    }
}
