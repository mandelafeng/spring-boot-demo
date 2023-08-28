package com.cfhui.encrypt.sm.sm4;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;


import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;
import java.security.Key;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Arrays;

/**
 * @description:
 * @author: Diuut
 * @date: 2022/5/31 10:56
 */
public class SM4Utils {
    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    private static final String ENCODING = BaseSmEnum.ENCODING.getMsg();

    public static final String ALGORITHM_NAME = BaseSmEnum.ALGORITHM_NAME.getMsg();
    // 加密算法/分组加密模式/分组填充方式
    // PKCS5Padding-以8个字节为一组进行分组加密
    // 定义分组加密模式使用：PKCS5Padding

    public static final String ALGORITHM_NAME_CBC_PADDING = BaseSmEnum.ALGORITHM_NAME_CBC_PADDING.getMsg();
    public static final String ALGORITHM_NAME_ECB_PADDING = BaseSmEnum.ALGORITHM_NAME_ECB_PADDING.getMsg();
    // 128-32位16进制；256-64位16进制
    public static final int DEFAULT_KEY_SIZE = 128;

    /**
     * 自动生成密钥
     *
     * @return
     * @explain
     */
    public static byte[] generateKey() throws Exception {
        return generateKey(DEFAULT_KEY_SIZE);
    }


    public static String generateKeyString() throws Exception {
        return ByteUtils.toHexString(generateKey());
    }

    /**
     * @param keySize
     * @return
     * @throws Exception
     * @explain
     */
    public static byte[] generateKey(int keySize) throws Exception {
        KeyGenerator kg = KeyGenerator.getInstance(ALGORITHM_NAME, BouncyCastleProvider.PROVIDER_NAME);
        kg.init(keySize, new SecureRandom());
        return kg.generateKey().getEncoded();
    }

    /**
     * sm4加密_CBC
     *
     * @param hexKey   16进制密钥（忽略大小写）
     * @param paramStr 待加密字符串
     * @return 返回16进制的加密字符串
     * @throws Exception
     * @explain 加密模式：CBC
     */
    public static String protectMsg_CBC(String hexKey, String paramStr) throws Exception {
        String result = "";
        // 16进制字符串-->byte[]
        byte[] keyData = ByteUtils.fromHexString(hexKey);
        // String-->byte[]
        byte[] srcData = paramStr.getBytes(ENCODING);
        // 加密后的数组
        byte[] cipherArray = encrypt_Cbc_Padding(keyData, srcData);

        // byte[]-->hexString
        result = ByteUtils.toHexString(cipherArray);
        return result;
    }

    /**
     * sm4加密_ECB
     *
     * @param hexKey   16进制密钥（忽略大小写）
     * @param paramStr 待加密字符串
     * @return 返回16进制的加密字符串
     * @throws Exception
     * @explain 加密模式：CBC
     */
    public static String protectMsg_ECB(String hexKey, String paramStr) throws Exception {
        String result = "";
        // 16进制字符串-->byte[]
        byte[] keyData = ByteUtils.fromHexString(hexKey);
        // String-->byte[]
        byte[] srcData = paramStr.getBytes(ENCODING);
        // 加密后的数组
        byte[] cipherArray = encrypt_Ecb_Padding(keyData, srcData);

        // byte[]-->hexString
        result = ByteUtils.toHexString(cipherArray);
        return result;
    }

