package me.shenfan.image.qiniu;

/**
 * Created by Sun on 2016/6/23.
 */
public abstract class QiNiuImageWatermarkUrlBuilder {
    protected static final String TAG = "watermark";
    protected final String host;
    String gravity;

    int dx = QiNiuImage.ORIGINAL_SIZE;
    int dy = QiNiuImage.ORIGINAL_SIZE;

    QiNiuImageWatermarkUrlBuilder(String host){
        this.host = Utils.checkHost(host);
    }

    public String getHost() {
        return host;
    }

    public QiNiuImageWatermarkUrlBuilder offsetX(int dx){
        return offset(dx, QiNiuImage.ORIGINAL_SIZE);
    }

    public QiNiuImageWatermarkUrlBuilder offsetY(int dy){
        return offset(QiNiuImage.ORIGINAL_SIZE, dy);
    }

    public QiNiuImageWatermarkUrlBuilder offset(int dx, int dy){
        this.dx = dx;
        this.dy = dy;
        return this;
    }

    public QiNiuImageWatermarkUrlBuilder gravity(QiNiuImage.GravityType gravityType){
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

        if (dx != QiNiuImage.ORIGINAL_SIZE){
            append(builder)
                    .append("dx");
            append(builder)
                    .append(dx);
        }

        if (dx != QiNiuImage.ORIGINAL_SIZE){
            append(builder)
                    .append("dy");
            append(builder)
                    .append(dy);
        }

        final String result = builder.toString();


        return builder.toString();
    }
}
