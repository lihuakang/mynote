package com.dl.jvmtest2;

/**
 * @Author : lhk
 * @Description :
 * @Date : 2020/8/27 17:18
 *  -Xss128k
 */
public class JavaVMStackSOF {
    private int stackLength=1;
    public void stackLeak(){
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        JavaVMStackSOF oom=new JavaVMStackSOF();
        try {
            oom.stackLeak();
        }catch (Throwable e){
            System.out.println("stack length"+oom.stackLength);
            throw e;
        }
    }
}
