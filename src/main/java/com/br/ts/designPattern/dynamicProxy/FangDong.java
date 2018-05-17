package com.br.ts.designPattern.dynamicProxy;

/**
 * 房东
 * Created by bearray123 on 18/5/5.
 */
public class FangDong implements IRentHouse {

    public void rent() {
        System.out.printf("房东：我想将雄楚一号的房子租至少2900元/月！\n");
    }

    public void takeCash() {
        System.out.printf("房东：收取租金 \n");

    }
}
