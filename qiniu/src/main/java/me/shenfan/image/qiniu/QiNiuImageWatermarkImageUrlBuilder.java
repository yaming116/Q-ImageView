package me.shenfan.image.qiniu;

/**
 * Created by Sun on 2016/6/23.
 */
public final class QiNiuImageWatermarkImageUrlBuilder extends QiNiuImageWatermarkUrlBuilder {

    String encodeUrl;
    float ws;

    QiNiuImageWatermarkImageUrlBuilder(String host) {
        super(host);
    }

    @Override
    public QiNiuImageWatermarkImageUrlBuilder offsetX(int dx) {
        super.offsetX(dx);
        return this;
    }

    @Override
    public QiNiuImageWatermarkImageUrlBuilder offsetY(int dy) {
        super.offsetY(dy);
        return this;
    }

    @Override
    public QiNiuImageWatermarkImageUrlBuilder offset(int dx, int dy) {
        super.offset(dx, dy);
        return this;
    }

    public QiNiuImageWatermarkImageUrlBuilder image(String url){
        if (url == null){
            throw new IllegalArgumentException("url == null");
        }
        encodeUrl = Base64.encodeUrl(url.getBytes());
        return this;
    }

    @Override
    public QiNiuImageWatermarkImageUrlBuilder gravity(QiNiuImage.GravityType gravityType) {
        super.gravity(gravityType);
        return this;
    }

    @Override
    protected int waterMarkType() {
        return 1;
    }

    public QiNiuImageWatermarkImageUrlBuilder ws(float ws){
        if (ws < 0 || ws > 1) {
            throw new IllegalArgumentException("dissolve must be between 0 and 1, inclusive.");
        }

        this.ws = ws;
        return this;
    }

    @Override
    protected void build(StringBuilder builder) {
        if (encodeUrl != null){
            append(builder)
                    .append("image");
            append(builder)
                    .append(encodeUrl);
        }

        if (ws != 0){
            append(builder)
                    .append("ws");
            append(builder)
                    .append(ws);
        }
    }
}
