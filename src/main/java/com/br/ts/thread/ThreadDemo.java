package com.br.ts.thread;

/**
 * Created by bearray123 on 18/4/16.
 *
 * join方法测试
 * 场景：在很多情况下，主线程生成并起动了子线程，如果子线程里要进行大量的耗时的运算，主线程往往将于子线程之前结束，
 * 但是如果主线程处理完其他的事务后，需要用到子线程的处理结果，也就是主线程需要等待子线程执行完成之后再结束，这个时候就要用到join()方法了。
 *
 * 即join()的作用是：“等待该线程终止”，这里需要理解的就是该线程是指的主线程等待子线程的终止。
 * 也就是在子线程调用了join()方法后面的代码，只有等到子线程结束了才能执行。
 *
 * join(long millis) : 相当于加了等待时间限制，最多等多少毫秒，如果到期后就不再等待
 */
public class ThreadDemo {

  static class BThread extends Thread {

    public BThread() {

      super("[BThread] Thread");
    }

    ;

    public void run() {

      String threadName = Thread.currentThread().getName();

      System.out.println(threadName + " start.");

      try {

        for (int i = 0; i < 5; i++) {

          System.out.println(threadName + " loop at " + i);

          Thread.sleep(1000);
        }

        System.out.println(threadName + " end.");
      } catch (Exception e) {

        System.out.println("Exception from " + threadName + ".run");
      }
    }
  }

  static class AThread extends Thread {

    BThread bt;

    public AThread(BThread bt) {

      super("[AThread] Thread");

      this.bt = bt;
    }

    public void run() {

      String threadName = Thread.currentThread().getName();

      System.out.println(threadName + " start.");

      try {

        bt.join();

        System.out.println(threadName + " end.");
      } catch (Exception e) {

        System.out.println("Exception from " + threadName + ".run");
      }
    }
  }

  public static class YieldTest extends Thread {

    public YieldTest(String name) {
      super(name);
    }

    @Override
    public void run() {
      for (int i = 1; i <= 50; i++) {
        System.out.println("" + this.getName() + "-----" + i);
        // 当i为30时，该线程就会把CPU时间让掉，让其他或者自己的线程执行（也就是谁先抢到谁执行）
        if (i == 30) {
          this.yield();
        }
      }
    }
  }

  //    public static void main(String[] args) {
  //
  //        String threadName = Thread.currentThread().getName();
  //
  //        System.out.println(threadName + " start.");
  //
  //        BThread bt = new BThread();
  //
  //        AThread at = new AThread(bt);
  //
  //        try{
  //
  //            bt.start();
  //
  //            Thread.sleep(2000);
  //
  //            at.start();
  //
  //            at.join();
  //
  //        } catch(Exception e) {
  //
  //            System.out.println("Exception from main");
  //
  //        }
  //
  //        System.out.println(threadName + " end!");
  //
  //    }
}
