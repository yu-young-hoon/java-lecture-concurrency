package com.yyh;

import java.util.concurrent.*;

public class ExecutorServiceTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("start " + Thread.currentThread().getName());
                    Thread.sleep(10);
                    System.out.println("end " + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Callable callable = new Callable() {
            @Override
            public Object call() throws Exception {
                try {
                    System.out.println("start " + Thread.currentThread().getName());
                    Thread.sleep(10);
                    System.out.println("end " + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return Thread.currentThread().getName();
            }
        };

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0 ; i < 10 ; ++i) {
            Future<String> future = executorService.submit(callable);
            System.out.println(future.get());
        }
    }
}
