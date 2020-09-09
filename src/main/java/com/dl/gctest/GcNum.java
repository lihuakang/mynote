package com.dl.gctest;

/**
 * -Xms20M -Xmx20M -Xmn10M -verbose:gc -XX:+PrintGCDetails -XX:SurvivorRatio=8
 * 说明虚拟机不是通过引用计数算法来判断是否存活的
 */
public class GcNum {
    public Object instance=null;
    private static final int _1MB=1024*1024;
    private byte[] bigSize=new byte[2*_1MB];
    public static void testGC(){
        GcNum objA=new GcNum();
        GcNum objB=new GcNum();
        objA.instance=objB;
        objB.instance=objA;
        objA=null;
        objB=null;
        System.gc();
    }

    public static void main(String[] args) {
        GcNum.testGC();
    }
}
