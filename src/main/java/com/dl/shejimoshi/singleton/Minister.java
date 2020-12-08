package com.dl.shejimoshi.singleton;

public class Minister {
    public static void main(String[] args) {
        Emperor emperor1=Emperor.getInstance();
        emperor1.emperorInfo();

        Emperor emperor2=Emperor.getInstance();
        emperor1.emperorInfo();

        Emperor emperor3=Emperor.getInstance();
        emperor1.emperorInfo();
    }
}
