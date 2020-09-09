package com.dl.pattern.strategy2;

public class Vip2Strategy implements Strategy {
    @Override
    public double getPrice(double standaradPrice) {
        System.out.println("二级会员，打八折"+standaradPrice*0.8);
        return standaradPrice*0.8;
    }
}
