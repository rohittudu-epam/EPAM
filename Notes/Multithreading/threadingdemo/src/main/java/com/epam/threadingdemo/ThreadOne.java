package com.epam.threadingdemo;

public class ThreadOne extends Thread {
    public ThreadOne(String name){
        super(name);
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(String.format("Currrent Thead: %s & Thread Count: %d", Thread.currentThread().getName(), i));
        }
    }
    
}
