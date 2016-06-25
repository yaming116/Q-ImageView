package me.shenfan.image.qiniu;

/**
 * Created by Sun on 2016/6/23.
 */
public final class QiNiuImageMogr2UrlBuilder {
    private static final String TAG = "imageMogr2";
    private final String host;

    boolean autoOrient;
    boolean strip;
    boolean interlace;
    boolean hasOver;

    String gravity;
    String format;
    String thumbnail;
    String blur;
    String sizeLimit;

    int amount = QiNiuImage.ORIGINAL_QUALITY;

    int rotate = QiNiuImage.ORIGINAL_SIZE;
    int width = QiNiuImage.ORIGINAL_SIZE;
    int height = QiNiuImage.ORIGINAL_SIZE;
    int dx = QiNiuImage.ORIGINAL_SIZE;
    int dy = QiNiuImage.ORIGINAL_SIZE;


    QiNiuImageMogr2UrlBuilder(String host){
        this.host = Utils.checkHost(host);
    }

    public String getHost() {
        return host;
    }

    public QiNiuImageMogr2UrlBuilder autoOrient(){
        this.autoOrient = true;
        return this;
    }

    public QiNiuImageMogr2UrlBuilder strip(){
        this.strip = true;
        return this;
    }

    public QiNiuImageMogr2UrlBuilder interlace(){
        this.interlace = true;
        return this;
    }

    public QiNiuImageMogr2UrlBuilder gravity(QiNiuImage.GravityType gravityType){
        if (gravityType == null){
            gravity = null;
            return this;
        }
        this.gravity = gravityType.value;
        return this;
    }

    public QiNiuImageMogr2UrlBuilder format(QiNiuImage.ImageFormat format){
        if (format != null){
            this.format = format.value;
        }else {
            this.format = null;
        }
        return this;
    }

    public QiNiuImageMogr2UrlBuilder blur(int radius, int sigma){
        if (radius < 1 || radius > 50) {
            throw new IllegalArgumentException("Radius must be between 1 and 50, inclusive.");
        }

        if (sigma < 0){
            throw new IllegalArgumentException("sigma must be > 0");
        }

        if (QiNiuImage.ImageFormat.GIF.value.equals(format)){
            new UnsupportedOperationException("Not support gif");
        }
        this.blur = radius + "x" + sigma;
        return this;
    }

    /**
     * This filter changes the overall quality of the image.
     *
     * @param amount 0 to 100 - The quality level (in %) that the end image will feature.
     * @throws IllegalArgumentException if {@code amount} outside bounds.
     */

    public QiNiuImageMogr2UrlBuilder quality(int amount, boolean over){
        if (amount < 0 || amount > 100) {
            throw new IllegalArgumentException("Quality must be between 0 and 100, inclusive.");
        }
        hasOver = over;
        this.amount = amount;
        return this;
    }

    public QiNiuImageMogr2UrlBuilder quality(int amount){
        return quality(amount, false);
    }

    public QiNiuImageMogr2UrlBuilder rotate(int rotate){
        if (rotate < 1 || rotate > 360) {
            throw new IllegalArgumentException("Rotate must be between 1 and 360, inclusive.");
        }

        this.rotate = rotate;
        return this;
    }

    public QiNiuImageMogr2UrlBuilder cropWidth(int width){
        return crop(width, QiNiuImage.ORIGINAL_SIZE);
    }

    public QiNiuImageMogr2UrlBuilder cropHeight(int height){
        return crop(QiNiuImage.ORIGINAL_SIZE, height);
    }

    public QiNiuImageMogr2UrlBuilder crop(int width, int height){
        if ((width < 10 || width > 16383) && width != QiNiuImage.ORIGINAL_SIZE){
            throw new IllegalArgumentException("Width must be between 10 and 16383, inclusive.");
        }

        if ((height < 10 || height > 16383) && height != QiNiuImage.ORIGINAL_SIZE){
            throw new IllegalArgumentException("Height must be between 10 and 16383, inclusive.");
        }

        this.width = width;
        this.height = height;
        return this;
    }

    public QiNiuImageMogr2UrlBuilder offsetX(int dx){
        return offset(dx, QiNiuImage.ORIGINAL_SIZE);
    }

    public QiNiuImageMogr2UrlBuilder offsetY(int dy){
        return offset(QiNiuImage.ORIGINAL_SIZE, dy);
    }

    public QiNiuImageMogr2UrlBuilder offset(int dx, int dy){
        boolean c = false;

        if (width == QiNiuImage.ORIGINAL_SIZE && height == QiNiuImage.ORIGINAL_SIZE){
            throw new UnsupportedOperationException("you mast call this after crop");
        }
        if (width != QiNiuImage.ORIGINAL_SIZE && Math.abs(dx) >= width){
            throw new IllegalArgumentException("width < dx");
        }

        if (height != QiNiuImage.ORIGINAL_SIZE && Math.abs(dx) >= height){
            throw new IllegalArgumentException("height < dy");
        }
        this.dx = dx;
        this.dy = dy;
        return this;
    }

    public QiNiuImageMogr2UrlBuilder thumbnail(int percent){
        if ((percent < 0 || percent > 1000)){
            throw new IllegalArgumentException("Percent must be between 0 and 1000, inclusive.");
        }
        thumbnail = "!" + percent + "p";
        return this;
    }

    public QiNiuImageMogr2UrlBuilder thumbnail(int width, int height){
        thumbnail = width + "x" + height + "!";
        return this;
    }

