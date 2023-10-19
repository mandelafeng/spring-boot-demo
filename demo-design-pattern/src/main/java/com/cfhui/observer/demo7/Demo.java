package com.cfhui.observer.demo7;

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
        mFactory=new AndroidFactory();
        //Ios
        mFactory=new IosFactory();
        //Wp
        mFactory=new WpFactory();

        interfaceController=mFactory.createInterfaceController();
        operationController=mFactory.createOperationController();
        interfaceController.display();
        operationController.control();

    }
}
