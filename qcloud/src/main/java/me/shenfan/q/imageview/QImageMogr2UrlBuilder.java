package me.shenfan.q.imageview;

/**
 * Created by Sun on 2016/6/23.
 */
public final class QImageMogr2UrlBuilder {
    private static final String TAG = "imageMogr2";
    private final String host;

    boolean autoOrient;
    boolean strip;
    boolean scrop;
    boolean interlace;
    boolean hasOver;

    String gravity;
    String format;
    String thumbnail;

    int amount = QImage.ORIGINAL_QUALITY;

    int rotate = QImage.ORIGINAL_SIZE;
    int frameNumber = QImage.ORIGINAL_SIZE;
    int width = QImage.ORIGINAL_SIZE;
    int height = QImage.ORIGINAL_SIZE;
    int dx = QImage.ORIGINAL_SIZE;
    int dy = QImage.ORIGINAL_SIZE;


    QImageMogr2UrlBuilder(String host){
        this.host = Utils.checkHost(host);
    }

    public String getHost() {
        return host;
    }

    public QImageMogr2UrlBuilder autoOrient(){
        this.autoOrient = true;
        return this;
    }

    public QImageMogr2UrlBuilder strip(){
        this.strip = true;
        return this;
    }

    public QImageMogr2UrlBuilder scrop(){
        this.scrop = true;
        return this;
    }

    public QImageMogr2UrlBuilder interlace(){
        this.interlace = true;
        return this;
    }

    public QImageMogr2UrlBuilder gravity(QImage.GravityType gravityType){
        if (gravityType == null){
            gravity = null;
            return this;
        }
        if (gravityType == QImage.GravityType.South || gravityType == QImage.GravityType.West
                || gravityType == QImage.GravityType.North){
            throw new UnsupportedOperationException("South, West, North");
        }
        this.gravity = gravityType.value;
        return this;
    }

    public QImageMogr2UrlBuilder format(QImage.ImageFormat format){
        if (format != null){
            this.format = format.value;
        }else {
            this.format = null;
        }
        return this;
    }

    /**
     * This filter changes the overall quality of the image.
     *
     * @param amount 0 to 100 - The quality level (in %) that the end image will feature.
     * @throws IllegalArgumentException if {@code amount} outside bounds.
     */

    public QImageMogr2UrlBuilder quality(int amount, boolean over){
        if (amount < 0 || amount > 100) {
            throw new IllegalArgumentException("Quality must be between 0 and 100, inclusive.");
        }
        hasOver = over;
        this.amount = amount;
        return this;
    }

    public QImageMogr2UrlBuilder quality(int amount){
        return quality(amount, false);
    }

    public QImageMogr2UrlBuilder rotate(int rotate){
        if (rotate < 0 || rotate > 360) {
            throw new IllegalArgumentException("Rotate must be between 0 and 360, inclusive.");
        }

        this.rotate = rotate;
        return this;
    }

    public QImageMogr2UrlBuilder frameNumber(int frameNumber){
        if (frameNumber < 1 || frameNumber > 100) {
            throw new IllegalArgumentException("FrameNumber must be between 1 and 100, inclusive.");
        }

        this.frameNumber = frameNumber;
        return this;
    }

    public QImageMogr2UrlBuilder cropWidth(int width){
        return crop(width, QImage.ORIGINAL_SIZE);
    }

    public QImageMogr2UrlBuilder cropHeight(int height){
        return crop(QImage.ORIGINAL_SIZE, height);
    }

    public QImageMogr2UrlBuilder crop(int width, int height){
        if ((width < 10 || width > 16383) && width != QImage.ORIGINAL_SIZE){
            throw new IllegalArgumentException("Width must be between 10 and 16383, inclusive.");
        }

        if ((height < 10 || height > 16383) && height != QImage.ORIGINAL_SIZE){
            throw new IllegalArgumentException("Height must be between 10 and 16383, inclusive.");
        }

        this.width = width;
        this.height = height;
        return this;
    }

    public QImageMogr2UrlBuilder offsetX(int dx){
        return offset(dx, QImage.ORIGINAL_SIZE);
    }

    public QImageMogr2UrlBuilder offsetY(int dy){
        return offset(QImage.ORIGINAL_SIZE, dy);
    }

    public QImageMogr2UrlBuilder offset(int dx, int dy){
        boolean c = false;

        if (width == QImage.ORIGINAL_SIZE && height == QImage.ORIGINAL_SIZE){
            throw new UnsupportedOperationException("you mast call this after crop");
        }
        if (width != QImage.ORIGINAL_SIZE && Math.abs(dx) >= width){
            throw new IllegalArgumentException("width < dx");
        }

        if (height != QImage.ORIGINAL_SIZE && Math.abs(dx) >= height){
            throw new IllegalArgumentException("height < dy");
        }
        this.dx = dx;
        this.dy = dy;
        return this;
    }

    public QImageMogr2UrlBuilder thumbnail(int width, int height){
        thumbnail = width + "x" + height + "!";
        return this;
    }

    public QImageMogr2UrlBuilder thumbnailScaleeWidth(int width){
        thumbnail = "!" + width + "px";
        return this;
    }

    public QImageMogr2UrlBuilder thumbnailScaleeHeight(int height){
        thumbnail = "!x" + height + "p";
        return this;
    }

    public QImageMogr2UrlBuilder thumbnailWidthe(int width){
        thumbnail = width + "x";
        return this;
    }

    public QImageMogr2UrlBuilder thumbnailHeighte(int height){
        thumbnail = "x" + height;
        return this;
    }

    public QImageMogr2UrlBuilder thumbnailEdgeeMax(int width, int height){
        thumbnail = width + "x" + height;
        return this;
    }

    public QImageMogr2UrlBuilder thumbnailEdgeeMin(int width, int height){
        thumbnail = "!" + width + "x" + height + "r";
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

        if (scrop){

        }

        if (width != QImage.ORIGINAL_SIZE || height != QImage.ORIGINAL_SIZE){
            if (scrop){
                append(builder);
                builder.append("scrop");
            }else {
                append(builder);
                builder.append("crop");
            }

            if (dx != QImage.ORIGINAL_SIZE || dy != QImage.ORIGINAL_SIZE){
                append(builder);
                builder.append("!");
            }else {
                append(builder);
            }

            if (width != QImage.ORIGINAL_SIZE && height != QImage.ORIGINAL_SIZE){
                builder.append(width)
                        .append("x")
                        .append(height);
            }else if(width != QImage.ORIGINAL_SIZE ){
                builder.append(width)
                        .append("x");
            }else {
                builder.append("x")
                        .append(height);
            }
            if (dx != QImage.ORIGINAL_SIZE){
                if (dx < 0){
                    builder.append(dx);
                }else {
                    builder.append("a");
                    builder.append(dx);
                }
            }


            if (dx != QImage.ORIGINAL_SIZE){
                if (dy < 0){
                    builder.append(dy);
                }else {
                    builder.append("a");
                    builder.append(dy);
                }
            }
        }

        if (rotate != QImage.ORIGINAL_SIZE){
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

        if (amount != QImage.ORIGINAL_QUALITY){
            append(builder)
                    .append("quality");

            if (hasOver){
                builder.append("!");
            }

            append(builder)
                    .append(amount);
        }

        if (frameNumber != QImage.ORIGINAL_QUALITY){
            append(builder)
                    .append("cgif");

            append(builder)
                    .append(frameNumber);
        }

        if (interlace){
            append(builder)
                    .append("interlace");
            append(builder)
                    .append("1");
        }
        return builder.toString();
    }
}
