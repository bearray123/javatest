package com.br.ts.designPattern.dynamicProxy;

import org.junit.Test;

/**
 * Created by bearray123 on 18/5/5.
 */
public class DPTest {

    @Test public void testDP() {
        IRentHouse realObj = new FangDong();
        RentInvocationHandler rp = new RentInvocationHandler(realObj);
        //获取动态代理类
        IRentHouse proxy = rp.getProxy();
        //代理类去执行操作
        proxy.rent();
        proxy.takeCash();
    }
}
