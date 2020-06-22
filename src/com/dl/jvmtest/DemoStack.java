package com.dl.jvmtest;

/**
 * VM Args:-Xss128k
 */
public class DemoStack {
    private int stackLength=1;
    public void stackLeadk(){
        stackLength++;
        stackLeadk();
    }

    public static void main(String[] args) {
        DemoStack demoStack=new DemoStack();
        try {
            demoStack.stackLeadk();;
        }catch (Throwable e){
            System.out.println("stack Length:"+demoStack.stackLength);
            throw e;
        }
    }
}
