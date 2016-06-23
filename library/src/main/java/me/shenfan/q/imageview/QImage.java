package me.shenfan.q.imageview;

import android.widget.ImageView;

/**
 * Created by Sun on 2016/6/22.
 */
public class QImage {

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
        NorthWest("NorthWest"),North("North"),NorthEast("NorthEast")
        ,West("West"),Center("Center"),East("East")
        ,SouthWest("SouthWest"),South("South"),SouthEast("SouthEast");

        final String value;

        GravityType(String value){
            this.value = value;
        }
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
