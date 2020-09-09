package com.dl.jvmtest;

import java.util.ArrayList;
import java.util.List;

/**
 * -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 */
public class Demo {
    public static void main(String[] args) {
        List list=new ArrayList();
       while (true){
           list.add(new A());
       }
    }
}
