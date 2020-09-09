package com.dl.pattern.strategy2;

/**
 * 先定义策略，面向接口，开闭原则
 */
public interface Strategy {


    double getPrice(double standaradPrice);
}
