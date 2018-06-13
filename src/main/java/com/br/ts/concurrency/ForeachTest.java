package com.br.ts.concurrency;

import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * 这个demo是测试foreach语句对LinkedBlockingQueue遍历时是否是线程安全的
 * 结论：线程安全
 * Created by bearray123 on 18/6/13.
 */
public class ForeachTest {


    public static void main(String[] strings) {

        final LinkedBlockingQueue<String> blockingQueue = new LinkedBlockingQueue<String>();
        blockingQueue.offer("xionglei");
        blockingQueue.offer("huyu");

        //线程1 是打印线程，永不停止
        new Thread(new Runnable() {
            public void run() {
                for (String value : blockingQueue) {
                    System.out.println(Thread.currentThread().getName() + " 中打印 ：" + value.toString());
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "Thread-1-print").start();

        //线程2 是打印线程，永不停止
        new Thread(new Runnable() {
            public void run() {
                for (String value : blockingQueue) {
                    System.out.println(Thread.currentThread().getName() + " 中打印 ：" + value.toString());
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "Thread-2-print").start();

        // 线程3 每隔300ms 去add一次数据 依次从xionglei-1开始， xionglei-1  xionglei-2 。。。
        // 5秒后被 线程4中断,停止运行
        final Thread thread3 = new Thread(new Runnable() {
            public void run() {
                int i = 1;
                for (String value : blockingQueue) {
                    String toAdd = "xionglei-" + i++;
                    blockingQueue.add(toAdd);
                    System.out.println(Thread.currentThread().getName() + "添加了 " +toAdd + " $$$$$$$$$" );
                    try {
                        Thread.currentThread().sleep(300);
                    } catch (InterruptedException e) {
                        System.out.println("#############" + Thread.currentThread().getName() + "被终止了 #########" );
                        return ;
                    }
                }
            }
        }, "Thread-3-add");
        thread3.start();

        //线程4 是清理队列的线程，睡眠5s后 先中断线程3，要线程3终止，然后去清空队列
        new Thread(new Runnable() {
            public void run() {

                try {
                    Thread.sleep(5000);

                    thread3.interrupt();

                    System.out.println(Thread.currentThread().getName() + " 执行清理 blockingQueue.clear");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "Thread-4-clear").start();


    }


}