    /**
     * 加密模式之CBC
     *
     * @param key
     * @param data
     * @return
     * @throws Exception
     * @explain
     */
    public static byte[] encrypt_Cbc_Padding(byte[] key, byte[] data) throws Exception {
        Cipher cipher = generateCbcCipher(ALGORITHM_NAME_CBC_PADDING, Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(data);
    }

    private static Cipher generateCbcCipher(String algorithmName, int mode, byte[] key) throws Exception {
        Cipher cipher = Cipher.getInstance(algorithmName, BouncyCastleProvider.PROVIDER_NAME);
        Key sm4Key = new SecretKeySpec(key, ALGORITHM_NAME);
        cipher.init(mode, sm4Key, generateIV());
        return cipher;
    }

    /**
     * 加密模式之ECB
     *
     * @param key
     * @param data
     * @return
     * @throws Exception
     * @explain
     */
    public static byte[] encrypt_Ecb_Padding(byte[] key, byte[] data) throws Exception {
        Cipher cipher = generateEcbCipher(ALGORITHM_NAME_ECB_PADDING, Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(data);
    }

    private static Cipher generateEcbCipher(String algorithmName, int mode, byte[] key) throws Exception {
        Cipher cipher = Cipher.getInstance(algorithmName, BouncyCastleProvider.PROVIDER_NAME);
        Key sm4Key = new SecretKeySpec(key, ALGORITHM_NAME);
        cipher.init(mode, sm4Key);
        return cipher;
    }

    //生成iv
    public static AlgorithmParameters generateIV() throws Exception {
        //iv 为一个 16 字节的数组，这里采用和 iOS 端一样的构造方法，数据全为0
        byte[] iv = new byte[16];
        Arrays.fill(iv, (byte) 0x00);
        System.out.println("generateIV"+ByteUtils.toHexString(iv));
        AlgorithmParameters params = AlgorithmParameters.getInstance(ALGORITHM_NAME);
        params.init(new IvParameterSpec(iv));
        return params;
    }

    /**
     * sm4解密_CBC
     *
     * @param hexKey 16进制密钥
     * @param text   16进制的加密字符串（忽略大小写）
     * @return 解密后的字符串
     * @throws Exception
     * @explain 解密模式：采用CBC
     */
    public static String uncoverMsg_CBC(String hexKey, String text) throws Exception {
        // 用于接收解密后的字符串
        String result = "";
        // hexString-->byte[]
        byte[] keyData = ByteUtils.fromHexString(hexKey);
        // hexString-->byte[]
        byte[] resultData = ByteUtils.fromHexString(text);
        // 解密
        byte[] srcData = decrypt_Cbc_Padding(keyData, resultData);
        // byte[]-->String
        result = new String(srcData, ENCODING);
        return result;
    }

    /**
     * sm4解密_ECB
     *
     * @param hexKey 16进制密钥
     * @param text   16进制的加密字符串（忽略大小写）
     * @return 解密后的字符串
     * @throws Exception
     * @explain 解密模式：采用ECB
     */
    public static String uncoverMsg_ECB(String hexKey, String text) throws Exception {
        // 用于接收解密后的字符串
        String result = "";
        // hexString-->byte[]
        byte[] keyData = ByteUtils.fromHexString(hexKey);
        // hexString-->byte[]
        byte[] resultData = ByteUtils.fromHexString(text);
        // 解密
        byte[] srcData = decrypt_Ecb_Padding(keyData, resultData);
        // byte[]-->String
        result = new String(srcData, ENCODING);
        return result;
    }

    /**
     * 解密
     *
     * @param key
     * @param cipherText
     * @return
     * @throws Exception
     * @explain
     */
    public static byte[] decrypt_Cbc_Padding(byte[] key, byte[] cipherText) throws Exception {
        Cipher cipher = generateCbcCipher(ALGORITHM_NAME_CBC_PADDING, Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(cipherText);
    }

    /**
     * 解密
     *
     * @param key
     * @param cipherText
     * @return
     * @throws Exception
     * @explain
     */
    public static byte[] decrypt_Ecb_Padding(byte[] key, byte[] cipherText) throws Exception {
        Cipher cipher = generateEcbCipher(ALGORITHM_NAME_ECB_PADDING, Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(cipherText);
    }



    public static void main(String[] args) {
        try {
            String json = "openid=ooj0E5I8XR4AC5RnafNaZTwRhQn6";
            System.out.println("加密前源数据————" + json);
            // 生成32位16进制密钥
            String key="0qig5udx3ntl8wci";
//             key = SM4Utils.generateKeyString();
            key=StringToHex.stringToHexString(key);
            //key=StringToHex.stringToHexString(key);
//            System.out.println(key + "-----生成key");
            String cipher = SM4Utils.protectMsg_ECB(key,json);
            System.out.println("加密串---" + cipher);
            json = SM4Utils.uncoverMsg_ECB(key, cipher);
            System.out.println("解密后数据---" + json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
