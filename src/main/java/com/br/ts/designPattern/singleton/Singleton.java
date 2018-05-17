package com.br.ts.designPattern.singleton;

/**
 * Effective Java 推荐的懒汉式加载
 * 最优答案
 * 只有调用getInstance时才会加载SingletonHolder
 **/
public class Singleton {

  private static class SingletonHolder {

    public static final Singleton INSTANCE = new Singleton();
  }

  private Singleton() {
  }

  public static final Singleton getInstance() {
    return SingletonHolder.INSTANCE;
  }

  public static void main(String[] args) {
    Singleton s = Singleton.getInstance();
  }
}

