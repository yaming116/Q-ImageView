package me.shenfan.image.qiniu;

import org.junit.Test;


import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */

public class QiNiuImageView2UrlBuilderUnitTest {

    @Test
    public void basic(){
        final String host = "www.test.com";
        final String url = QiNiuImage.create(host).toUrl();
        assertEquals(host + "?imageView2" , url);
    }


    @Test
     public void testResize(){
        final String host = "www.test.com";
        final String result = "www.test.com?imageView2/0/w/100/h/100";
        final String url = QiNiuImage.create(host).resize(100, 100).toUrl();

        assertEquals(result, url);
    }

    @Test
    public void testResizeW(){
        final String host = "www.test.com";
        final String result = "www.test.com?imageView2/0/w/100";
        final String url = QiNiuImage.create(host).resize(100, QiNiuImage.ORIGINAL_SIZE).toUrl();

        assertEquals(result, url);
    }

    @Test
    public void testResizeH(){
        final String host = "www.test.com";
        final String result = "www.test.com?imageView2/0/h/100";
        final String url = QiNiuImage.create(host).resize(QiNiuImage.ORIGINAL_SIZE, 100).toUrl();

        assertEquals(result, url);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testResizeException() {
        final String host = "www.test.com";
        QiNiuImage.create(host).resize(-1, 100).toUrl();

    }

    @Test
    public void testFormat(){
        final String host = "www.test.com";
        final String result = "www.test.com?imageView2/2/w/100/h/100/format/jpg";
        final String url = QiNiuImage.create(host).resize(100, 100)
                .mode(2)
                .format(QiNiuImage.ImageFormat.JPG).toUrl();
        assertEquals(result, url);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testModeException(){
        final String host = "www.test.com";
        QiNiuImage.create(host).mode(-1).toUrl();
    }

    @Test
    public void testQuality(){
        final String host = "www.test.com";
        final String result = "www.test.com?imageView2/2/w/100/h/100/format/jpg/q/80";
        final String url = QiNiuImage.create(host).resize(100, 100)
                .mode(2)
                .quality(80)
                .format(QiNiuImage.ImageFormat.JPG).toUrl();
        assertEquals(result, url);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQualityException(){
        final String host = "www.test.com";
        QiNiuImage.create(host).quality(-1).toUrl();
    }

    @Test
    public void testOverQuality(){
        final String host = "www.test.com";
        final String result = "www.test.com?imageView2/2/w/100/h/100/format/jpg/q/80!";
        final String url = QiNiuImage.create(host).resize(100, 100)
                .mode(2)
                .overQuality(80)
                .format(QiNiuImage.ImageFormat.JPG).toUrl();
        assertEquals(result, url);
    }
}