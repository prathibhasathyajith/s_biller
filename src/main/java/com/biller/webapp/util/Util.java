package com.biller.webapp.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.UUID;

/**
 * Created by Prathibha Sathyajith on 10/15/2018.
 */
public class Util {

    public static String genareteUserId() {
        return DigestUtils
                .sha256Hex(
                        String.valueOf(System.currentTimeMillis())
                                + UUID.randomUUID()
                );
    }


    public static String genareteTempPassword() {
        return DigestUtils
                .md5Hex(
                        String.valueOf(System.currentTimeMillis()).getBytes()
                )
                .substring(0, 6);
    }
}
