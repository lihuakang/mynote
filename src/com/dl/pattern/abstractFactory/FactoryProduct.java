package com.dl.pattern.abstractFactory;

/**
 * 创建一个工厂创造器，通过传递形状和颜色来获取工厂
 */
public class FactoryProduct {
    public static AbstractFactory getFactory(String choice){
        if (choice.equalsIgnoreCase("shape")){
            return new ShapeFactory();
        }else if (choice.equalsIgnoreCase("color")){
            return new ColorFactory();
        }
        return null;
    }
}
