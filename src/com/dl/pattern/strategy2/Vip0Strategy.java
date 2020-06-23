package com.dl.pattern.strategy2;

/**
 * 普通会员策略
 */
public class Vip0Strategy implements Strategy {

    @Override
    public double getPrice(double standaradPrice) {
        System.out.println("普通会员，原价"+standaradPrice);
        return standaradPrice;
    }
}
