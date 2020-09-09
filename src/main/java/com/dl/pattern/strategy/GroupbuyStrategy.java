package com.dl.pattern.strategy;

public class GroupbuyStrategy implements PromotionStrategy {
    @Override
    public void doPromotion() {
        System.out.println("拼团，满20人，全员团价");
    }
}
