package me.shenfan.q.imageview;

import android.media.Image;

/**
 * Created by Sun on 2016/6/22.
 */
public final class QImageView2UrlBuilder {

    private static final String TAG = "imageView2";

    private final String host;

    int resizeWidth;
    int resizeHeight;
    int mode;
    int amount = QImage.ORIGINAL_QUALITY;
    String format;
    boolean hasResize;
    boolean hasOver;

    QImageView2UrlBuilder(String host){
        this.host = Utils.checkHost(host);
    }

    public String getHost() {
        return host;
    }

    public QImageView2UrlBuilder resizeWidth(int width){
        return resize(width, QImage.ORIGINAL_SIZE);
    }

    public QImageView2UrlBuilder resizeHeight(int height){
        return resize(QImage.ORIGINAL_SIZE, height);
    }

    public QImageView2UrlBuilder resize(int width, int height){
        if (width < 0 && width != QImage.ORIGINAL_SIZE) {
            throw new IllegalArgumentException("Width must be a positive number.");
        }
        if (height < 0 && height != QImage.ORIGINAL_SIZE) {
            throw new IllegalArgumentException("Height must be a positive number.");
        }
        if (width == 0 && height == 0) {
            throw new IllegalArgumentException("Both width and height must not be zero.");
        }
        hasResize = true;
        resizeWidth = width;
        resizeHeight = height;
        return this;
    }

    /**
     * This filter changes the overall quality of the image.
     *
     * @param amount 0 to 100 - The quality level (in %) that the end image will feature.
     * @throws IllegalArgumentException if {@code amount} outside bounds.
     */
    public QImageView2UrlBuilder quality(int amount){
        if (amount < 0 || amount > 100) {
            throw new IllegalArgumentException("Quality must be between 0 and 100, inclusive.");
        }
        this.amount = amount;
        return this;
    }

    /**
     *
     * @param amount 0 to 100 - The quality level (in %) that the end image will feature.
     * @throws IllegalArgumentException if {@code amount} outside bounds.
     * @return
     */
    public QImageView2UrlBuilder overQuality(int amount){
        hasOver = true;
        return quality(amount);
    }

    public QImageView2UrlBuilder format(QImage.ImageFormat format){
        if (format != null){
            this.format = format.value;
        }else {
            this.format = null;
        }
        return this;
    }

    public QImageView2UrlBuilder mode(int mode){
        if (mode < 0 || mode > 5) {
            throw new IllegalArgumentException("Mode must be between 0 and 5, inclusive.");
        }
        this.mode = mode;
        return this;
    }

    private void buildMode(StringBuilder builder){
        builder.append("/")
                .append(mode);
    }

    /**
     *
     * @return
     */
    public String toUrl(){
        StringBuilder builder = new StringBuilder(host);

        builder.append("?")
                .append(TAG);

        if (hasResize){
            buildMode(builder);
            if (resizeWidth != QImage.ORIGINAL_SIZE){
                builder.append("/");
                builder.append("w");
                builder.append("/");
                builder.append(resizeWidth);
            }

            if (resizeHeight != QImage.ORIGINAL_SIZE){
                builder.append("/");
                builder.append("h");
                builder.append("/");
                builder.append(resizeHeight);
            }
        }

        if (format != null){
            builder.append("/")
                    .append("format")
                    .append("/")
                    .append(format);
        }

        if (amount != QImage.ORIGINAL_QUALITY){
            builder.append("/")
                    .append("q");

            if (hasOver){
                builder.append("!");
            }

            builder.append("/")
                    .append(amount);
        }

        return builder.toString();
    }
}
