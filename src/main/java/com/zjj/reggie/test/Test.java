package com.zjj.reggie.test;

public class Test {
    public static void main(String[] args) {
        String name="aaaaa.jpg";
        String suffix=name.substring(name.indexOf("."));
        String suffix1=name.substring(2);
        System.out.println(name.lastIndexOf("."));
        System.out.println(suffix);
        System.out.println(suffix1);
    }
}


