package me.shenfan.q.imageview;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Sun on 2016/6/23.
 */
public class QImageMogr2UrlBuilderTest {

    public static final String HOST = "http://v2test-10000812.image.myqcloud.com/tencentyunRestAPITest";

    @Test
    public void basic(){
        final String result = QImage.createMogr(HOST).toUrl();

        assertEquals(HOST + "?imageMogr2", result);
    }

    @Test
    public void testBasic(){
        final String result = QImage.createMogr(HOST)
                .autoOrient()
                .thumbnail(100, 100)
                .strip()
                .gravity(QImage.GravityType.Center)
                .cropWidth(100)
                .scrop()
                .rotate(180)
                .format(QImage.ImageFormat.GIF)
                .quality(80)
                .frameNumber(30)
                .interlace().toUrl();

        final String r = HOST + "?imageMogr2/auto-orient/thumbnail/100x100!/strip/gravity/Center" +
                "/scrop/100x/rotate/180/format/gif/quality/80/cgif/30/interlace/1";
        assertEquals(r, result);
    }

    @Test
    public void testThumbnailScaleeH(){
        final String result = QImage.createMogr(HOST)
                .thumbnailScaleeHeight(50)
                .toUrl();
        final String r = HOST + "?imageMogr2/thumbnail/!x50p";
        assertEquals(r, result);
    }

    @Test
    public void testThumbnailScaleeW(){
        final String result = QImage.createMogr(HOST)
                .thumbnailScaleeWidth(50)
                .toUrl();
        final String r = HOST + "?imageMogr2/thumbnail/!50px";
        assertEquals(r, result);
    }

    @Test
    public void testThumbnailWidthe(){
        final String result = QImage.createMogr(HOST)
                .thumbnailHeighte(50)
                .toUrl();
        final String r = HOST + "?imageMogr2/thumbnail/x50";
        assertEquals(r, result);
    }

    @Test
    public void testThumbnailHeighte(){
        final String result = QImage.createMogr(HOST)
                .thumbnailWidthe(50)
                .toUrl();
        final String r = HOST + "?imageMogr2/thumbnail/50x";
        assertEquals(r, result);
    }

    @Test
    public void testThumbnailEdgeeMax(){
        final String result = QImage.createMogr(HOST)
                .thumbnailEdgeeMax(200, 400)
                .toUrl();
        final String r = HOST + "?imageMogr2/thumbnail/200x400";
        assertEquals(r, result);
    }

    @Test
    public void testThumbnailEdgeeMin(){
        final String result = QImage.createMogr(HOST)
                .thumbnailEdgeeMin(200, 400)
                .toUrl();
        final String r = HOST + "?imageMogr2/thumbnail/!200x400r";
        assertEquals(r, result);
    }

    @Test
    public void testCrop(){
        final String result = QImage.createMogr(HOST)
                .crop(600, 600)
                .offset(20, 20)
                .quality(85)
                .toUrl();
        final String r = HOST + "?imageMogr2/crop/!600x600a20a20/quality/85";
        assertEquals(r, result);
    }

    @Test
    public void testCrop_(){
        final String result = QImage.createMogr(HOST)
                .crop(600, 600)
                .offset(-20, 20)
                .quality(85, true)
                .toUrl();
        final String r = HOST + "?imageMogr2/crop/!600x600-20a20/quality!/85";
        assertEquals(r, result);
    }

    @Test
    public void testFormat(){
        final String result = QImage.createMogr(HOST)
                .format(QImage.ImageFormat.JPG)
                .interlace()
                .toUrl();
        final String r = HOST + "?imageMogr2/format/jpg/interlace/1";
        assertEquals(r, result);
    }

    @Test
    public void testScrop(){
        final String result = QImage.createMogr(HOST)
                .crop(300, 400)
                .scrop()
                .toUrl();
        final String r = HOST + "?imageMogr2/scrop/300x400";
        assertEquals(r, result);
    }
}
