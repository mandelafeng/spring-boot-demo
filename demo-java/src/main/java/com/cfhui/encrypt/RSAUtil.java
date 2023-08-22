package com.cfhui.encrypt;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import java.io.*;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * [ 非对称加密工具类 ]
 * @author cfhui
 * @since V1
 * @date 2023/8/22 下午 3:25
 */
public class RSAUtil {

    /**
     * 密钥长度(bit)
     */
    public static final int KEY_LENGTH = 1024;
    /**
     * 1024/8 -11
     */
    public static final int MAX_ENCRYPT_BLOCK = 117;
    /**
     * <p>
     * 单次解密最大密文长度，这里仅仅指1024bit 长度密钥 1024/8
     * </p>
     *
     * @see #MAX_ENCRYPT_BLOCK
     */
    public static final int MAX_DECRYPT_BLOCK = 128;

    /**
     * 加密算法
     */
    public static final String ALGORITHM_RSA = "RSA";

    /**
     * 算法/模式/填充
     */
    public static final String CIPHER_TRANSFORMATION_RSA = "RSA/ECB/PKCS1Padding";

    /**
     * 签名算法
     */
    public static final String SIGN_ALGORITHMS = "SHA1WithRSA";

    /**
     * UTF-8字符集
     **/
    public static final String CHARSET_UTF8 = "UTF-8";

    /**
     * GBK字符集
     **/
    public static final String CHARSET_GBK = "GBK";

    public static final String CHARSET = CHARSET_UTF8;

    /**
     * 得到公钥
     *
     * @param key     密钥字符串（经过base64编码）
     * @param charset 字符编码
     * @throws Exception 异常
     */
    public static PublicKey getPublicKey(String key, String charset) throws Exception {
        byte[] keyBytes = Base64.decodeBase64(key.getBytes(charset));
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
        return keyFactory.generatePublic(keySpec);
    }

    /**
     * 得到私钥
     *
     * @param key     密钥字符串（经过base64编码）
     * @param charset 字符编码
     * @throws Exception 异常
     */
    public static PrivateKey getPrivateKey(String key, String charset) throws Exception {
        byte[] keyBytes;
        keyBytes = Base64.decodeBase64(key.getBytes(charset));
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
        return keyFactory.generatePrivate(keySpec);
    }

    /**
     * 得到密钥字符串（经过base64编码）
     *
     * @return 密钥字符串
     */
    public static String getKeyString(Key key) throws Exception {
        byte[] keyBytes = key.getEncoded();
        return new String(Base64.encodeBase64(keyBytes), CHARSET);
    }

