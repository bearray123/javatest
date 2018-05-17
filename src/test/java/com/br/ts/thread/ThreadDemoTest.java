package com.br.ts.thread;

import org.junit.Test;
import com.br.ts.thread.ThreadDemo.*;

/**
 * Created by bearray123 on 18/4/16.
 */
public class ThreadDemoTest {

  @Test
  public void testYield() {

    YieldTest yt1 = new YieldTest("张三");
    YieldTest yt2 = new YieldTest("李四");
    yt1.start();
    yt2.start();
  }

  @Test
  public void testJoin() {

    String threadName = Thread.currentThread().getName();

    System.out.println(threadName + " start.");

    BThread bt = new BThread();

    AThread at = new AThread(bt);

    try {

      bt.start();

      Thread.sleep(2000);

      at.start();

      at.join();
    } catch (Exception e) {

      System.out.println("Exception from main");
    }

    System.out.println(threadName + " end!");
  }
}
