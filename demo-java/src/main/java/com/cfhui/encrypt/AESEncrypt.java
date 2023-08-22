package com.cfhui.encrypt;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.nio.file.Files;
/**
 * [ 对称加密工具类 ]
 * @author cfhui
 * @since V1
 * @date 2023/8/22 下午 3:25
 */
public class AESEncrypt {
    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    /**
     * 生成 SecretKey
     *
     * @param secret
     * @param salt
     * @return
     */
    public static SecretKey generateSecretKey(String secret, String salt) {
        SecretKey secretKey = null;
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            PBEKeySpec keySpec = new PBEKeySpec(secret.toCharArray(), salt.getBytes(), 65536, 256);
            secretKey = new SecretKeySpec(factory.generateSecret(keySpec).getEncoded(), "AES");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return secretKey;
    }

    /**
     * 加密
     *
     * @param content
     * @param secretKey
     * @return
     */
    private static byte[] encrypt(byte[] content, SecretKey secretKey) {
        byte[] str = null;
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(iv));
            str = cipher.doFinal(content);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return str;
    }

    /**
     * 解密
     *
     * @param bytes
     * @param secretKey
     * @return
     */
    private static byte[] decrypt(byte[] bytes, SecretKey secretKey) {
        byte[] decryptStr = null;
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(iv));
            decryptStr = cipher.doFinal(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return decryptStr;
    }

    /**
     * 文件加密
     *
     * @param file
     * @param secretKey
     */
    public static int encryptFile(File file, SecretKey secretKey, int encryptLength) {
        try {
            // 以 byte 的形式读取，不改变文件数据的编码格式
            byte[] bytes = Files.readAllBytes(file.toPath());

            // 仅加密 encryptLength 长度的数据
            byte[] substring = new byte[encryptLength];
            System.arraycopy(bytes, 0, substring, 0, encryptLength);

            // 加密
            byte[] encrypt = encrypt(substring, secretKey);

            // 使用密文替换老数据
            byte[] newContent = new byte[encrypt.length + bytes.length - encryptLength];
            System.arraycopy(encrypt, 0, newContent, 0, encrypt.length);
            System.arraycopy(bytes, encryptLength, newContent, encrypt.length, bytes.length - encryptLength);

            // 覆盖写入文件
            Files.write(file.toPath(), newContent);

            return encrypt.length;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * 文件解密
     *
     * @param file
     * @param secretKey
     * @param decryptLength
     */
    public static void decryptFile(File file, SecretKey secretKey, int decryptLength) {
        try {
            // 以 byte 的形式读取，不改变文件数据的编码格式
            byte[] bytes = Files.readAllBytes(file.toPath());

            // 截取密文数据
            byte[] substring = new byte[decryptLength];
            System.arraycopy(bytes, 0, substring, 0, decryptLength);

            // 解密
            byte[] decrypt = decrypt(substring, secretKey);

            // 使用明文替换加密数据
            byte[] newContent = new byte[decrypt.length + bytes.length - decryptLength];
            System.arraycopy(decrypt, 0, newContent, 0, decrypt.length);
            System.arraycopy(bytes, decryptLength, newContent, decrypt.length, bytes.length - decryptLength);

            // 覆盖写入文件
            Files.write(file.toPath(), newContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            // generate secret key
            SecretKey secretKey = generateSecretKey("password", "salt");

            File file = new File("E:\\MyWorkspace\\Test\\test.xosc");
            System.out.println("开始加密");
            int encryptLength = encryptFile(file, secretKey, Files.readAllBytes(file.toPath()).length);
            System.out.println("加密完成");
            System.out.println("开始解密");
            decryptFile(file, secretKey, encryptLength);
            System.out.println("解密完成");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
