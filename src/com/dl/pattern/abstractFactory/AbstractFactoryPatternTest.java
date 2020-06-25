package com.dl.pattern.abstractFactory;

public class AbstractFactoryPatternTest {
    public static void main(String[] args) {
        //获取形状工厂
        AbstractFactory shareFactory = FactoryProduct.getFactory("shape");
        //获取形状为Circle的对象
        Shape circle = shareFactory.getShape("circle");
        circle.drow();
        Shape square = shareFactory.getShape("square");
        square.drow();
        Shape rectangle = shareFactory.getShape("rectangle");
        rectangle.drow();


        AbstractFactory colorFactory = FactoryProduct.getFactory("color");
        Color red = colorFactory.getColor("red");
        red.fill();
        Color blue = colorFactory.getColor("blue");
        blue.fill();
        Color green = colorFactory.getColor("green");
        green.fill();

    }
}
