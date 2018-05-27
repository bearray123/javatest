package com.br.ts.thread;

import javafx.scene.media.SubtitleTrack;

/**
 * Created by bearray123 on 18/5/23.
 */
public class TestVolatile {

    public volatile AsrType type = AsrType.IDEL;

    public int count = 0;


    enum AsrType {

        IDEL, //0

        START, //1

        STOP, //2

        CANCLE //3

    }

    private static class SubThread extends Thread {

        private TestVolatile demoInstance;
        private String threadName;

        public SubThread(String name, TestVolatile instance) {
            super(name);
            threadName = name;
            demoInstance = instance;
        }

        @Override public void run() {

            AsrType modifyTo;

            int temp;

            if ("thread-1".equals(threadName)) {
                modifyTo = AsrType.START;
                temp = 1;
            } else if ("thread-2".equals(threadName)) {
                modifyTo = AsrType.STOP;
                temp = 2;
            } else if ("thread-3".equals(threadName)) {
                modifyTo = AsrType.CANCLE;
                temp = 3;
            } else {
                modifyTo = AsrType.IDEL;
                temp = 0;
            }

            //demoInstance.modifyType(modifyTo);
            demoInstance.modifyCount(temp);

            System.out.printf(threadName + " 线程修改后，状态变成了 ： " + demoInstance.count + "\n");

            while (true) {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }

                System.out.printf(threadName + " 线程中打印type: " + demoInstance.count + "\n");
            }

        }
    }

    public void modifyType(AsrType type) {
        this.type = type;

    }

    public void modifyCount(int count) {
        this.count = count;

    }

    public static void main(String [] strings) {

        final TestVolatile testInstance = new TestVolatile();

        new SubThread("thread-1", testInstance).start();

        new SubThread("thread-2", testInstance).start();

        new SubThread("thread-3", testInstance).start();


    }







}
