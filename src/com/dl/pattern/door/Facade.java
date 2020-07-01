package com.dl.pattern.door;

/**
 * 门面角色类
 */
public class Facade {
    public void test(){
    ModuleA a=new ModuleA();
    a.testA();
    ModuleB b=new ModuleB();
    b.testB();
    ModuleC c=new ModuleC();
    c.testC();
    }
}
