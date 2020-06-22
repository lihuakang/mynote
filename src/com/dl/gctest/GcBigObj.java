package com.dl.gctest;

/**
 * 设置大对象直接进入老年代
 *  -verbose:gc -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:SurvivorRatio=8
 *  -XX:+UseSerialGC
 *   -XX:PretenureSizeThreshold=3145728
 */
public class GcBigObj {
    private static final int _1MB=1024*1024;
    public static void main(String[] args) {
        byte[] allocation;
        allocation=new byte[4*_1MB];
    }
}
