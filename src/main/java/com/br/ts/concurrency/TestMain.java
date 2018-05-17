package com.br.ts.concurrency;

/**
 * Created by bearray123 on 18/3/26.
 */
public class TestMain {

  public static void main(String[] args) {

    System.out.print("Hello Concurrency!");

    new Thread(new Runnable() {
      @Override
      public void run() {
        int i = 0;
        while (i++ < 10) {

          SingleHandler.getmInstance().ageAdd();
          //                    System.out.println("Thread-A release the lock");
          //                    try {
          //                        Thread.sleep(1000); //sleep不会释放锁
          //                        System.out.println("Thread-A sleep 1000ms");
          //                    } catch (InterruptedException e) {
          //                        e.printStackTrace();
          //                    }finally {
          //                        i++;
          //                    }
        }
      }
    }, "Thread-A").start();

    new Thread(new Runnable() {
      @Override
      public void run() {
        int i = 0;
        while (i++ < 10) {
          //                    System.out.println("Thread-B get Age = " + SingleHandler.getmInstance().age);
          SingleHandler.getmInstance().ageSub();
          //                    System.out.println("Thread-B release the lock");
          //                    try {
          //                        Thread.sleep(1000);
          //                        System.out.println("Thread-B sleep 1000ms");
          //                    } catch (InterruptedException e) {
          //                        e.printStackTrace();
          //                    } finally {
          //                        i++;
          //                    }

        }
      }
    }, "Thread-B").start();
  }
}
