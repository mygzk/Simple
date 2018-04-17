package com.example.lib.proxymode;

/**
 * Created by guozhk on 2018/4/17.
 */

public class ProxyTest {

    public static void main(String[] agrs) {
        ProxyTest test = new ProxyTest();
        test.testProxy1();
        test.testProxy2();

    }


    /**
     * 静态代理
     */
    private void testProxy1() {
        //目标对象
        UserDao target = new UserDao();

        //代理对象
        UserDaoProxy proxy = new UserDaoProxy(target);

        //执行的是代理的方法
        proxy.save();
    }



    private void testProxy2(){
        //目标对象
        IUserDao target = new UserDao();

        // 给目标对象，创建代理对象
        IUserDao proxy = (IUserDao) new ProxyFactory(target).getProxyInstance();

        // 执行方法   【代理对象】
        proxy.save();
    }

}
