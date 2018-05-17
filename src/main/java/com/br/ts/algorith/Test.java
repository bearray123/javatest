package com.br.ts.algorith;

// "static void main" must be defined in a public class.
public class Test {
  public static void main(String[] args) {
    System.out.println("Hello World!");
    // int a[] = {16,2,9,3,8,28,19,17,34,45};
    int a[] = {8, 2, 9, 3};
    quickSort(a, 0, a.length - 1);
  }

  public static void quickSort(int[] a, int low, int hight) {

    if (low > hight) {
      return;
    }
    int key = a[low]; //基准
    int povit = low;
    int start = low;
    int end = hight;
    // 16,2,9,3,8,28,19,17,34,45

    while (a[end] > key) {
      end--;
    } //找出第一个小于key的index  end = 4
    while (a[start] < key) { //如果选第一个为基准，则一次都进不去
      start++;
    } //找出第一个大于key的index  start= 5

    System.out.println("该轮结束start=" + start + " end=" + end);
    if (start <= end) { //如果找出来的这两个index在移位过程中未交叉重复，则换位
      int temp = a[start];
      a[start] = a[end];
      a[end] = temp;
      povit = end;
    }
    // 8,2,9,3,16,28,19,17,34,45
    // 8,2,3,9,16,28,19,17,34,45

    for (int i = 0; i < a.length; i++) {
      System.out.print(" " + a[i]);
    }
    System.out.println("");

    quickSort(a, low, povit - 1);
    quickSort(a, povit + 1, hight);
  }
}