package com.dl.pattern.strategy;

public class CouponStrategy implements PromotionStrategy {
    @Override
    public void doPromotion() {
        System.out.println("领取优惠券，直接抵扣");
    }
}
