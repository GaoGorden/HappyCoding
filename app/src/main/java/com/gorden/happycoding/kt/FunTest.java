package com.gorden.happycoding.kt;

public class FunTest {

    private static String a = "test";

    private static String foo(boolean flag){
        if (flag){
            a =  "dive into java";
        } else {
            a = "";
        }
        return a;
    }

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println("thread one:" + foo(true));
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println("thread two:" + foo(false));
                }
            }
        }).start();
    }
}
