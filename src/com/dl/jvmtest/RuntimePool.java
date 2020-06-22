package com.dl.jvmtest;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * -XX:PermSize=10m -XX:MaxPermSize=10m
 * -XX:MetaspaceSize=10M -XX:MaxMetaspaceSize=10M
 */
public class RuntimePool {
    public static void main(String[] args) {
        while (true){
            Enhancer enhancer=new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                    return methodProxy.invokeSuper(o,args);
                }
            });
            enhancer.create();
        }
    }

    static class OOMObject{

    }
}
