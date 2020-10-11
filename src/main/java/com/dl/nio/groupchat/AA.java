package com.dl.nio.groupchat;

import java.util.Scanner;

public class AA {
    static String[] usernames=new String[10];
    static String[] passwords=new String[10];
    public static void main(String[] args) {
        int x= 0;
        Scanner scanner=new Scanner(System.in);
        while (true) {
            aa:
            System.out.println("请选择操作【注册，登录,查询，删除】");
            String op = scanner.next();
            switch (op) {
                case "登录": {
                    System.out.println("请输入用户名");
                    String username = scanner.next();
                    if ("".equals(username) || "".trim().equals(username) || username.trim().length() == 0) {
                        System.out.println("用户名不能为空");
                    }
                    while (username.length() < 8 || username.length() > 16) {
                        System.out.println("用户名长度为8-16之间");
                        username = scanner.next();
                    }
                    System.out.println("请输入密码");
                    Scanner scanner1 = new Scanner(System.in);
                    String password = scanner1.next();
                    if ("".equals(password) || "".trim().equals(password) || password.trim().length() == 0) {
                        System.out.println("密码不能为空");
                        return;
                    }
                    if (password.length() < 8 || password.length() > 16) {
                        System.out.println("密码长度为8-16之间");
                        password = scanner1.next();
                    }

                    boolean flag=false;
                    for (int i = 0; i < usernames.length; i++) {
                        if (username.equals(usernames[i]) && password.equals(passwords[i])) {
                            flag=true;
                            System.out.println("登录成功");
                            break;
                        }
                    }
                    if (flag==false) {
                        System.out.println("登录失败,用户名或密码不正确");
                    }
                    continue;
                }
                case "查询":{
                    System.out.println("输入用户名");
                    String us=scanner.next();
                    for (int i=0;i<usernames.length;i++){
                        if (usernames[i]!=null&&usernames[i].startsWith(us)){
                            System.out.println("相似的用户有"+usernames[i]);
                        }
                    }
                    continue;
                }
                case "注册": {
                    System.out.println("请输入用户名");
                    String username = scanner.next();
                    if ("".equals(username) || "".trim().equals(username) || username.trim().length() == 0) {
                        System.out.println("用户名不能为空");
                    }
                    while (username.length() < 8 || username.length() > 16) {
                        System.out.println("用户名长度为8-16之间");
                        username = scanner.next();
                    }
                    System.out.println("请输入密码");
                    Scanner scanner1 = new Scanner(System.in);
                    String password = scanner1.next();
                    if ("".equals(password) || "".trim().equals(password) || password.trim().length() == 0) {
                        System.out.println("密码不能为空");
                    }
                    if (password.length() < 8 || password.length() > 16) {
                        System.out.println("密码长度为8-16之间");
                        password = scanner1.next();
                    }

                    usernames[x] = username;
                    passwords[x] = password;
                    x++;
                    System.out.println("注册成功");
                    continue;
                }
                case "删除": {
                    System.out.println("请先登录");
                    System.out.println("请输入用户名");
                    String username = scanner.next();
                    if ("".equals(username) || "".trim().equals(username) || username.trim().length() == 0) {
                        System.out.println("用户名不能为空");
                    }
                    while (username.length() < 8 || username.length() > 16) {
                        System.out.println("用户名长度为8-16之间");
                        username = scanner.next();
                    }
                    System.out.println("请输入密码");
                    Scanner scanner1 = new Scanner(System.in);
                    String password = scanner1.next();
                    if ("".equals(password) || "".trim().equals(password) || password.trim().length() == 0) {
                        System.out.println("密码不能为空");
                        return;
                    }
                    if (password.length() < 8 || password.length() > 16) {
                        System.out.println("密码长度为8-16之间");
                        password = scanner1.next();
                    }
                    ee:
                    for (int i = 0; i < usernames.length; i++) {
                        if (username.equals(usernames[i]) && password.equals(passwords[i])) {
                            System.out.println("登录成功");
                            System.out.println("请输入你要删除的用户名");
                            String user=scanner.next();
                            for (int j=0;j<usernames.length;j++){
                                if (user.equals(usernames[j])){
                                    usernames[j]="";
                                    passwords[j]="";
                                    System.out.println("删除成功");
                                    continue ee;
                                }
                            }
                            System.out.println("没有此用户");
                            break;
                        }
                    }
//                    System.out.println("请重新登录,用户名或密码不正确");
                    continue;
                }

            }
        }
    }
}
