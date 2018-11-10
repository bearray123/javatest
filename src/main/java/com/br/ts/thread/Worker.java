package com.br.ts.thread;

import java.util.concurrent.Callable;

/**
 * 工作任务线程
 * Created by bearray123 on 18/5/27.
 */
public class Worker implements Callable<String> {



    public String call() throws Exception {
        System.out.printf("线程" + Thread.currentThread().getName() + " 开始执行!\n");

        String result = "execute result is ok!";

        for(;;) {
            if (Thread.currentThread().isInterrupted()) {
                break;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                System.out.println("子线程收到 InterruptedException");
                result =  "任务被取消了";
            }
        }

        System.out.printf("线程" + Thread.currentThread().getName() + " 执行完毕\n");
        return result;
    }
}
