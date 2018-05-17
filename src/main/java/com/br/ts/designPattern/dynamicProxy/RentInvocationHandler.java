package com.br.ts.designPattern.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 *
 * 动态代理
 * Created by bearray123 on 18/5/5.
 */
public class RentInvocationHandler implements InvocationHandler {

  IRentHouse realObj;

  public RentInvocationHandler(IRentHouse obj) {
    realObj = obj;
  }


    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
      if ("rent".equals(method.getName())){

         System.out.printf("代理经纪人：您雄楚一号的房子需要出租么？ 链家地产为您服务 \n");

      } else if ("takeCash".equals(method.getName())) {

         System.out.printf("代理经纪人：年轻人，需要租房不，拎包入住，便宜！\n");

      }
      Object obj = method.invoke(realObj);
      return obj;
    }

    public <T> T getProxy(){
      return (T) Proxy.newProxyInstance(realObj.getClass().getClassLoader(),
            realObj.getClass().getInterfaces(), this);
    }

}
