package com.cfhui.observer.demo6;

/**
 * []
 *
 * @author cfhui
 * @version V1
 * @date 2023/10/19 下午 3:23
 */
public class Demo {
    public static void main(String[] args) {
        ReaderFactory factory1 = new JpgReaderFactory();
        Reader reader1 = factory1.getReader();
        reader1.read();

        ReaderFactory factory2 = new PngReaderFactory();
        Reader reader2 = factory2.getReader();
        reader2.read();

        ReaderFactory factory3 = new GifReaderFactory();
        Reader reader3 = factory3.getReader();
        reader3.read();

    }
}
