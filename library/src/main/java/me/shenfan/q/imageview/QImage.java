package me.shenfan.q.imageview;

import android.widget.ImageView;

/**
 * Created by Sun on 2016/6/22.
 */
public class QImage {

    /** Original size for image width or height. **/
    public static final int ORIGINAL_SIZE = Integer.MIN_VALUE;

    /** Image formats supported by QImageView.*/
    public static enum ImageFormat{
        JPG("jpg"), BMP("bmp"), GIF("gif"), PNG("png"), WEBP("webp"), YJPEG("yjpeg");

        final String value;

        ImageFormat(String value){
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
}
