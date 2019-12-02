package com.yyh;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrencyCollection {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable1 = new Runnable() {
            Map<Integer, String> map = new HashMap<>();
            public synchronized void putDelegate(Integer a, String b) {
                map.put(a, String.valueOf(b));
            }
            @Override
            public void run() {
                long startTime = System.currentTimeMillis();
                for(int i = 0 ; i < 10000 ; ++i) {
                    putDelegate(i, String.valueOf(i));
                }
                System.out.println("Used HashMap = " + (System.currentTimeMillis() - startTime));
            }
        };
        Runnable runnable2 = new Runnable() {
            Map<Integer, String> map = new ConcurrentHashMap<>();
            @Override
            public void run() {
                long startTime = System.currentTimeMillis();
                for(int i = 0 ; i < 10000 ; ++i) {
                    map.put(i, String.valueOf(i));
                }
                System.out.println("Used ConcurrentHashMap = " + (System.currentTimeMillis() - startTime));
            }
        };
        Thread t1 = new Thread(runnable1);
        t1.start();
        Thread t2 = new Thread(runnable2);
        t2.start();
        Thread t3 = new Thread(runnable1);
        t3.start();
        Thread t4 = new Thread(runnable2);
        t4.start();
    }
}
