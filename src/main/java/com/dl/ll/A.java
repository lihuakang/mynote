package com.dl.ll;

import java.util.HashMap;
import java.util.Map;

public class A {
    public static void main(String[] args) {
        Map map=new HashMap();
        map.put("tom",123.2);
        map.put("tom",(Double)map.get("tom")+100);

        System.out.println(map.get("tom"));
    }
}