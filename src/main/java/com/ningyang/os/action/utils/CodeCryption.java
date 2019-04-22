package com.ningyang.os.action.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


/**
 * 加解密工具
 *
 * @author dennies yang
 */
public class CodeCryption {
    private static final Logger log = LoggerFactory.getLogger(CodeCryption.class);
    private static final char[] HEX_DIGITS = {'0',
            '1',
            '2',
            '3',
            '4',
            '5',
            '6',
            '7',
            '8',
            '9',
            'a',
            'b',
            'c',
            'd',
            'e',
            'f'};

    public String encryptedData(String data) {

        try {
            String encodedStr = DesUtils.encrypt(CommucationConstants.COMM_SIGNED, data);
//            String urlEncodeData = java.net.URLEncoder.encode(encodedStr);
            return encodedStr;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Encryp Data Error. Detail:" + e.getMessage());
        }
        return null;
    }

    /**
     * 生成MD5数据签名方法
     *
     * @param action 请求的action接口
     * @param data   已使用DES加密的JSON数据字符串
     * @param dt     时间，格式SimpleDateFormat("yyyyMMddHHmmss");
     * @return
     */
    public String sign(String action, String data, String dt) {
        String key = CommucationConstants.COMM_SIGNED; // 通信密串，双方协定

        String signed = new String();

        // 将action的值转换为小写
        action = action.toLowerCase();
        // 构造请求字符串
        String str = "&action="
                + action
                + "&data="
                + data
                + "&dt="
                + dt
                + "&key="
                + key;

        System.out.println("str=" + str);

        signed = getMD5Str(str);

        System.out.println("signed=" + signed);

        return signed.toUpperCase();// 返回32位大写的MD5数字签名串
    }

    /**
     * MD5加密
     *
     * @param str
     * @return
     */
    private String getMD5Str(String str) {
        MessageDigest messageDigest = null;

        try {
            messageDigest = MessageDigest.getInstance("MD5");

            messageDigest.reset();

            messageDigest.update(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            System.out.println("NoSuchAlgorithmException caught!");
            System.exit(-1);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        byte[] byteArray = messageDigest.digest();

        StringBuffer md5StrBuff = new StringBuffer();

        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
                md5StrBuff.append("0")
                        .append(Integer.toHexString(0xFF & byteArray[i]));
            else
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
        }

        return md5StrBuff.toString();
    }

    /**
     * 加密字符串
     *
     * @param algorithm BASE64 MD5 SHA1
     * @param str
     */
    public static String encode(String algorithm, String str) {
        if (str == null)
            return null;
        try {
            if (algorithm.toUpperCase().equals("BASE64"))
                return encodeBase64(str);
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.update(str.getBytes());
            return getFormattedText(messageDigest.digest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Takes the raw bytes from the digest and formats them correct.
     *
     * @param bytes the raw bytes from the digest.
     * @return the formatted bytes.
     */
    private static String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);

        // 把密文转换成十六进制的字符串形式
        for (int j = 0; j < len; j++) {
            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }

        return buf.toString();
    }

    /**
     * BASE64编码
     *
     * @param str
     * @return
     */
    private static String encodeBase64(String str) {
        if (str == null)
            return null;
        try {
            return new String(org.apache.commons.codec.binary.Base64.encodeBase64(str.getBytes("UTF-8"))).replaceAll("\r",
                    "")
                    .replaceAll("\n",
                            "");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * BASE64解码
     *
     * @param str
     * @return
     */
    public static String decodeBase64(String str) {
        if (str == null)
            return null;
        try {
            return new String(org.apache.commons.codec.binary.Base64.decodeBase64(str.getBytes("UTF-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解密字符串
     *
     * @param algorithm BASE64
     * @param str
     * @return
     */
    private static String decode(String algorithm, String str) {

        if (str == null)
            return null;
        if (algorithm.toUpperCase().equals("BASE64"))
            return decodeBase64(str);
        else
            return null;
    }

    // DES加密
    public static byte[] desEncrypt(byte[] datasource, String password) {
        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(password.getBytes());
            // 创建一个密匙工厂，然后用它把DESKeySpec转换成
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKey);
            // Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance("DES");
            // 用密匙初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
            // 现在，获取数据并加密
            // 正式执行加密操作
            return cipher.doFinal(datasource);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    // DES解密
    public static byte[] desDecrypt(byte[] src, String password)
            throws Exception {
        // DES算法要求有一个可信任的随机数源
        SecureRandom random = new SecureRandom();
        // 创建一个DESKeySpec对象
        DESKeySpec desKey = new DESKeySpec(password.getBytes());
        // 创建一个密匙工厂
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        // 将DESKeySpec对象转换成SecretKey对象
        SecretKey securekey = keyFactory.generateSecret(desKey);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance("DES");
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, random);
        // 真正开始解密操作
        return cipher.doFinal(src);
    }

    public static void main(String[] args) {

        System.out.println("MD5  :" + CodeCryption.encode("MD5", "123456"));
        System.out.println("SHA1 :" + CodeCryption.encode("SHA1", "123456"));
        System.out.println("BASE64 :" + CodeCryption.encode("BASE64", "123456"));
        System.out.println("BASE64 :" + decode("BASE64", "MTIzNDU2"));

        // 待加密内容
        String str = "测试DES内容";
        // 密码，长度要是8的倍数
        String password = CommucationConstants.COMM_SIGNED;
        byte[] result = desEncrypt(str.getBytes(), password);
        System.out.println("加密后内容为：" + new String(result));

        // 直接将如上内容解密
        try {
            byte[] decryResult = desDecrypt(result, password);
            System.out.println("加密后内容为：" + new String(decryResult));
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

}
