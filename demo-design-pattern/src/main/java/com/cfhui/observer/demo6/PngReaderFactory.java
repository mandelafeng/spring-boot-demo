package com.cfhui.observer.demo6;

public class PngReaderFactory implements ReaderFactory {
    @Override
    public Reader getReader() {
        return new PngReader();
    }
}
