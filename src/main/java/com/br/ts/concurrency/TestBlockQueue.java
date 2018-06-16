package com.br.ts.concurrency;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 *
 * 结论：take cpu占用0.1%，
 *      poll cpu占用0.5%
 *
 *      对于take操作，由于是阻塞试的，当阻塞时线程进入休眠状态不会占用CPU了
 *      对于poll操作，若delay时间的话，消耗的cpu占比比take要高
 *
 * Created by bearray123 on 18/6/6.
 */
public class TestBlockQueue {


    public static class MyThread extends Thread {

        LinkedBlockingQueue<String> bq;

        public MyThread(String name, LinkedBlockingQueue linkedBlockingQueue) {
            super(name);
            bq = linkedBlockingQueue;
        }

        @Override public void run() {

            while (true) {
                try {
                    //String data = bq.take();
                    String data = bq.poll(10, TimeUnit.MILLISECONDS);
                    //System.out.println("取出来数据了： " + data);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }



    public static void main(String[] strings) {

        final LinkedBlockingQueue<String> blockingQueue = new LinkedBlockingQueue<String>();
        blockingQueue.offer("xionglei");
        blockingQueue.offer("huyu");

        Thread thread = new MyThread("mythread001", blockingQueue);
        thread.start();


    }





}
