package com.cfhui.observer.demo6.factory;

import com.cfhui.observer.demo6.base.PngReader;
import com.cfhui.observer.demo6.base.Reader;

public class PngReaderFactory implements ReaderFactory {
    @Override
    public Reader getReader() {
        return new PngReader();
    }
}
