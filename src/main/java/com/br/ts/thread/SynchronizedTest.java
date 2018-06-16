package com.br.ts.thread;

/**
 *
 * 测试synchronized对象锁
 * 结论：当锁同一个对象时，必须释放锁才后进入另一个锁的方法
 * Created by bearray123 on 18/6/16.
 */
public class SynchronizedTest {

    /**
     * 锁对象
     * @throws InterruptedException
     */
    public synchronized void add () throws InterruptedException {
        System.out.println("run add!");
        //持有锁10s
        Thread.sleep(10000);
    }

    /**
     * 锁对象
     */
    public synchronized void delete() {
        System.out.println("run delete!");
    }

    /**
     * 锁class
     */
    public synchronized static void update() {
        System.out.println("run update!");
    }


    public static void main(String [] strings) {

        final SynchronizedTest demo = new SynchronizedTest();

        new Thread(new Runnable() {
            public void run() {
                try {
                    demo.add();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                demo.delete();
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                demo.update();
            }
        }).start();
    }


}
