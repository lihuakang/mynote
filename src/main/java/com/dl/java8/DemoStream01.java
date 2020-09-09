package com.dl.java8;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @Author : lhk
 * @Description :
 * @Date : 2020/8/25 14:46
 */
public class DemoStream01 {
    public static void main(String[] args) throws IOException {
        String contens=new String(Files.readAllBytes(
                Paths.get("D:/a.txt")
        ), StandardCharsets.UTF_8);
        List<String> words= Arrays.asList(contens.split(","));
        long count=0;
        for (String w:words){
            if (w.length()>12)count++;
        }
        System.out.println(count);
        count=words.stream().filter(w->w.length()>12).count();
        System.out.println(count);
        count=words.parallelStream().filter(w->w.length()>12).count();
        System.out.println(count);
        Stream<String> s=Stream.of("sdf","sdf","sss");
        int[] arr=new int[4];
        arr[0]=12;
        arr[1]=1124;
        arr[2]=435;
        arr[3]=5432;
        Arrays.stream(arr,0,1);
        Stream<String> echos=Stream.generate(()->"echo");
        Stream<Double> randoms=Stream.generate(Math::random);
        Stream<String> toLowerCase=words.stream().map(String::toLowerCase);
    }
}
