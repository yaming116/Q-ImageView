package me.shenfan.image.qiniu;

/**
 * Created by Sun on 2016/6/23.
 */
public class QiNiuImage {
    /** Original size for image width or height. **/
    public static final int ORIGINAL_SIZE = Integer.MIN_VALUE;
    /** Original size for image quality. **/
    public static final int ORIGINAL_QUALITY = Integer.MIN_VALUE;

    /** Image formats supported by QImageView.*/
    public enum ImageFormat{
        JPG("jpg"),GIF("gif"), PNG("png"), WEBP("webp");

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
     * @return QiNiuImageViewUrlBuilder
     */
    public static QiNiuImageViewUrlBuilder create(String host){
        return new QiNiuImageViewUrlBuilder(host);
    }

    /**
     * Create a new instance for the specified host.
     * @param host
     * @return QiNiuImageViewUrlBuilder
     */
    public static QiNiuImageMogr2UrlBuilder createMogr2(String host){
        return new QiNiuImageMogr2UrlBuilder(host);
    }

    /**
     * Create a new instance for the specified host.
     * @param host
     * @return QImageWatermarkImageUrlBuilder
     */
    public static QiNiuImageWatermarkImageUrlBuilder createImageWater(String host){
        return new QiNiuImageWatermarkImageUrlBuilder(host);
    }

    /**
     * Create a new instance for the specified host.
     * @param host
     * @return QImageWatermarkTextUrlBuilder
     */
    public static QiNiuImageWatermarkTextUrlBuilder createTextWater(String host){
        return new QiNiuImageWatermarkTextUrlBuilder(host);
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
