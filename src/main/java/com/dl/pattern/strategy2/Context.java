package com.dl.pattern.strategy2;

/**
 * 策略接收器，专门接收策略实现的算法
 * 负责和具体的策略类交互
 */
public class Context {
    private Strategy strategy;
    public Context(Strategy strategy){
        this.strategy=strategy;
    }
    public double getReultPrice(double price){
        return this.strategy.getPrice(price);
    }
}
