package me.shenfan.image.qiniu;

import org.junit.Test;

import static org.junit.Assert.*;
/**
 * Created by Sun on 2016/6/25.
 */
public class QiNiuImageMog2UrlBuilderTest {

    private static final String HOST = "http://78re52.com1.z0.glb.clouddn.com/resource/gogopher.jpg";

    private QiNiuImageMogr2UrlBuilder create(){
        return QiNiuImage.createMogr2(HOST);
    }

    @Test
    public void thumbnailPercent(){
        final String r = HOST + "?imageMogr2/thumbnail/!75p";

        final String result = create()
                .thumbnail(75).toUrl();

        assertEquals(r, result);
    }

    @Test
    public void thumbnailScaleWidth(){
        final String r =HOST +  "?imageMogr2/thumbnail/!75px";
        final String result = create().thumbnailScaleWidth(75).toUrl();
        assertEquals(r, result);

    }

    @Test
    public void thumbnailScaleHeight(){
        final String r = HOST + "?imageMogr2/thumbnail/!x75p";
        final String result = create().thumbnailScaleHeight(75).toUrl();
        assertEquals(r, result);

    }

    @Test
     public void thumbnailWidth(){
        final String r = HOST + "?imageMogr2/thumbnail/700x";
        final String result = create().thumbnailWidthe(700).toUrl();
        assertEquals(r, result);
    }

    @Test
    public void thumbnailHeight(){
        final String r = HOST + "?imageMogr2/thumbnail/x467";
        final String result = create().thumbnailHeighte(467).toUrl();
        assertEquals(r, result);
    }

    @Test
    public void thumbnailWH(){
        final String r = HOST + "?imageMogr2/thumbnail/300x300";
        final String result = create().thumbnailEdgeeMax(300, 300).toUrl();
        assertEquals(r, result);
    }

    @Test
    public void thumbnailEdgeeMin(){
        final String r = HOST + "?imageMogr2/thumbnail/!200x200r";
        final String result = create().thumbnailEdgeeMin(200, 200).toUrl();
        assertEquals(r, result);
    }

    @Test
    public void thumbnailForce(){
        final String r = HOST + "?imageMogr2/thumbnail/200x300!";
        final String result = create().thumbnail(200, 300).toUrl();
        assertEquals(r, result);
    }

    @Test
    public void thumbnailMax(){
        final String r = HOST + "?imageMogr2/thumbnail/200x300>";
        final String result = create().thumbnailMin(200, 300).toUrl();
        assertEquals(r, result);
    }

    @Test
    public void thumbnailMin(){
        final String r = HOST + "?imageMogr2/thumbnail/700x600<";
        final String result = create().thumbnailMax(700, 600).toUrl();
        assertEquals(r, result);
    }

    @Test
    public void thumbnailArea(){
        final String r = HOST + "?imageMogr2/thumbnail/350000@";
        final String result = create().thumbnailArea(350000).toUrl();
        assertEquals(r, result);
    }

    @Test
    public void crop(){
        final String r = HOST + "?imageMogr2/crop/300x";
        final String result = create().cropWidth(300).toUrl();
        assertEquals(r, result);
    }

    @Test
    public void cropHeight(){
        final String r = HOST + "?imageMogr2/crop/x200";
        final String result = create().cropHeight(200).toUrl();
        assertEquals(r, result);
    }

    @Test
    public void cropWH(){
        final String r = HOST + "?imageMogr2/crop/300x300";
        final String result = create().crop(300, 300).toUrl();
        assertEquals(r, result);
    }

    @Test
    public void cropOffset(){
        final String r = HOST + "?imageMogr2/crop/!300x300a30a100";
        final String result = create().crop(300, 300)
                .offset(30, 100).toUrl();
        assertEquals(r, result);
    }

    @Test
    public void cropOffset_(){
        final String r = HOST + "?imageMogr2/crop/!300x300a30-100";
        final String result = create().crop(300, 300)
                .offset(30, -100).toUrl();
        assertEquals(r, result);
    }

    @Test
    public void cropOffsetA(){
        final String r = HOST + "?imageMogr2/crop/!300x300-30a100";
        final String result = create().crop(300, 300)
                .offset(-30, 100).toUrl();
        assertEquals(r, result);
    }

    @Test
    public void cropOffset__(){
        final String r = HOST + "?imageMogr2/crop/!300x300-30-100";
        final String result = create().crop(300, 300)
                .offset(-30, -100).toUrl();
        assertEquals(r, result);
    }

    @Test
    public void testGravity(){
        final String r = HOST + "?imageMogr2/gravity/NorthWest/crop/300x300";
        final String result = create().crop(300, 300)
                .gravity(QiNiuImage.GravityType.NorthWest).toUrl();
        assertEquals(r, result);
    }

    @Test
    public void rotate(){
        final String r = HOST + "?imageMogr2/rotate/45";
        final String result = create().rotate(45).toUrl();
        assertEquals(r, result);
    }

    @Test
    public void testBlur(){
        final String r = HOST + "?imageMogr2/blur/3x5";
        final String result = create().blur(3, 5).toUrl();
        assertEquals(r, result);
    }

    @Test
    public void thumbnailWHI(){
        final String r = HOST + "?imageMogr2/thumbnail/300x300/interlace/1";
        final String result = create().
                thumbnailEdgeeMax(300, 300)
                .interlace().toUrl();
        assertEquals(r, result);
    }
}
