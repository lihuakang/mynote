package com.dl.shejimoshi.multition;

import java.util.ArrayList;
import java.util.Random;

/**
 *   多例模式
 */
public class Emperor {
    private static int maxNumOfEmperor=2;//最多只有两个实例

    private static ArrayList emperorInfoList=new ArrayList(maxNumOfEmperor);//装皇帝名字

    private static ArrayList emperorList=new ArrayList(maxNumOfEmperor);// 装皇帝的实例

    private static int countNumOfEmperor=0; //正在被人尊称的皇帝

    static {
        //生产皇帝
        for (int i=0;i<maxNumOfEmperor;i++){
            emperorList.add(new Emperor("皇帝"+i));
        }
    }

    public Emperor(String info) {
        emperorInfoList.add(info);
    }

    public static Emperor getInstance(){
        Random random=new Random();
        countNumOfEmperor=random.nextInt(maxNumOfEmperor);
        return (Emperor) emperorInfoList.get(countNumOfEmperor);
    }

    //皇帝叫什么名字啊
    public static void emperorInfo(){
        System.out.println(emperorInfoList.get(countNumOfEmperor));
    }
}