    public QiNiuImageMogr2UrlBuilder thumbnailScaleWidth(int widthPercent){
        if ((widthPercent < 0 || widthPercent > 1000)){
            throw new IllegalArgumentException("WidthPercent must be between 0 and 1000, inclusive.");
        }
        thumbnail = "!" + widthPercent + "px";
        return this;
    }

    public QiNiuImageMogr2UrlBuilder thumbnailScaleHeight(int heightPercent){
        if ((heightPercent < 0 || heightPercent > 1000)){
            throw new IllegalArgumentException("HeightPercent must be between 0 and 1000, inclusive.");
        }
        thumbnail = "!x" + heightPercent + "p";
        return this;
    }

    public QiNiuImageMogr2UrlBuilder thumbnailWidthe(int width){
        if ((width < 0 || width > 1000)){
            throw new IllegalArgumentException("Width must be between 0 and 1000, inclusive.");
        }
        thumbnail = width + "x";
        return this;
    }

    public QiNiuImageMogr2UrlBuilder thumbnailHeighte(int height){
        if ((height < 0 || height > 1000)){
            throw new IllegalArgumentException("Height must be between 0 and 1000, inclusive.");
        }
        thumbnail = "x" + height;
        return this;
    }

    public QiNiuImageMogr2UrlBuilder thumbnailMax(int width, int height){
        thumbnail = width + "x" + height + "<";
        return this;
    }

    public QiNiuImageMogr2UrlBuilder thumbnailMin(int width, int height){
        thumbnail = width + "x" + height + ">";
        return this;
    }

    public QiNiuImageMogr2UrlBuilder thumbnailEdgeeMax(int width, int height){
        if ((width < 0 || width > 1000)){
            throw new IllegalArgumentException("Width must be between 0 and 1000, inclusive.");
        }
        if ((height < 0 || height > 1000)){
            throw new IllegalArgumentException("Height must be between 0 and 1000, inclusive.");
        }
        thumbnail = width + "x" + height;
        return this;
    }

    public QiNiuImageMogr2UrlBuilder thumbnailEdgeeMin(int width, int height){
        if ((width < 0 || width > 1000)){
            throw new IllegalArgumentException("Width must be between 0 and 1000, inclusive.");
        }
        if ((height < 0 || height > 1000)){
            throw new IllegalArgumentException("Height must be between 0 and 1000, inclusive.");
        }
        thumbnail = "!" + width + "x" + height + "r";
        return this;
    }

    public QiNiuImageMogr2UrlBuilder thumbnailArea(long area){
        if (area < 0){
            throw new IllegalArgumentException("area < 0");
        }
        thumbnail = area + "@";
        return this;
    }


    public QiNiuImageMogr2UrlBuilder sizeLimit(int limit){
        if (format == null){
            throw new UnsupportedOperationException("You must call this after format");
        }
        if (!QiNiuImage.ImageFormat.JPG.value.equals(format)){
            throw new UnsupportedOperationException("Not support gif");
        }
        sizeLimit = limit + "k";
        return this;
    }

    private StringBuilder append(StringBuilder builder){
        return builder.append("/");
    }

    public String toUrl(){
        StringBuilder builder = new StringBuilder(host);


        builder.append("?")
                .append(TAG);

        if (autoOrient){
            append(builder);
            builder.append("auto-orient");
        }
        if (thumbnail != null){
            append(builder);
            builder.append("thumbnail");
            append(builder);
            builder.append(thumbnail);
        }
        if (strip){
            append(builder);
            builder.append("strip");
        }
        if (gravity != null){
            append(builder);
            builder.append("gravity");
            append(builder);
            builder.append(gravity);
        }


        if (width != QiNiuImage.ORIGINAL_SIZE || height != QiNiuImage.ORIGINAL_SIZE){
            append(builder)
                    .append("crop");
            if (dx != QiNiuImage.ORIGINAL_SIZE || dy != QiNiuImage.ORIGINAL_SIZE){
                append(builder);
                builder.append("!");
            }else {
                append(builder);
            }

            if (width != QiNiuImage.ORIGINAL_SIZE && height != QiNiuImage.ORIGINAL_SIZE){
                builder.append(width)
                        .append("x")
                        .append(height);
            }else if(width != QiNiuImage.ORIGINAL_SIZE ){
                builder.append(width)
                        .append("x");
            }else {
                builder.append("x")
                        .append(height);
            }
            if (dx != QiNiuImage.ORIGINAL_SIZE){
                if (dx < 0){
                    builder.append(dx);
                }else {
                    builder.append("a");
                    builder.append(dx);
                }
            }


            if (dx != QiNiuImage.ORIGINAL_SIZE){
                if (dy < 0){
                    builder.append(dy);
                }else {
                    builder.append("a");
                    builder.append(dy);
                }
            }
        }

        if (rotate != QiNiuImage.ORIGINAL_SIZE){
            append(builder);
            builder.append("rotate");
            append(builder);
            builder.append(rotate);
        }

        if (format != null){
            append(builder)
                    .append("format");
            append(builder)
                    .append(format);
        }

        if (blur != null){
            append(builder)
                    .append("blur");
            append(builder)
                    .append(blur);
        }

        if (interlace){
            append(builder)
                    .append("interlace");
            append(builder)
                    .append(1);

        }

        if (amount != QiNiuImage.ORIGINAL_QUALITY){
            append(builder)
                    .append("quality");

            if (hasOver){
                builder.append("!");
            }

            append(builder)
                    .append(amount);
        }

        if (sizeLimit != null){
            append(builder)
                    .append("size-limit");
            append(builder)
                    .append(sizeLimit);
        }
        return builder.toString();
    }
}
