package com.dl.pattern.strategy2;

public class Vip1Strategy implements Strategy {
    @Override
    public double getPrice(double standaradPrice) {
        System.out.println("一级会员，打九折"+standaradPrice*0.9);
        return standaradPrice*0.9;
    }
}
