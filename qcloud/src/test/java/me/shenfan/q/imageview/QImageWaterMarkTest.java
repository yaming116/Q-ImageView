package me.shenfan.q.imageview;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Sun on 2016/6/23.
 */
public class QImageWaterMarkTest {

    private static final String HOST = "http://v2test-10000812.image.myqcloud.com/tencentyunRestAPITest";

    @Test
    public void testImageWaterMark(){
        final String result = QImage.createImageWater(HOST).toUrl();

        assertEquals(HOST + "?watermark/1", result);
    }

    @Test
    public void testImageWaterMarkContent(){
        final String result = QImage.createImageWater(HOST)
                .image("http://tengxunyun-10004486.image.myqcloud.com/shuiyin_2.png")
                .gravity(QImage.GravityType.SouthWest)
                .toUrl();

        assertEquals(HOST + "?watermark/1/image/aHR0cDovL3Rlbmd4dW55dW4tMTAwMDQ0ODYuaW1hZ2UubXlxY2xvdWQuY29tL3NodWl5aW5fMi5wbmc=/gravity/southwest", result);
    }


    @Test
    public void testImageTextMark(){
        final String result = QImage.createTextWater(HOST).toUrl();

        assertEquals(HOST + "?watermark/2", result);
    }

    @Test
    public void testTextWaterMarkContent() {
        final String result = QImage.createTextWater(HOST)
                .text("优图")
                .fill("white")
                .fontSize(100)
                .dissolve(50)
                .gravity(QImage.GravityType.NorthEast)
                .offset(20, 20)
                .toUrl();
        assertEquals(HOST + "?watermark/2/text/" + Base64.encodeUrl("优图".getBytes()) +
                "/fontsize/100/fill/d2hpdGU=/dissolve/50/gravity/northeast/dx/20/dy/20", result);
    }
}
