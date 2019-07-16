package ru.eltex;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Scanner;

public class Main{
    
    private static Integer a = 0;
    
    private static volatile Integer v = 0;
    
    static ReentrantLock lock = new ReentrantLock();
    
    private static Semaphore sem = new Semaphore(1,true);
    
    public static void reset_counter() {
        a = 0;
        v = 0;
    }
    
    public static synchronized void counter(MyThread thread) {
        Integer temp = a;
        a = a + 1;
        System.out.println("Thread id: " + thread.getId());
        System.out.printf("%d = %d + 1\n", a, temp);
    }
    
    public static void v_counter(MyThread thread) {
        Integer temp = v;
        v = v + 1;
        System.out.println("Thread id: " + thread.getId());
        System.out.printf("%d = %d + 1\n", v, temp);
    }
    
    public static void rl_counter(MyThread thread) {
        lock.lock();
        Integer temp = a;
        a = a + 1;
        System.out.println("Thread id: " + thread.getId());
        System.out.printf("%d = %d + 1\n", a, temp);
        lock.unlock();
    }
    
    public static void sp_counter(MyThread thread) throws InterruptedException{
        sem.acquire();
        Integer temp = a;
        a = a + 1;
        System.out.println("Thread id: " + thread.getId());
        System.out.printf("%d = %d + 1\n", a, temp);
        sem.release();
    }
    
    public static void main(String args[]) throws InterruptedException{
        Integer quit = 0;
        String [] menu = { "Synchronized", "Volatile", "ReentrantLock", "Semaphore" };
        while (quit == 0) {
            reset_counter();
            System.out.println();
            System.out.println("1-" + menu[0]);
            System.out.println("2-" + menu[1]);
            System.out.println("3-" + menu[2]);
            System.out.println("4-" + menu[3]);
            System.out.println("q-Exit");
            System.out.println("Enter 1,2,3,4 or q: ");
            Scanner in = new Scanner(System.in);
            Character op = in.next().charAt(0);
            
            switch (op) {
                case '1': {
                    System.out.println(menu[Character.getNumericValue(op) - 1]);
                    for (int i = 0; i < 10; i++) {
                        Runnable r = new MyThread(i, Character.getNumericValue(op));
                        Thread t = new Thread (r);
                        t.start();
                        t.join();
                        
                    }
                } break;
                
                case '2': {
                    System.out.println(menu[Character.getNumericValue(op) - 1]);
                    for (int i = 0; i < 10; i++) {
                        Runnable r = new MyThread(i, Character.getNumericValue(op));
                        Thread t = new Thread (r);
                        t.start();
                        t.join();
                    }
                } break;
                
                case '3': {
                    System.out.println(menu[Character.getNumericValue(op) - 1]);
                    for (int i = 0; i < 10; i++) {
                        Runnable r = new MyThread(i, Character.getNumericValue(op));
                        Thread t = new Thread (r);
                        t.start();
                        t.join();
                    }
                } break;
                
                case '4': {
                    System.out.println(menu[Character.getNumericValue(op) - 1]);
                    for (int i = 0; i < 10; i++) {
                        Runnable r = new MyThread(i, Character.getNumericValue(op));
                        Thread t = new Thread (r);
                        t.start();
                        t.join();
                    }
                } break;
                
                
                case 'q': { quit = 1; } break;
            }
        }
    }
}
