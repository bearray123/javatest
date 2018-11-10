package com.br.ts.autotest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by bearray123 on 18/11/7.
 */
public class AutoCalcTime {


    public static void main(String [] args) {


        try {

            //文件最后必须要带两个空行
            FileReader fr = new FileReader("/Users/bearray123/Desktop/createAudioTrackTime.log");
            //FileReader fr = new FileReader("/Users/bearray123/Desktop/speaktime.log");
            BufferedReader bufferedReader = new BufferedReader(fr);

            int c = 0;
            int k = 0;
            String lineStr = "";
            float totalTime = 0;
            long startTime = 0;
            long endTime = 0;
            while (!"".equals(lineStr = bufferedReader.readLine())) {
                k++;
                long time = new Long(lineStr.substring(lineStr.indexOf("=")+1,lineStr.length()));
                if (k==1) {
                    startTime = time;
                } else if(k==2) {
                    endTime = time;
                    totalTime += (endTime - startTime);
                    k = 0;
                    c++;
                } else {
                    throw new RuntimeException("k 超过2了");
                }
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("totalTime= " + totalTime);
            System.out.println("c= " + c);
            System.out.println("avgTime= " + totalTime/c);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("程序结束");




    }

}
