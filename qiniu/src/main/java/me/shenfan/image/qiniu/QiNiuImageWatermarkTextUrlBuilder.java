package me.shenfan.image.qiniu;

/**
 * Created by Sun on 2016/6/23.
 */
public final class QiNiuImageWatermarkTextUrlBuilder extends QiNiuImageWatermarkUrlBuilder {

    String encodeText;
    String font;
    int fontSize = QiNiuImage.ORIGINAL_SIZE;
    String fill;//text color
    int dissolve = QiNiuImage.ORIGINAL_SIZE;

    QiNiuImageWatermarkTextUrlBuilder(String host) {
        super(host);
    }

    /**
     * <href>https://support.qiniu.com/hc/kb/article/112878/</href>
     * @param font
     * @return
     */
    public QiNiuImageWatermarkTextUrlBuilder font(String font){
        this.font = font;
        return this;
    }

    public QiNiuImageWatermarkTextUrlBuilder fontSize(int fontSize){
        this.fontSize = fontSize;
        return this;
    }

    public QiNiuImageWatermarkTextUrlBuilder fill(String fill){
        this.fill = fill;
        return this;
    }

    public QiNiuImageWatermarkTextUrlBuilder dissolve(int dissolve){
        if (dissolve < 1 || dissolve > 100) {
            throw new IllegalArgumentException("dissolve must be between 1 and 101, inclusive.");
        }

        this.dissolve = dissolve;
        return this;
    }

    @Override
    public QiNiuImageWatermarkTextUrlBuilder offsetX(int dx) {
        super.offsetX(dx);
        return this;
    }

    @Override
    public QiNiuImageWatermarkTextUrlBuilder offsetY(int dy) {
        super.offsetY(dy);
        return this;
    }

    @Override
    public QiNiuImageWatermarkTextUrlBuilder offset(int dx, int dy) {
        super.offset(dx, dy);
        return this;
    }

    public QiNiuImageWatermarkTextUrlBuilder text(String text){
        if (text == null){
            throw new IllegalArgumentException("text == null");
        }
        encodeText = Base64.encodeUrl(text.getBytes());
        return this;
    }

    @Override
    public QiNiuImageWatermarkTextUrlBuilder gravity(QiNiuImage.GravityType gravityType) {
        super.gravity(gravityType);
        return this;
    }

    @Override
    protected int waterMarkType() {
        return 2;
    }

    @Override
    protected void build(StringBuilder builder) {
        if (encodeText != null){
            append(builder)
                    .append("text");
            append(builder)
                    .append(encodeText);
        }

        if (font != null){
            append(builder)
                    .append("font");
            append(builder)
                    .append(Base64.encodeUrl(font.getBytes()));
        }

        if (fontSize != QiNiuImage.ORIGINAL_SIZE){
            append(builder)
                    .append("fontsize");
            append(builder)
                    .append(fontSize);
        }

        if (fill != null){
            append(builder)
                    .append("fill");
            append(builder)
                    .append(Base64.encodeUrl(fill.getBytes()));
        }

        if (dissolve != QiNiuImage.ORIGINAL_SIZE){
            append(builder)
                    .append("dissolve");
            append(builder)
                    .append(dissolve);
        }
    }
}
