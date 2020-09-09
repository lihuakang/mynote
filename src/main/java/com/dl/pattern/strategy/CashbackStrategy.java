package com.dl.pattern.strategy;

public class CashbackStrategy implements  PromotionStrategy {
    @Override
    public void doPromotion() {
        System.out.println("返现促销");
    }
}
