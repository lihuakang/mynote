package com.dl.shejimoshi.singleton;

/**
 *   懒汉式单例  线程不安全
 */
public class Emperor {
    private static Emperor emperor=null;

    private Emperor(){

    }
    public static Emperor getInstance(){
        if (emperor==null){
            emperor=new Emperor();
        }
        return emperor;
    }
    //皇帝叫什么名字啊
    public static void emperorInfo(){
        System.out.println("我是皇帝某某某");
    }
}
