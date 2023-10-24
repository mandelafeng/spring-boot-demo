package com.cfhui.observer.demo6.factory;

import com.cfhui.observer.demo6.base.GifReader;
import com.cfhui.observer.demo6.base.Reader;

public class GifReaderFactory implements ReaderFactory {
    @Override
    public Reader getReader() {
        return new GifReader();
    }
}
