package com.dl.shejimoshi.strategy;

/**
 * 这是锦囊
 */
public class Context {

    private Istrategy istrategy;
    public Context(Istrategy istrategy){
        this.istrategy=istrategy;
    }

    //使用计谋了
    public void operate(){
        this.istrategy.oprate();
    }
}
