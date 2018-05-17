package com.br.ts.algorith;

public class TestStringAdd {

  public static void main(String[] args) {

    String str1 = "837366262728949050569583456";
    String str2 = "12743644";

    char[] c_1 = str1.toCharArray();
    char[] c_2 = str2.toCharArray();

    int len_s1 = str1.length();
    int len_s2 = str2.length();

    int[] result = new int[len_s1 + 1];

    int maxlen = c_1.length > c_2.length ? c_1.length : c_2.length;
    int minlen = c_1.length < c_2.length ? c_1.length : c_2.length;

    int flag = 0;
    for (int i = maxlen - 1; i >= (maxlen - minlen); i--) {
      flag = ((c_1[i] - '0') + (c_2[i] - '0')) % 10;
    }
  }
}