package com.dl.pattern.strategy2;

/**
 * 不使用策略
 */
public class NoStrategy {
    public double getPrice(String type,double price){
        if ("普通客户小批量".equals(type)){
            System.out.println("【未使用设计模式】不打折");
            return price;
        }else if ("普通客户大批量".equals(type)){
            System.out.println("【未使用设计模式】 打九折");
            return price*0.9;
        }else if ("老客户小批量".equals(type)){
            System.out.println("【未使用设计模式】 打八折");
            return price*0.8;
        }else if ("老客户大批量".equals(type)){
            System.out.println("【未使用设计模式】打七折");
            return price*0.7;
        }else if ("老客户特大批量".equals(type)){
            System.out.println("【未使用设计模式】打六折");
            return price*0.6;
        }
        return price;
    }
}
