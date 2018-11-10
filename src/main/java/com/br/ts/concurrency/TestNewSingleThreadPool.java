package com.br.ts.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * 测试newSingleThreadExecutor形式的线程池是否有runnbale任务堆积
 * 结论：当添加任务的频率高于执行任务的频率时肯定会堆积
 *
 * Created by bearray123 on 18/11/10.
 */
public class TestNewSingleThreadPool {

    public ExecutorService mSingleThreadExecutor = Executors.newSingleThreadExecutor();


    public static void main(String [] strings) {

        TestNewSingleThreadPool demo = new TestNewSingleThreadPool();

        while (true) {
            demo.addTask();
            try {
                // 添加任务按照10ms去添加
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


    private void addTask() {

        mSingleThreadExecutor.execute(new Runnable() {
            public void run() {
                try {
                    // 任务执行按照 1000ms 去执行
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

    }


}
