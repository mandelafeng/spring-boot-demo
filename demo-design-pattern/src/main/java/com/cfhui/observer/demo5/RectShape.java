package com.cfhui.observer.demo5;

public class RectShape implements Shape {
    public RectShape() {
       System.out.println(  "RectShape: created");
    }

    @Override
    public void draw() {
       System.out.println(  "draw: RectShape");
    }

}
