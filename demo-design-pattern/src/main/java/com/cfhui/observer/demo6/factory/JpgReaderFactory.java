package com.cfhui.observer.demo6.factory;

import com.cfhui.observer.demo6.base.JpgReader;
import com.cfhui.observer.demo6.base.Reader;

public class JpgReaderFactory implements ReaderFactory {
    @Override
    public Reader getReader() {
        return new JpgReader();
    }
}
