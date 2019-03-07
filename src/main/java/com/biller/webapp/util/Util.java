package com.biller.webapp.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.security.MessageDigest;
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

    public static String makeHash(String text) throws Exception {
        MessageDigest md;
        md = MessageDigest.getInstance("MD5");
        byte[] md5hash = new byte[32];
        md.update(text.getBytes("iso-8859-1"), 0, text.length());
        md5hash = md.digest();
        return convertToHex(md5hash);
    }
    public static String makeHashSha1(String text) throws Exception {
        MessageDigest md;
        md = MessageDigest.getInstance("SHA-1");
        byte[] md5hash = new byte[32];
        md.update(text.getBytes("iso-8859-1"), 0, text.length());
        md5hash = md.digest();
        return convertToHex(md5hash);
    }

    private static String convertToHex(byte[] data) {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
            int halfbyte = (data[i] >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                if ((0 <= halfbyte) && (halfbyte <= 9)) {
                    buf.append((char) ('0' + halfbyte));
                } else {
                    buf.append((char) ('a' + (halfbyte - 10)));
                }
                halfbyte = data[i] & 0x0F;
            } while (two_halfs++ < 1);
        }
        return buf.toString();
    }
}
