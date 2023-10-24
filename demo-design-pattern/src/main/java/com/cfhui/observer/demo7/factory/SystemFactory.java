package com.cfhui.observer.demo7.factory;

import com.cfhui.observer.demo7.OperationController;
import com.cfhui.observer.demo7.UIController;

public interface SystemFactory {
    public OperationController createOperationController();
    public UIController createInterfaceController();
}
