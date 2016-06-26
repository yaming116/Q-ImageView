package me.shenfan.q.imageview;

import org.junit.Test;

import me.shenfan.q.imageview.QImage;
import me.shenfan.q.imageview.Utils;

import static org.junit.Assert.*;

/**
 * Created by Sun on 2016/6/25.
 */
public class QImageTest {
    private static final String HOST = "www.qcloud.com";

    @Test
    public void testImageInfo(){
        final String result = QImage.getImageInfo(HOST);

        assertEquals(HOST + "?imageInfo", result);
    }

    @Test
    public void testHost2(){
        final String r = Utils.checkHost(HOST);
        assertEquals(r, HOST);
    }

    @Test
    public void testHost3(){
        final String r = Utils.checkHost(HOST + "/");
        assertEquals(r, HOST);
    }

    @Test
    public void testExit(){
        final String result = QImage.getExif(HOST);

        assertEquals(HOST + "?exif", result);
    }

    @Test
    public void testImageAve(){
        final String result = QImage.getImageAve(HOST);

        assertEquals(HOST + "?imageAve", result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testHostException(){
        final String r = Utils.checkHost("");
        assertEquals(r, HOST);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testHostException2(){
        final String r = Utils.checkHost(null);
        assertEquals(r, HOST);
    }
}
