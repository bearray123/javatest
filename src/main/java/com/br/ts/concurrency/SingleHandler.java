package com.br.ts.concurrency;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by bearray123 on 18/3/26.
 */
public class SingleHandler {

  //    public static volatile SingleHandler mInstance;

  private ReentrantLock lock = new ReentrantLock(true);

  public int age = 0;

  //    /**
  //     * 静态内部类只有使用时才加载
  //     */
  //    public static final class SingleHolder {
  //        public static final SingleHandler INSTANCE = new SingleHandler();
  //    }
  //
  //    /**
  //     * 推荐写法一：最优写法
  //     * @return
  //     */
  //    public static SingleHandler getmInstance() {
  //        return SingleHolder.INSTANCE;
  //    }

  public static volatile SingleHandler mInstance;

  /**
   * 推荐写法二：双重锁检测
   * 必须加volatile申明才能保证安全，防止指令重排序
   */
  public static SingleHandler getmInstance() {
    if (mInstance == null) {
      synchronized (SingleHandler.class) {
        if (mInstance == null) {
          mInstance = new SingleHandler();
        }
      }
    }
    return mInstance;
  }

  /**
   * 效率低的写法
   */
  //    public synchronized static SingleHandler getmInstance() {
  //        if (mInstance == null) {
  //                mInstance = new SingleHandler();
  //
  //        }
  //        return mInstance;
  //    }
  private SingleHandler() {

  }

  public void ageAdd() {

    System.out.println(Thread.currentThread().getName() + " try to get lock @ add");

    lock.lock();

    try {
      //        synchronized (SingleHandler.class) {

      System.out.println(
          Thread.currentThread().getName() + " has got lock $$$     @ add" + "object=" + this);

      age++;
      try {
        Thread.sleep(5000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      //        }
    } finally {
      System.out.println(Thread.currentThread().getName() + " has realease lock ~    @ add");
      lock.unlock();
    }
  }

  public void ageSub() {

    System.out.println(Thread.currentThread().getName() + " try to get lock @ sub ");

    lock.lock();

    try {
      System.out.println(
          Thread.currentThread().getName() + " has got lock $$$     @ sub" + "object=" + this);
      //        synchronized (SingleHandler.class) {
      age--;
      try {
        Thread.sleep(5000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      //        }
    } finally {
      System.out.println(Thread.currentThread().getName() + " has realease lock ~    @ sub");
      lock.unlock();
    }
  }
}
