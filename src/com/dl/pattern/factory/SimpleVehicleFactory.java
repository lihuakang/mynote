package com.dl.pattern.factory;

/**
 * 简单工厂，可扩展性不好
 */
public class SimpleVehicleFactory {
    public Car createCar(){
        //前置操作
        System.out.println("工厂生产了一个车");
        return new Car();
    }

    public Airplain createAirplain(){
        //前置操作
        System.out.println("工厂生产了一个飞机");
        return new Airplain();
    }
}
