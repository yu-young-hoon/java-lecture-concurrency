package com.yyh;

public class ClassThreadingProblem {
    private int lastIdUsed;
    
    public ClassThreadingProblem(int lastIdUsed) {
        this.lastIdUsed = lastIdUsed;
    }
    
    public synchronized int getNextId() {
        System.out.println("lastIdUsed = " + lastIdUsed);
        return ++lastIdUsed;
    }
    
    public static void main(String[] args) throws InterruptedException {
        final ClassThreadingProblem classThreadingProblem = new ClassThreadingProblem(10);
        
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                classThreadingProblem.getNextId();
            }
        };
        
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        t1.start();
        t2.start();
    }
}
