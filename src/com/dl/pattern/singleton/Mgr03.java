package com.dl.pattern.singleton;

public class Mgr03 {
    private Mgr03(){};

    private static class Mgr03Holder{
        private final static Mgr03 Instance=new Mgr03();
    }

    public static Mgr03 getInstance(){
        return Mgr03Holder.Instance;
    }

    public static void main(String[] args) {
        for (int i=0;i<100;i++){
            new Thread(()-> System.out.println(Mgr03.getInstance().hashCode())).start();
        }
    }
}
