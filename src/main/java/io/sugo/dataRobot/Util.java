package io.sugo.dataRobot;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.Random;

/**
 * Created by qwe on 17-7-13.
 */
public class Util {
    private static final String numBase = "0123456789";
    private static final String strBase = "abcdefghijklmnopqrstuvwxyz0123456789";
    private static Random random = new Random();
    public static MessageDigest md;





    public static String getMd5Sum(String pos) {
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        StringBuffer sb = new StringBuffer();
        md.reset();
        md.update(pos.getBytes());
        byte[] digest = md.digest();
        for (byte b : digest) {
            sb.append(String.format("%02x", b & 0xff));
        }
        return sb.toString();
    }




}
