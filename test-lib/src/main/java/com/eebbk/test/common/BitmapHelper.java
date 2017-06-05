package com.eebbk.test.common;

import android.graphics.Bitmap;

/**
 * 位图帮助类
 *
 * @author paopao
 */
public class BitmapHelper {

    private static int sum = 0;

    /**
     * 对比位图指纹数据，计算不相同的数据位
     *
     * @param bm1
     * @param bm2
     * @return 如果不超过5，就说明两张图片很相似；如果大于10，说明它们是两张不同的图片。
     */
    public static int compare(Bitmap bitmap1, Bitmap bitmap2) {
        String hash1 = getHash(bitmap1);
        String hash2 = getHash(bitmap2);

        int diff = 0;
        for (int i = 0; i < hash1.length(); i++) {
            if (hash1.charAt(i) != hash2.charAt(i)) {
                diff++;
            }
        }
        return diff;
    }

    public static String getHash(Bitmap bitmap) {
        Bitmap temp = Bitmap.createScaledBitmap(bitmap, 8, 8, false);
        int[] grayValues = reduceColor(temp);
        int average = sum / grayValues.length;
        String reslut = computeBits(grayValues, average);
        return reslut;
    }

    private static String computeBits(int[] grayValues, int average) {
        char[] result = new char[grayValues.length];
        for (int i = 0; i < grayValues.length; i++) {
            if (grayValues[i] < average)
                result[i] = '0';
            else
                result[i] = '1';
        }
        return new String(result);
    }

    private static int[] reduceColor(Bitmap bitmap) {
        sum = 0;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        int[] grayValues = new int[width * height];
        int[] pix = new int[width * height];
        bitmap.getPixels(pix, 0, width, 0, 0, width, height);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int x = j * width + i;
                int r = (pix[x] >> 16) & 0xff;
                int g = (pix[x] >> 8) & 0xff;
                int b = pix[x] & 0xff;
                int grayValue = (r * 30 + g * 59 + b * 11) / 100;
                sum += grayValue;
                grayValues[x] = grayValue;
            }
        }
        return grayValues;
    }
}
