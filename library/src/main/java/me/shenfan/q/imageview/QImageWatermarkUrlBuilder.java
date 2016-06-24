package me.shenfan.q.imageview;

/**
 * Created by Sun on 2016/6/23.
 */
public abstract class QImageWatermarkUrlBuilder {
    protected static final String TAG = "watermark";
    protected final String host;
    String gravity;

    int dx = QImage.ORIGINAL_SIZE;
    int dy = QImage.ORIGINAL_SIZE;

    QImageWatermarkUrlBuilder(String host){
        this.host = Utils.checkHost(host);
    }

    public String getHost() {
        return host;
    }

    public QImageWatermarkUrlBuilder offsetX(int dx){
        return offset(dx, QImage.ORIGINAL_SIZE);
    }

    public QImageWatermarkUrlBuilder offsetY(int dy){
        return offset(QImage.ORIGINAL_SIZE, dy);
    }

    public QImageWatermarkUrlBuilder offset(int dx, int dy){
        this.dx = dx;
        this.dy = dy;
        return this;
    }

    public QImageWatermarkUrlBuilder gravity(QImage.GravityType gravityType){
        if (gravityType == null){
            gravity = null;
            return this;
        }
        this.gravity = gravityType.value;
        return this;
    }

    protected abstract int waterMarkType();

    protected abstract void build(StringBuilder builder);

    protected StringBuilder append(StringBuilder builder){
        return builder.append("/");
    }

    public String toUrl(){
        StringBuilder builder = new StringBuilder(host);


        builder.append("?")
                .append(TAG);

        append(builder)
                .append(waterMarkType());

        build(builder);

        if (gravity != null){
            append(builder);
            builder.append("gravity");
            append(builder);
            builder.append(gravity);
        }

        if (dx != QImage.ORIGINAL_SIZE){
            append(builder)
                    .append("dx");
            append(builder)
                    .append(dx);
        }

        if (dx != QImage.ORIGINAL_SIZE){
            append(builder)
                    .append("dy");
            append(builder)
                    .append(dy);
        }

        final String result = builder.toString();

        if (QImage.DEBUG){

        }
        return builder.toString();
    }
}
