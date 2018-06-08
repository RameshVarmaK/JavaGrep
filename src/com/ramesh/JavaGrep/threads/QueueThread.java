package com.ramesh.JavaGrep.threads;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;

public class QueueThread extends Thread {
    private Queue<String> dataQueue;
    private AtomicBoolean compilationFlag;
    public QueueThread(Queue<String> dataQueue, AtomicBoolean compilationFlag){
        this.dataQueue=dataQueue;
        this.compilationFlag=compilationFlag;
    }
    @Override
    public void run() {
        while (true){
            if(compilationFlag.get()==true){
                break;
            }
            System.out.println(dataQueue.size());
        }
    }
}
