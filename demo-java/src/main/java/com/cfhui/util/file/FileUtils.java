package com.cfhui.util.file;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

/**
 * @className: FileUtils
 * @description: TODO 类描述
 * @author: chunfenghui
 * @date: 2021/6/13 下午 1:13
 **/
public class FileUtils {
    /**
     * 关闭文件流
     *
     * @author Lius
     * @date 2018/10/26 16:32
     */
    public static void closeBufferedReader(BufferedReader bufferedReader) {
        if (bufferedReader != null) {
            try {
                bufferedReader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 判断文件是否存在
     *
     * @author Lius
     * @date 2018/10/27 10:38
     */
    public static boolean isExistFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            return false;
        }
        return true;
    }

    /**
     * 读文件
     *
     * @param filePath 读取文件路径
     * @return 返回字符串
     * @author Lius
     * @date 2018/10/26 15:59
     */
    public static String readFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            return null;
        }
        StringBuffer stringBuffer = null;
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
            stringBuffer = new StringBuffer();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                if ("\r".equals(line)) {
                    continue;
                }
                stringBuffer.append(line).append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            closeBufferedReader(bufferedReader);
        }
        return stringBuffer.toString();
    }

    /**
     * 文件读取
     *
     * @param filePath 问价路径
     * @return 返回二进制
     * @author Lius
     * @date 2018/10/26 16:45
     */
    public static byte[] readFileByByte(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            return null;
        }
        byte[] bytes = null;
        try {
            bytes = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return bytes;
    }

    /**
     * 根据文件路径获得文件名
     *
     * @author Lius
     * @date 2018/10/27 10:19
     */
    public static String getFileName(String filePath) {
        String[] splits = filePath.split("\\\\");
        return splits[splits.length - 1];
    }

    /**
     * 通过文件名获得文件类型
     *
     * @param fileName 文件名
     * @author Lius
     * @date 2018/10/26 17:44
     */
    public static String getFileTypeByName(String fileName) {
        String[] splits = fileName.split("\\.");
        return splits[splits.length - 1];
    }

    /**
     * 通过文件路径获得文件类型
     *
     * @param filePath 文件路径
     * @author Lius
     * @date 2018/10/27 10:27
     */
    public static String getFileTypeByPath(String filePath) {
        return getFileTypeByName(getFileName(filePath));
    }


    /**
     * 文件压缩
     * @author Lius
     * @date 2018/10/29 13:55
     */
    public static byte[] compress(byte[] input) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Deflater compressor = new Deflater(1);
        try {
            compressor.setInput(input);
            compressor.finish();
            final byte[] buf = new byte[2048];
            while (!compressor.finished()) {
                int count = compressor.deflate(buf);
                bos.write(buf, 0, count);
            }
        } finally {
            compressor.end();
        }
        return bos.toByteArray();
    }

    /**
     * 文件解压缩
     * @author Lius
     * @date 2018/10/29 13:56
     */
    public static byte[] uncompress(byte[] input) throws DataFormatException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Inflater decompressor = new Inflater();
        try {
            decompressor.setInput(input);
            final byte[] buf = new byte[2048];
            while (!decompressor.finished()) {
                int count = decompressor.inflate(buf);
                bos.write(buf, 0, count);
            }
        } finally {
            decompressor.end();
        }
        return bos.toByteArray();
    }
}
