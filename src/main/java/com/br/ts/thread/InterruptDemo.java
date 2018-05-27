package com.br.ts.thread;

/**
 *
 * 结论：
 *    1. 调用Thread#interrupt()之后，线程不会中断停止，它只是收到一个中断信号而已，具体是否停止由线程自身控制
 *
 * Created by bearray123 on 18/5/23.
 */
public class InterruptDemo {


    public static void main(String[] args) {


        SubThread subThread1 = new SubThread("subThread-1");
        SubThread subThread2 = new SubThread("subThread-2");


        subThread1.start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {

        }
        subThread1.interrupt();
    }



    private static class SubThread extends Thread {

        SubThread(String name) {
            super(name);
        }

        private volatile boolean interruptFlag = true;

        @Override public void run() {
            System.out.printf(Thread.currentThread().getName() + "开始执行 run方法 \n" );
            while (/*interruptFlag*/ !isInterrupted()) {
                System.out.printf(Thread.currentThread().getName() + " is Running! \n" );
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    //当支持中断的阻塞方法（例如sleep、wait、join，BlockingQueue#take#put，IO的操作阻塞不会抛这种异常）
                    // 收到中断异常后会重置中断标志位，所以
                    // 当收到InterruptedException后必须处理或往上层抛，
                    interrupt(); //重新set中断标志
                    continue;
                }

                System.out.printf(Thread.currentThread().getName() + " run方法最后一行代码执行$ \n" );
            }

        }
    }


}