    /**
     * 公钥加密
     *
     * @param content   待加密内容
     * @param publicKey 公钥
     * @param charset   字符集，如UTF-8, GBK, GB2312
     * @return 密文内容
     * @throws Exception 异常
     */
    public static String rsaEncrypt(String content, String publicKey,
                                    String charset) throws Exception {
        try {
            PublicKey pubKey = getPublicKey(publicKey, charset);
            Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORMATION_RSA);
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
            byte[] data = StringUtils.isEmpty(charset) ? content.getBytes() : content.getBytes(charset);
            int inputLen = data.length;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int offSet = 0;
            byte[] cache;
            int i = 0;
            // 对数据分段加密
            while (inputLen - offSet > 0) {
                if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                    cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
                } else {
                    cache = cipher.doFinal(data, offSet, inputLen - offSet);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * MAX_ENCRYPT_BLOCK;
            }
            byte[] encryptedData = Base64.encodeBase64(out.toByteArray());
            out.close();
            return StringUtils.isEmpty(charset) ? new String(encryptedData) : new String(encryptedData, charset);
        } catch (Exception e) {
            throw new Exception(
                "error occured in rsaEncrypt: EncryptContent = " + content
                    + ",charset = " + charset, e);
        }
    }

    /**
     * 公钥加密
     *
     * @param content   待加密内容
     * @param publicKey 公钥
     * @return 密文内容
     * @throws Exception 异常
     */
    public static String rsaEncrypt(String content, String publicKey)
        throws Exception {
        return rsaEncrypt(content, publicKey, RSAUtil.CHARSET);
    }

    /**
     * 私钥解密
     *
     * @param content    待解密内容
     * @param privateKey 私钥
     * @param charset    字符集，如UTF-8, GBK, GB2312
     * @return 明文内容
     * @throws Exception 异常
     */
    public static String rsaDecrypt(String content,
                                    String privateKey,
                                    String charset) throws Exception {
        try {
            PrivateKey priKey = getPrivateKey(privateKey, charset);
            Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORMATION_RSA);
            cipher.init(Cipher.DECRYPT_MODE, priKey);
            byte[] encryptedData = StringUtils.isEmpty(charset) ? Base64
                .decodeBase64(content.getBytes()) : Base64
                .decodeBase64(content.getBytes(charset));
            int inputLen = encryptedData.length;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int offSet = 0;
            byte[] cache;
            int i = 0;
            // 对数据分段解密
            while (inputLen - offSet > 0) {
                if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                    cache = cipher.doFinal(encryptedData, offSet,
                        MAX_DECRYPT_BLOCK);
                } else {
                    cache = cipher.doFinal(encryptedData, offSet, inputLen
                        - offSet);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * MAX_DECRYPT_BLOCK;
            }
            byte[] decryptedData = out.toByteArray();
            out.close();

            return StringUtils.isEmpty(charset) ? new String(decryptedData) : new String(decryptedData, charset);
        } catch (Exception e) {
            throw new Exception("error occured in rsaDecrypt: EncodeContent = "
                + content + ",charset = " + charset, e);
        }
    }

    /**
     * 私钥解密
     *
     * @param content    待解密内容
     * @param privateKey 私钥
     * @return 明文内容
     * @throws Exception 异常
     */
    public static String rsaDecrypt(String content, String privateKey)
        throws Exception {
        return rsaDecrypt(content, privateKey, RSAUtil.CHARSET);
    }

    /**
     * 获得密钥对
     *
     * @return 密钥对
     */
    public static KeyPair createKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(RSAUtil.ALGORITHM_RSA);
        // 密钥位数
        keyPairGen.initialize(KEY_LENGTH);
        // 密钥对
        return keyPairGen.generateKeyPair();
    }

    /**
     * 从本地文件读取key
     *
     * @param filePath 文件路径
     * @return 文本key字符串 rsa_private_key.pem rsa_public_key.pem
     */
    public static String getKey(String filePath) throws Exception {
        StringBuilder sb = new StringBuilder();
        File file = new File(filePath);
        if (!file.exists()) {
            throw new Exception("密钥文件地址错误！");
        }
        try (FileInputStream fileInputStream = new FileInputStream(file);
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream))) {
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("密钥文件地址错误！");
        } catch (IOException e) {
            throw new IOException("密钥文件读取错误！");
        }
        return sb.toString().trim();
    }

    public static void main(String[] args) throws Exception {
//        KeyPair keyPair = createKeyPair();
//        System.out.println("生成公钥");
//        PublicKey publicKey = getPublicKey(new BASE64Encoder().encode(keyPair.getPublic().getEncoded()), "utf-8");
//        String publicKeyStr = new BASE64Encoder().encode(publicKey.getEncoded());
//        System.out.println(publicKeyStr);
//        System.out.println("=============");
//        System.out.println("生成私钥");
//        PrivateKey privateKey = getPrivateKey(new BASE64Encoder().encode(keyPair.getPrivate().getEncoded()), "utf-8");
//        String privateKeyStr = new BASE64Encoder().encode(privateKey.getEncoded());
//
//        System.out.println(privateKeyStr);
//        String strEncrpt = rsaEncrypt("abcd", publicKeyStr, "utf-8");
//        System.out.println("密文===" + strEncrpt);
//        String content = rsaDecrypt(strEncrpt, privateKeyStr, "utf-8");
//        System.out.println("解密===" + content);
        String filePath1 = "E:\\MyWorkspace\\Test\\rsa_public_key.pem";
        String filePath2 = "E:\\MyWorkspace\\Test\\rsa_private_key.pem";
        System.out.println(getKey(filePath1));
        System.out.println(getKey(filePath2));

    }
}
