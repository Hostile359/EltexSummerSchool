package ru.eltex;

import java.util.concurrent.*;

class MyThread implements Runnable{
    
    MyThread () { }
    
    public void run() {
        Main.counter();
    }
}
