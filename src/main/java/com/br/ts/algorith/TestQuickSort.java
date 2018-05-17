package com.br.ts.algorith;

import java.util.Random;

/**
 * Created by bearray123 on 17/12/24.
 *
 * 快速排序
 */
public class TestQuickSort {

  public static void main(String[] args) {

    //int a[] = {16,56,9,3,8,28,19,17,34,45};
    // int a[] = {26,  35,  8,  39 , 88,  20,  26, 77 , 69,  56};

    //new Random(), 如果不传seed，默认以系统时间做关联生成种子，这样每次生成的随机数都不一样
    Random random = new Random();

    //new Random(seed) ，如果传了seed（种子），则每次生成的随机数都是固定的
    //Random random = new Random(900);

    System.out.println("生成随机数");
    int a[] = new int[10];
    for (int i = 0; i < 10; i++) {
      a[i] = random.nextInt(101);
      System.out.print("  " + a[i]);
    }

    System.out.println("\n开始排序");
    quickSort4(a, 0, a.length - 1);
    for (int i = 0; i < a.length; i++) {
      System.out.print("  " + a[i]);
    }
  }

  /**
   * 以最后一个数为基准数
   */
  public static void quickSort(int[] arr, int start, int end) {
    if (start >= end) {
      return;
    }
    int mid = arr[end];
    int left = start;
    int right = end;
    // 16,56,9,3,8,28,19,17,34, 45
    while (left < right) {

      while (arr[left] <= mid && left < right) { //从左边开始找出第一个大于基准数的 left:56
        left++;
      }

      while (arr[right] >= mid && left < right) { //从右边开始找出第一个小于基准数的 right:34
        right--;
      }

      swap(arr, left, right); //交换两者   16,34,9,3,8,28,19,17,56,45
    }
    //因为选的最右边的数作为基准，所以一轮结束之后左边
    if (arr[left] >= arr[end]) { //轮询完后左边跟基准对比，如果大于基准，则互换
      swap(arr, left, end);
    } else {
      left++;
    }
    //left 存的就是基准只的index
    quickSort(arr, start, left - 1);
    quickSort(arr, left + 1, end);
  }

  private static void swap(int[] arr, int x, int y) {
    int temp = arr[x];
    arr[x] = arr[y];
    arr[y] = temp;
  }

  /**
   * 以第一个数为基准数
   */
  public static void quickSort2(int[] a, int start, int end) {

    if (start >= end) {
      return;
    }
    int pivot = a[start];
    int left = start;
    int right = end;

    while (left < right) {
      while (a[right] >= pivot && left < right) {
        right--;
      } //从右边开始，找出第一个小于基准的
      while (a[left] <= pivot && left < right) {
        left++;
      } //从左边开始，找出第一个大于基准的
      swap(a, left, right);
    }
    if (a[right] <= a[start]) {
      swap(a, right, start);
    } else {
      right--;
    }
    quickSort2(a, start, right - 1);
    quickSort2(a, right + 1, end);
  }

  /**
   * 以第一个数为基准
   * 这种简洁点，但难理解
   */
  public static void quickSort3(int[] arr, int left, int right) {
    if (left < right) {
      int key = arr[left]; //基准数存放到key中
      int low = left;
      int high = right;
      //16,56,9,3,8,28,19,17,34,45
      while (low < high) {
        while (low < high && arr[high] > key) {
          high--;
        } //找出第一个比基准小的 8
        arr[low] = arr[high]; //a[0] = 8

        while (low < high && arr[low] < key) {
          low++;
        } //找出第一个比基准大的 56
        arr[high] = arr[low]; //a[4] = 56
      }
      //上述步骤走完后，其中arr[low]中放的数是重复的，最后需要将key放回arr[low]中，作为基准
      arr[low] = key;
      quickSort3(arr, left, low - 1);
      quickSort3(arr, low + 1, right);
    }
  }

  public static void quickSort4(int[] a, int start, int end) {

    if (start >= end) {
      return;
    }

    int pivot = a[start];

    int left = start;
    int right = end;
    while (left < right) {
      while (a[right] >= a[left] && right > left) {
        right--;
      }
      while (a[left] <= a[right] && right > left) {
        left++;
      }
      swap(a, left, right);
      if (a[right] <= pivot) {
        swap(a, right, start);
      } else {
        right--;
      }
    }
    quickSort4(a, start, right - 1);
    quickSort4(a, right + 1, end);
  }
}
