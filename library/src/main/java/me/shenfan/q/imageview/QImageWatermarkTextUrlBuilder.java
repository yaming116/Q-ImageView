package me.shenfan.q.imageview;

/**
 * Created by Sun on 2016/6/23.
 */
public final class QImageWatermarkTextUrlBuilder extends QImageWatermarkUrlBuilder {

    String encodeText;
    String font;
    int fontSize = QImage.ORIGINAL_SIZE;
    String fill;//text color
    int dissolve = QImage.ORIGINAL_SIZE;

    QImageWatermarkTextUrlBuilder(String host) {
        super(host);
    }

    /**
     * <href>https://www.qcloud.com/doc/product/275/%E4%B8%87%E8%B1%A1%E4%BC%98%E5%9B%BE%E6%94%AF%E6%8C%81%E5%AD%97%E4%BD%93%E5%88%97%E8%A1%A8</href>
     * @param font
     * @return
     */
    public QImageWatermarkTextUrlBuilder font(String font){
        this.font = font;
        return this;
    }

    public QImageWatermarkTextUrlBuilder fontSize(int fontSize){
        this.fontSize = fontSize;
        return this;
    }

    public QImageWatermarkTextUrlBuilder fill(String fill){
        this.fill = fill;
        return this;
    }

    public QImageWatermarkTextUrlBuilder dissolve(int dissolve){
        if (dissolve < 1 || dissolve > 100) {
            throw new IllegalArgumentException("dissolve must be between 1 and 101, inclusive.");
        }

        this.dissolve = dissolve;
        return this;
    }

    @Override
    public QImageWatermarkTextUrlBuilder offsetX(int dx) {
        super.offsetX(dx);
        return this;
    }

    @Override
    public QImageWatermarkTextUrlBuilder offsetY(int dy) {
        super.offsetY(dy);
        return this;
    }

    @Override
    public QImageWatermarkTextUrlBuilder offset(int dx, int dy) {
        super.offset(dx, dy);
        return this;
    }

    public QImageWatermarkTextUrlBuilder text(String text){
        if (text == null){
            throw new IllegalArgumentException("text == null");
        }
        encodeText = Base64.encodeUrl(text.getBytes());
        return this;
    }

    @Override
    public QImageWatermarkTextUrlBuilder gravity(QImage.GravityType gravityType) {
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

        if (fontSize != QImage.ORIGINAL_SIZE){
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

        if (dissolve != QImage.ORIGINAL_SIZE){
            append(builder)
                    .append("dissolve");
            append(builder)
                    .append(dissolve);
        }
    }
}
