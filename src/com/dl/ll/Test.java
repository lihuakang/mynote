package com.dl.ll;

import java.util.ArrayList;
import java.util.List;

public class Test {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        String string=" Integer, id; int, parkingLotId; String, vehicelModelName; String, plateNumber; double, minuteMoney; int ,minuteDiscount; double, mileageMoney; int, mileageDiscount; double, nightMoney; int, nightDiscount; double, fullDayMoney; int ,fullDayDiscount";
        String b=string.replace(" ","");
        StringBuffer show = show(b);
        System.out.println(show);
    }

    public static StringBuffer show(String str){
        StringBuffer stringBuffer=new StringBuffer();
        String[] split = str.split(";");
        for(String s:split){
            String[] split1 = s.split(",");
            stringBuffer.append("\"").append(split1[1]).append("\"").append(":").append("\"").append(split1[0]).append("\"").append(",");
        }
        return stringBuffer;
    }


}
