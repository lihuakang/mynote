package com.dl.pattern.strategy;

public class Test {
    public static void main(String[] args) {
        PromotionActivity activity618=new PromotionActivity(new CouponStrategy());
        PromotionActivity activity1111=new PromotionActivity(new CashbackStrategy());
        activity618.execute();
        activity1111.execute();
    }

}
