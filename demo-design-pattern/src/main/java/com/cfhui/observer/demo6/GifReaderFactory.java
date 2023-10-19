package com.cfhui.observer.demo6;

public class GifReaderFactory implements ReaderFactory {
    @Override
    public Reader getReader() {
        return new GifReader();
    }
}
