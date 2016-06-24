package me.shenfan.q.imageview;

import android.widget.ImageView;

/**
 * Created by Sun on 2016/6/22.
 */
public class QImage {

    public static boolean DEBUG = false;

    /** Original size for image width or height. **/
    public static final int ORIGINAL_SIZE = Integer.MIN_VALUE;
    /** Original size for image quality. **/
    public static final int ORIGINAL_QUALITY = Integer.MIN_VALUE;

    /** Image formats supported by QImageView.*/
    public enum ImageFormat{
        JPG("jpg"), BMP("bmp"), GIF("gif"), PNG("png"), WEBP("webp"), YJPEG("yjpeg");

        final String value;

        ImageFormat(String value){
            this.value = value;
        }
    }

    public enum GravityType{
        NorthWest("northwest"),North("north"),NorthEast("northeast")
        ,West("west"),Center("center"),East("east")
        ,SouthWest("southwest"),South("south"),SouthEast("southeast");

        final String value;

        GravityType(String value){
            this.value = value;
        }
    }

    public static void setDebug(boolean debug){
        DEBUG = debug;
    }

    /**
     * Create a new instance for the specified host.
     * @param host
     * @return QImageView2UrlBuilder
     */
    public static QImageView2UrlBuilder create(String host){
        return new QImageView2UrlBuilder(host);
    }

    /**
     * Create a new instance for the specified host.
     * @param host
     * @return QImageMogr2UrlBuilder
     */
    public static QImageMogr2UrlBuilder createMogr(String host){
        return new QImageMogr2UrlBuilder(host);
    }

    /**
     * Create a new instance for the specified host.
     * @param host
     * @return QImageWatermarkImageUrlBuilder
     */
    public static QImageWatermarkImageUrlBuilder createImageWater(String host){
        return new QImageWatermarkImageUrlBuilder(host);
    }

    /**
     * Create a new instance for the specified host.
     * @param host
     * @return QImageWatermarkTextUrlBuilder
     */
    public static QImageWatermarkTextUrlBuilder createTextWater(String host){
        return new QImageWatermarkTextUrlBuilder(host);
    }

    public static String getImageInfo(String host){
        final String h = Utils.checkHost(host);
        return h + "?imageInfo";
    }

    public static String getExif(String host){
        final String h = Utils.checkHost(host);
        return h + "?exif";
    }

    public static String getImageAve(String host){
        final String h = Utils.checkHost(host);
        return h + "?imageAve";
    }
}
