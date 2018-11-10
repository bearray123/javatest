package com.br.ts.thread;

/**
 * Created by bearray123 on 18/10/17.
 */
public class AliveDemo {


    private Thread thread1 = new Thread(new Runnable() {
        public void run() {
            for(;;) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                    System.out.println(thread1.getName() + " 已经跳出循环，马上结束");
                    return;
                }
            }
        }
    });


    private void testAlive() throws InterruptedException {
        System.out.println("#1" + thread1.getName() + " Alive=" + thread1.isAlive());
        thread1.start();
        System.out.println("#2" + thread1.getName() + " Alive=" + thread1.isAlive());

        Thread.sleep(2000);

        System.out.println("#3" + thread1.getName() + " Alive=" + thread1.isAlive());

        Thread.sleep(3000);

        thread1.interrupt(); //这里去interrupt是异步的，调了interrupt后立即执行后面的代码，这时线程不一定马上收到终端信号

        Thread.sleep(10); //所有这里睡眠10ms很重要，不睡眠的话下面#4打印的就是true

        System.out.println("#4" + thread1.getName() + " Alive=" + thread1.isAlive());

        Thread.sleep(3000);

        System.out.println("#5" + thread1.getName() + " Alive=" + thread1.isAlive());

    }




    public static void main(String[] args) {

        AliveDemo demo = new AliveDemo();
        try {
            demo.testAlive();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
