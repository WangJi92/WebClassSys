package com.hdu.cms.common.Utils;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * Created by JetWang on 2016/10/10.
 */
public class RandomUtil {
    /**
     * 产生一个32位的唯一字符串
     *
     * @return
     */
    public static String getUUID32BIT() {
        return UUID.randomUUID().toString();
    }

    /**
     * 产生自定义长度的随机字符串
     * @param passLength
     * @param uniquetype
     * @return
     */
    public static String getUniqueNubmerString(int passLength, UNIQUETYPE uniquetype) {
        StringBuffer buffer = null;
        StringBuffer sb = new StringBuffer();
        Random r = new Random();
        r.setSeed(new Date().getTime());
        switch (uniquetype.getValue()) {
            case 0:
                buffer = new StringBuffer("0123456789");
                break;
            case 1:
                buffer = new StringBuffer("abcdefghijklmnopqrstuvwxyz");
                break;
            case 2:
                buffer = new StringBuffer("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
                break;
            case 3:
                buffer = new StringBuffer(
                        "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
                break;
            case 4:
                buffer = new StringBuffer(
                        "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-!,.?*&^$#");
                sb.append(buffer.charAt(r.nextInt(buffer.length() - 10)));
                passLength -= 1;
                break;

        }
        int range = buffer.length();
        for (int i = 0; i < passLength; ++i) {
            sb.append(buffer.charAt(r.nextInt(range)));
        }
        return sb.toString();
    }

}

enum UNIQUETYPE {
    NUMBER(0),
    LOWERALPHABET(1),
    BIGALPHABET(2),
    NUMBERANDALP(3),
    OTHER(4);
    private int value;

    UNIQUETYPE(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}

