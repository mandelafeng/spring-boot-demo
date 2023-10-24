package com.cfhui.observer.demo7;

import com.cfhui.observer.demo7.factory.AndroidFactory;
import com.cfhui.observer.demo7.factory.IosFactory;
import com.cfhui.observer.demo7.factory.SystemFactory;
import com.cfhui.observer.demo7.factory.WpFactory;

/**
 * []
 *
 * @author cfhui
 * @version V1
 * @date 2023/10/19 下午 3:49
 */
public class Demo {
    public static void main(String[] args) {
        SystemFactory mFactory;
        UIController interfaceController;
        OperationController operationController;

        //Android
        mFactory = new AndroidFactory();
        interfaceController = mFactory.createInterfaceController();
        operationController = mFactory.createOperationController();
        interfaceController.display();
        operationController.control();
        //Ios
        mFactory = new IosFactory();
        interfaceController = mFactory.createInterfaceController();
        operationController = mFactory.createOperationController();
        interfaceController.display();
        operationController.control();
        //Wp
        mFactory = new WpFactory();
        interfaceController = mFactory.createInterfaceController();
        operationController = mFactory.createOperationController();
        interfaceController.display();
        operationController.control();

    }
}
