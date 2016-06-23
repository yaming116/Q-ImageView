package me.shenfan.q.imageview;

import org.junit.Test;

import java.lang.String;

import me.shenfan.q.imageview.QImage;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */

public class QImageView2UrlBuilderUnitTest {

    @Test
    public void basic(){
        final String host = "www.test.com";
        final String url = QImage.create(host).toUrl();
        assertEquals(host + "?imageView2" , url);
    }


    @Test
     public void testResize(){
        final String host = "www.test.com";
        final String result = "www.test.com?imageView2/0/w/100/h/100";
        final String url = QImage.create(host).resize(100, 100).toUrl();

        assertEquals(result, url);
    }

    @Test
    public void testResizeW(){
        final String host = "www.test.com";
        final String result = "www.test.com?imageView2/0/w/100";
        final String url = QImage.create(host).resize(100, QImage.ORIGINAL_SIZE).toUrl();

        assertEquals(result, url);
    }

    @Test
    public void testResizeH(){
        final String host = "www.test.com";
        final String result = "www.test.com?imageView2/0/h/100";
        final String url = QImage.create(host).resize(QImage.ORIGINAL_SIZE, 100).toUrl();

        assertEquals(result, url);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testResizeException() {
        final String host = "www.test.com";
        QImage.create(host).resize(-1, 100).toUrl();

    }

    @Test
    public void testFormat(){
        final String host = "www.test.com";
        final String result = "www.test.com?imageView2/2/w/100/h/100/format/jpg";
        final String url = QImage.create(host).resize(100, 100)
                .mode(2)
                .format(QImage.ImageFormat.JPG).toUrl();
        assertEquals(result, url);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testModeException(){
        final String host = "www.test.com";
        QImage.create(host).mode(-1).toUrl();
    }

    @Test
    public void testQuality(){
        final String host = "www.test.com";
        final String result = "www.test.com?imageView2/2/w/100/h/100/format/jpg/q/80";
        final String url = QImage.create(host).resize(100, 100)
                .mode(2)
                .quality(80)
                .format(QImage.ImageFormat.JPG).toUrl();
        assertEquals(result, url);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQualityException(){
        final String host = "www.test.com";
        QImage.create(host).quality(-1).toUrl();
    }

    @Test
    public void testOverQuality(){
        final String host = "www.test.com";
        final String result = "www.test.com?imageView2/2/w/100/h/100/format/jpg/q!/80";
        final String url = QImage.create(host).resize(100, 100)
                .mode(2)
                .overQuality(80)
                .format(QImage.ImageFormat.JPG).toUrl();
        assertEquals(result, url);
    }
}