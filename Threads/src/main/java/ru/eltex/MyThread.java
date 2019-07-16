package ru.eltex;

import java.util.concurrent.*;

class MyThread implements Runnable{
    private Integer thread_id;
    private Integer type;
    
    MyThread (Integer id, Integer type) {
        this.thread_id = id; 
        this.type = type;//1-synchronazed, 2-volatile, 3-ReentrantLock, 4-Semaphore
    }
    
    public void run() {
        switch (this.type) {
            case 1: { Main.counter(this); } break;
            case 2: { Main.v_counter(this); } break;
            case 3: { Main.rl_counter(this); } break;
            case 4: {
                try {
                    Main.sp_counter(this);
                } catch(InterruptedException ex) {
                    ex.printStackTrace();
                }
            }break;
        }
    }
    
    public Integer getId() {
        return this.thread_id;
    }
}
