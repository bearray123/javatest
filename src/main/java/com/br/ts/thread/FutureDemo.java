package com.br.ts.thread;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 *
 * FutureTask --> RunnableFuture  --> Runnable,Future
 *
 * Runnable 与 Callable 一个主要区别是 Callable可以返回结果（Future就是结果的封装）
 * 而RunnableFuture相当于赋予了Runnable一个获取结果的能力（Future）
 *
 * FutureTask就是RunnableFuture的具体实现类
 *
 * Created by bearray123 on 18/5/27.
 *
 */
public class FutureDemo {

    private FutureTask<String> ft = new FutureTask<String>(new Worker());


    public static void main(String [] s) {

        FutureDemo demo = new FutureDemo();

        Thread thread = new Thread(demo.ft);
        thread.start();

        try {
            //主线程睡眠1秒
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //主线程中去取消子线程任务
        //demo.ft.cancel(true);

        //直接调thread.interrupt（）和ft.cancel是有区别的，interrupt后通过ft.get（）还可以取结果
        //但任务cancel后是无法再get获取结果，再去get只会获取到异常InterruptedException
        //thread.interrupt();


        try {

            String result = demo.ft.get();
            System.out.printf("main主线程： futuretask的执行结果 = "+ result);

        } catch (InterruptedException e) {
            System.out.println("main主线程收到 InterruptedException ");
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (CancellationException e) { //如果任务线程在获取结果get之前取消则get结果时会抛出该异常
            System.out.println("main主线程收到 CancellationException ");
        }
    }


}


