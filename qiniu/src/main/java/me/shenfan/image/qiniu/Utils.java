package me.shenfan.image.qiniu;

/**
 * Created by Sun on 2016/6/23.
 */
public class Utils {

    public static final String checkHost(String host){

        if (host == null || host.length() == 0) {
            throw new IllegalArgumentException("Host must not be blank.");
        }
        if (host.endsWith("/")) {
            host = host.substring(0, host.length() - 1);
        }

        return host;
    }
}
