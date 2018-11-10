package com.br.ts.collection;

import com.br.ts.bean.Student;
import java.util.ArrayList;

/**
 * Created by bearray123 on 18/10/16.
 */
public class TestMain {


    private ArrayList<Student> mList = new ArrayList<Student>();



    public static void main(String[] args) {

        Student s1 = new Student("xionglei","10");

        TestMain demo = new TestMain();
        demo.mList.add(s1);

        System.out.println("demo.mList.get = " + demo.mList.get(0).name);

        //s1 = null;
        //s1.name = "huyu";
        s1 = new Student("xx", "123");

        //Student getS = demo.mList.get(0);
        System.out.println("demo.mList.get = " + demo.mList.get(0).name);


    }


}
