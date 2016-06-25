package me.shenfan.image.qiniu;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Sun on 2016/6/23.
 */
public class QiNiuImageWaterMarkTest {

    private static final String HOST = "http://developer.qiniu.com/resource/logo-2.jpg";

    @Test
    public void testImageWaterMark(){
        final String result = QiNiuImage.createImageWater(HOST).toUrl();

        assertEquals(HOST + "?watermark/1", result);
    }

    @Test
    public void testImageWaterMarkContent(){
        final String result = QiNiuImage.createImageWater(HOST)
                .image("http://developer.qiniu.com/resource/logo-2.jpg")
                .gravity(QiNiuImage.GravityType.SouthWest)
                .toUrl();

        assertEquals(HOST + "?watermark/1/image/" + Base64.encodeUrl("http://developer.qiniu.com/resource/logo-2.jpg".getBytes()) +"/gravity/SouthWest", result);
    }


    @Test
    public void testImageTextMark(){
        final String result = QiNiuImage.createTextWater(HOST).toUrl();

        assertEquals(HOST + "?watermark/2", result);
    }

    @Test
    public void testTextWaterMarkContent() {
        final String result = QiNiuImage.createTextWater(HOST)
                .text("七牛")
                .fill("white")
                .fontSize(100)
                .dissolve(50)
                .gravity(QiNiuImage.GravityType.NorthEast)
                .offset(20, 20)
                .toUrl();
        assertEquals(HOST + "?watermark/2/text/" + Base64.encodeUrl("七牛".getBytes()) +
                "/fontsize/100/fill/d2hpdGU=/dissolve/50/gravity/NorthEast/dx/20/dy/20", result);
    }
}
