Q-ImageView
====

[![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](https://github.com/yaming116/UpdateApp/blob/master/LICENSE)

是时候解放手机的一些图片操作了，现在云服务器处理已经很强大了，下面对腾讯和七牛两家的图片处理做了一下封装。

|名称|获取图片基本信息|获取图片色调|图片EXIF信息|基本图片处理|
|---:|:----:|:----:|:----:|:----:|
|腾讯|支持|支持|支持|图片的缩放（不支持渐进显示）|
|七牛|支持|支持|支持|图片的缩放|


|名称|高级图片处理|文字水印|图片水印|
|---:|:----:|:----:|:----:|
|腾讯|支持（不支持高斯模糊、图片旋转背景会加一层灰色）|支持（不支持水印图片透明度）|支持（不支持图个水印）|
|七牛|支持（不支持GIF，不支持人脸识别）|支持|支持|

qcloud是对腾讯优图Restful Api图片处理的封装，具体文档[详见](https://www.qcloud.com/doc/product/275/RESTful%20API#8-.E5.9B.BE.E5.83.8F.E5.A4.84.E7.90.86)
目前支持 *imageView2*, *imageMogr2*, *图片水印*, *文字水印*等。

qiniu是对七牛图片处理Api的封装，具体文档[详见](http://developer.qiniu.com/code/v6/api/kodo-api/index.html#image)
目前支持 *imageView2*, *imageMogr2*, *图片水印*, *文字水印*等。

Use
===

# 腾讯优图

基本图片处理

[接口详细信息](https://www.qcloud.com/doc/product/275/RESTful%20API#8.1-.E5.9F.BA.E6.9C.AC.E5.9B.BE.E5.83.8F.E5.A4.84.E7.90.86.EF.BC.88imageview2.EF.BC.89)

```
download_url?imageView2/<mode>/w/<Width>/h/<Height>
                          /format/<Format>
                          /q/<Quality>

```
```java
        
        final String result = "www.test.com?imageView2/2/w/100/h/100/format/jpg/q/80";
        final String url = QImage.create(host)
                .resize(100, 100)
                .mode(2)
                .quality(80)
                .format(QImage.ImageFormat.JPG).toUrl();
```

高级图片处理

[接口详细信息](https://www.qcloud.com/doc/product/275/RESTful%20API#8.2-.E9.AB.98.E7.BA.A7.E5.9B.BE.E5.83.8F.E5.A4.84.E7.90.86.EF.BC.88imagemogr2.EF.BC.89)

```
  download_url?imageMogr2/auto-orient
                         /thumbnail/<imageSizeGeometry>
                         /strip
                         /gravity/<gravityType>
                         /crop/<imageSizeAndOffsetGeometry>
                         /scrop/<imageSizeAndOffsetGeometry>
                         /rotate/<rotateDegree>
                         /format/<Format>
                         /quality/<Quality>
                         /cgif/<FrameNumber>
                         /interlace/<Mode>
```

```java
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

        final String r = HOST + "?imageMogr2/auto-orient/thumbnail/100x100!/strip/gravity/center" +
                "/scrop/100x/rotate/180/format/gif/quality/80/cgif/30/interlace/1";
        assertEquals(r, result);
```

图片水印

```java
final String result = QImage.createImageWater(HOST)
                .image("http://tengxunyun-10004486.image.myqcloud.com/shuiyin_2.png")
                .gravity(QImage.GravityType.SouthWest)
                .toUrl();

        assertEquals(HOST + "?watermark/1/image/aHR0cDovL3Rlbmd4dW55dW4tMTAwMDQ0ODYuaW1hZ2UubXlxY2xvdWQuY29tL3NodWl5aW5fMi5wbmc=/gravity/southwest", result);
    
```

文字水印

```java
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
```

 [更多使用](./library/src/test/java/me/shenfan/q/imageview)

# 七牛

基本图片处理

[接口详细信息](http://developer.qiniu.com/code/v6/api/kodo-api/image/imageview2.html)

```java

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
```

高级图片处理

[接口详细信息](http://developer.qiniu.com/code/v6/api/kodo-api/image/imagemogr2.html)

```java
//裁剪
@Test
    public void testGravity(){
        final String r = HOST + "?imageMogr2/gravity/NorthWest/crop/300x300";
        final String result = create().crop(300, 300)
                .gravity(QiNiuImage.GravityType.NorthWest).toUrl();
        assertEquals(r, result);
    }
    //旋转
    @Test
    public void rotate(){
        final String r = HOST + "?imageMogr2/rotate/45";
        final String result = create().rotate(45).toUrl();
        assertEquals(r, result);
    }    
```


图片水印

[接口详细信息](http://developer.qiniu.com/code/v6/api/kodo-api/image/watermark.html)

```java
    @Test
    public void testImageWaterMarkContent(){
        final String result = QiNiuImage.createImageWater(HOST)
                .image("http://developer.qiniu.com/resource/logo-2.jpg")
                .gravity(QiNiuImage.GravityType.SouthWest)
                .toUrl();

        assertEquals(HOST + "?watermark/1/image/" + Base64.encodeUrl("http://developer.qiniu.com/resource/logo-2.jpg".getBytes()) +"/gravity/SouthWest", result);
    }

```

文字水印

```java
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

```


### Gradle

```groovy

allprojects {
    repositories {
        jcenter()
    }
}

dependencies {
    compile 'me.shenfan.image:qcloud:1.0.0'
    
    compile 'me.shenfan.image:qiniu:1.0.0'
}
```

License
-------

    Copyright (C) 2011 花开堪折枝 Software Ltd

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

