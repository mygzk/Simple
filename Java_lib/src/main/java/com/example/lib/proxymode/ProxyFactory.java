package com.example.lib.proxymode;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by guozhk on 2018/4/17.
 */

public class ProxyFactory {
    private Object target;
    public ProxyFactory(Object object){
        this.target = object;
    }


    public Object getProxyInstance(){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                        System.out.println("开始事务2");
                        //执行目标对象方法
                        Object returnValue = method.invoke(target, objects);
                        System.out.println("提交事务2");
                        return returnValue;
                    }
                });
    }






}
