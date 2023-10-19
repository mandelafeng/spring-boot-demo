package com.cfhui.observer.demo6;

public class JpgReaderFactory implements ReaderFactory {
    @Override
    public Reader getReader() {
        return new JpgReader();
    }
}
