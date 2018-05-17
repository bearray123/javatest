package com.br.ts.algorith;

import java.util.Random;

public class TestJava {

  public static void main(String[] args) {

    Random ran = new Random();
    int[] a = new int[10];
    for (int i = 0; i < 10; i++) {
      a[i] = ran.nextInt(100);
      System.out.printf(" " + a[i]);
    }
    System.out.printf("\n");
    quickSort1(a, 0, a.length - 1);

    for (int v : a) {
      System.out.printf(" " + v);
    }
    System.out.printf("\n");
  }

  private static void quickSort1(int[] a, int start, int end) {
    if (start >= end) {
      return;
    }
    int key = a[start];
    int left = start;
    int right = end;

    while (left < right) {
      while (a[right] >= key && right > left) {
        right--;
      }
      while (a[left] <= key && right > left) {
        left++;
      }
      swap(a, right, left);
    }
    if (a[right] <= a[start]) {
      // if ((a[right] == a[start]))
      System.out.printf("\n 出现了！！");

      swap(a, right, start);
    } else {
      right--;
    }
    quickSort1(a, start, right - 1);
    quickSort1(a, right + 1, end);
  }

  private static void swap(int[] a, int right, int left) {
    int temp = a[right];
    a[right] = a[left];
    a[left] = temp;
  }
}