package me.shenfan.q.imageview;

import android.media.Image;

/**
 * Created by Sun on 2016/6/22.
 */
public final class QImageView2UrlBuilder {
    private static final String TAG = "imageView2";
    private static final String FILTER_QUALITY = "q";
    private static final String FILTER_FORMAT = "format";


    private final String host;

    int resizeWidth;
    int resizeHeight;
    int mode;
    int amount = QImage.ORIGINAL_SIZE;
    String format;
    boolean hasResize;
    boolean hasOver;

    QImageView2UrlBuilder(String host){
        if (host == null || host.length() == 0) {
            throw new IllegalArgumentException("Host must not be blank.");
        }
        if (!host.endsWith("/")) {
            host += "/";
        }
        this.host = host;
    }

    public String getHost() {
        return host;
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
            throw new IllegalArgumentException("Amount must be between 0 and 100, inclusive.");
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
        }
        return this;
    }

    public QImageView2UrlBuilder mode(int mode){
        if (mode < 0 || mode > 100) {
            throw new IllegalArgumentException("Mode must be between 0 and 5, inclusive.");
        }
        this.mode = mode;
        return this;
    }

    /**
     *
     * @return
     */
    public String build(){
        StringBuilder builder = new StringBuilder();

        builder.append("?")
                .append(TAG)
                .append("/")
                .append(mode);

        if (hasResize){
            if (resizeWidth != QImage.ORIGINAL_SIZE){
                builder.append("/");
                builder.append("w");
                builder.append(resizeWidth);
            }

            if (resizeHeight != QImage.ORIGINAL_SIZE){
                builder.append("/");
                builder.append("h");
                builder.append(resizeHeight);
            }
        }

        if (format != null){
            builder.append("/")
                    .append("format")
                    .append("/")
                    .append(format);
        }

        if (amount != QImage.ORIGINAL_SIZE){
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
