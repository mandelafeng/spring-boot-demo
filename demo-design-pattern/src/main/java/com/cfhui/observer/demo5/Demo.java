package com.cfhui.observer.demo5;

/**
 * []
 *
 * @author cfhui
 * @version V1
 * @date 2023/10/19 下午 2:12
 */
public class Demo {
    public static void main(String[] args) {
        Shape shape1= ShapeFactory.getShape("circle");
        shape1.draw();

        Shape shape2= ShapeFactory.getShape("rect");
        shape2.draw();

        Shape shape3= ShapeFactory.getShape("triangle");
        shape3.draw();
    }
}
