package me.shenfan.q.imageview;

/**
 * Created by Sun on 2016/6/23.
 */
public final class QImageWatermarkImageUrlBuilder extends QImageWatermarkUrlBuilder {

    String encodeUrl;

    QImageWatermarkImageUrlBuilder(String host) {
        super(host);
    }

    @Override
    public QImageWatermarkImageUrlBuilder offsetX(int dx) {
        super.offsetX(dx);
        return this;
    }

    @Override
    public QImageWatermarkImageUrlBuilder offsetY(int dy) {
        super.offsetY(dy);
        return this;
    }

    @Override
    public QImageWatermarkImageUrlBuilder offset(int dx, int dy) {
        super.offset(dx, dy);
        return this;
    }

    public QImageWatermarkImageUrlBuilder image(String url){
        if (url == null){
            throw new IllegalArgumentException("url == null");
        }
        encodeUrl = Base64.encodeUrl(url.getBytes());
        return this;
    }

    @Override
    public QImageWatermarkImageUrlBuilder gravity(QImage.GravityType gravityType) {
        super.gravity(gravityType);
        return this;
    }

    @Override
    protected int waterMarkType() {
        return 1;
    }

    @Override
    protected void build(StringBuilder builder) {
        if (encodeUrl != null){
            append(builder)
                    .append("image");
            append(builder)
                    .append(encodeUrl);
        }
    }
}
