package image;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class ImageArt {

    public static void main(String[] args) throws IOException {

        RGBPixel sepia1 = new RGBPixel(86, 36, 54);
        RGBPixel sepia2 = new RGBPixel(107, 73, 21);
        RGBPixel sepia3 = new RGBPixel(197, 168, 160);
        RGBPixel sepia4 = new RGBPixel(216, 201, 173);
        List<RGBPixel> faireyColors = Arrays.asList(sepia1, sepia2, sepia3, sepia4);

        List<List<GrayscalePixel>> grayImage = RGBPixel.convertToGrayscale(Objects.requireNonNull(ImageReader.readImage(Paths.get("src/main/resources/me.jpg"))));

        List<GrayscalePixel> pixelList = new ArrayList<>();
        grayImage.forEach(pixelList::addAll);

        List<GrayscalePixel> sortedPixelList = RGBPixel.sortGrayscale(pixelList);

        ImageWriter.writeImage(Paths.get("src/main/resources/grayscaledMe.jpg"), grayImage);

        TreeSet<GrayscalePixel> grayscalePixels = new TreeSet<>(sortedPixelList);

        Map map = createTranslationMap(faireyColors, grayscalePixels);
        ImageWriter.writeImage(Paths.get("src/main/resources/faireyfiedMe.jpg"), ConvertToFairey(grayImage, sortedPixelList));

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
                    colorPixel = new RGBPixel(86, 36, 54);

                } else if (greyScale >= 64 && greyScale < 128) {

                    //pixel = new GrayscalePixel(96);
                    colorPixel = new RGBPixel(107, 73, 21);

                } else if (greyScale >= 128 && greyScale < 192) {

                    //pixel = new GrayscalePixel(160);
                    colorPixel = new RGBPixel(197, 168, 160);

                } else {

                    //pixel = new GrayscalePixel(224);
                    colorPixel = new RGBPixel(216, 201, 173);

                }
                grayRow.add(colorPixel);
            }
            grayImage.add(grayRow);
        }
        return grayImage;
    }
}
