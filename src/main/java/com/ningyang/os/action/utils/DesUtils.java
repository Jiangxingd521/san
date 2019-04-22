package com.ningyang.os.action.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.util.Random;

/**
 * @author dennies yang
 * DES  加解密工具
 */
public class DesUtils {
    private static String DES_FORMAT = "DES/ECB/NoPadding";

    private static String DES_ENCODING = "UTF-8";

    public static String encrypt(String key, String data)
            throws Exception {
        if ((key == null) || (key.length() == 0)) {
            System.out.println("key is null.");
            return null;
        }

        if ((data == null) || (data.length() == 0)) {
            System.out.println("data is null.");
            return null;
        }

        if ((key.length() % 8 != 0) || (key.length() > 64)) {
            System.out.println("invalid key.");
            throw null;
        }

        byte[] bytes = handleDesData(data.getBytes(DES_ENCODING));

        DESKeySpec dks = new DESKeySpec(key.getBytes("ASCII"));

        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey securekey = keyFactory.generateSecret(dks);

        Cipher cipher = Cipher.getInstance(DES_FORMAT);

        cipher.init(1, securekey);

        byte[] result = cipher.doFinal(bytes);

        Base64 base64 = new Base64();
        byte[] encodedBytes = base64.encode(result);

        return new String(encodedBytes);
    }

    public static String decode(String key, String encryptedStr)
            throws Exception {
        if ((key == null) || (key.length() == 0)) {
            System.out.println("key is null.");
            return null;
        }

        if ((encryptedStr == null) || (encryptedStr.length() == 0)) {
            return null;
        }

        if ((key.length() % 8 != 0) || (key.length() > 64)) {
            System.out.println("invalid key.");
            throw null;
        }

        Base64 base64 = new Base64();
        byte[] decodedBytes = base64.decode(encryptedStr.getBytes());

        DESKeySpec objDesKeySpec = new DESKeySpec(key.getBytes("ASCII"));
        SecretKeyFactory objKeyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey objSecretKey = objKeyFactory.generateSecret(objDesKeySpec);

        Cipher objCipher = Cipher.getInstance(DES_FORMAT);

        objCipher.init(2, objSecretKey);

        String decryptedStr = new String(objCipher.doFinal(decodedBytes), DES_ENCODING).trim();

        return decryptedStr;
    }

    private static byte[] handleDesData(byte[] data) {
        int length = data.length;

        int mod = length % 8;
        byte[] nData;
        if (mod != 0) {
            nData = new byte[length + 8 - mod];
        } else {
            nData = new byte[length];
        }

        for (int i = 0; i < nData.length; i++) {
            if (i >= length)
                continue;
            nData[i] = data[i];
        }

        return nData;
    }

    public static String generateKey(int length) {
        if ((length <= 0) || (length % 8 != 0) || (length > 64)) {
            return null;
        }

        String val = "";

        Random random = new Random();
        for (int i = 0; i < length; i++) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";

            if ("char".equalsIgnoreCase(charOrNum)) {
                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val = val + (char) (choice + random.nextInt(26));
            } else {
                if (!"num".equalsIgnoreCase(charOrNum))
                    continue;
                val = val + String.valueOf(random.nextInt(10));
            }

        }

        return val.toLowerCase();
    }

    public static void main(String[] args)
            throws Exception {
        String key = generateKey(8);

        String encodedStr = encrypt(key, "加密测试数据test");
        System.out.println("encodedStr: " + encodedStr);

        String decodedStr = decode(key, encodedStr);
        System.out.println("decodedStr: " + decodedStr);
    }
}
