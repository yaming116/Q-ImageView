package me.shenfan.image.qiniu;

/**
 * Created by Sun on 2016/6/23.
 */
public final class QiNiuImageViewUrlBuilder {
    private static final String TAG = "imageView2";

    private final String host;

    int resizeWidth;
    int resizeHeight;
    int mode;
    int amount = QiNiuImage.ORIGINAL_QUALITY;
    String format;
    boolean hasResize;
    boolean hasOver;
    boolean interlace;
    boolean ignoreError;

    QiNiuImageViewUrlBuilder(String host){
        this.host = Utils.checkHost(host);
    }

    public String getHost() {
        return host;
    }

    public QiNiuImageViewUrlBuilder resizeWidth(int width){
        return resize(width, QiNiuImage.ORIGINAL_SIZE);
    }

    public QiNiuImageViewUrlBuilder resizeHeight(int height){
        return resize(QiNiuImage.ORIGINAL_SIZE, height);
    }

    public QiNiuImageViewUrlBuilder resize(int width, int height){
        if (width < 0 && width != QiNiuImage.ORIGINAL_SIZE) {
            throw new IllegalArgumentException("Width must be a positive number.");
        }
        if (height < 0 && height != QiNiuImage.ORIGINAL_SIZE) {
            throw new IllegalArgumentException("Height must be a positive number.");
        }
        if (width == 0 && height == 0) {
            throw new IllegalArgumentException("Both width and height must not be zero.");
        }
        if (width > 9999 || height > 9999){
            throw new IllegalArgumentException("Both width and height must between 0 and 100," +
                    " inclusive.");
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
    public QiNiuImageViewUrlBuilder quality(int amount){
        if (amount < 1 || amount > 100) {
            throw new IllegalArgumentException("Quality must be between 1 and 100, inclusive.");
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
    public QiNiuImageViewUrlBuilder overQuality(int amount){
        hasOver = true;
        return quality(amount);
    }

    public QiNiuImageViewUrlBuilder format(QiNiuImage.ImageFormat format){
        if (format != null){
            this.format = format.value;
        }else {
            this.format = null;
        }
        return this;
    }

    public QiNiuImageViewUrlBuilder mode(int mode){
        if (mode < 0 || mode > 5) {
            throw new IllegalArgumentException("Mode must be between 0 and 5, inclusive.");
        }
        this.mode = mode;
        return this;
    }

    public QiNiuImageViewUrlBuilder interlace(){
        this.interlace = true;
        return this;
    }

    public QiNiuImageViewUrlBuilder ignoreError(){
        this.ignoreError = true;
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
            if (resizeWidth != QiNiuImage.ORIGINAL_SIZE){
                builder.append("/");
                builder.append("w");
                builder.append("/");
                builder.append(resizeWidth);
            }

            if (resizeHeight != QiNiuImage.ORIGINAL_SIZE){
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

        if (interlace){
            builder.append("/")
                    .append("interlace")
                    .append("/")
                    .append(1);
        }

        if (amount != QiNiuImage.ORIGINAL_QUALITY){
            builder.append("/")
                    .append("q");
            builder.append("/")
                    .append(amount);

            if (hasOver){
                builder.append("!");
            }
        }

        if (interlace){
            builder.append("/")
                    .append("ignore-error")
                    .append("/")
                    .append(1);
        }

        return builder.toString();
    }
}
