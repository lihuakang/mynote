package com.dl.jvmtest2;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : lhk
 * @Description :
 * @Date : 2020/8/27 16:49
 * -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * -Xms初始的Heap的大小。
 *
 * -Xmx最大Heap的大小。
 */
public class HeapOOM {
    static class OOMObject{

    }
    public static void main(String[] args) {
        List<OOMObject> list=new ArrayList<OOMObject>();
        while (true){
            list.add(new OOMObject());
        }
    }
}
