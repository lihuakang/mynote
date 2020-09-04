package com.dl.ll;

import java.util.ArrayList;
import java.util.List;

public class Test2{

    public static void main(String[] args) {
        List<Integer>  list=new ArrayList();
        list.add(5);
        list.add(10);
        for (int x:list){
            System.out.println(x+",");
        }
    }
}
