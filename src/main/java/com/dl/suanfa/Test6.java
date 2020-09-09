package com.dl.suanfa;

/**
 * 将一个字符串中的空格替换成 "%20"。
 */
public class Test6 {
    public String replace(String str){
        String replace = str.replace(" ", "%20");
        return replace;
    }

    public static void main(String[] args) {
        Test6 test6=new Test6();
        String a="fsd fsds sfd";
        String replace = test6.replace(a);
        System.out.println(replace);
    }
}
