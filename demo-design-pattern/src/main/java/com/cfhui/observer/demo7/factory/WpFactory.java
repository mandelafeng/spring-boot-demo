package com.cfhui.observer.demo7.factory;

import com.cfhui.observer.demo7.OperationController;
import com.cfhui.observer.demo7.UIController;
import com.cfhui.observer.demo7.WpOperationController;
import com.cfhui.observer.demo7.WpUIController;

public class WpFactory implements SystemFactory {
    @Override
    public OperationController createOperationController() {
        return new WpOperationController();
    }

    @Override
    public UIController createInterfaceController() {
        return new WpUIController();
    }
}
