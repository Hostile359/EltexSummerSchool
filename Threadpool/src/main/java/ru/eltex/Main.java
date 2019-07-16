package ru.eltex;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Scanner;

public class Main{
    
    private static Integer a = 0;
    
    public static void reset_counter() {
        a = 0;
    }
    
    public static synchronized void counter() {
        Integer temp = a;
        a = a + 1;
        System.out.println(Thread.currentThread().getName());
        System.out.printf("%d = %d + 1\n", a, temp);
    }

    
    public static void main(String args[]) throws InterruptedException{
        
        ExecutorService exec = Executors.newFixedThreadPool(10);
        Runnable r = new MyThread();
        for(int i = 0; i < 10; i++)
            exec.submit(r);
        //Thread.sleep(2000);
        exec.shutdown();
    }
}
