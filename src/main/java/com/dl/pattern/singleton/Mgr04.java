package com.dl.pattern.singleton;

public enum  Mgr04 {
    INSTANCE;

    public static void main(String[] args) {
        for (int i=0;i<100;i++){
            new Thread(()-> System.out.println(Mgr04.INSTANCE.hashCode())).start();
        }
    }
}
