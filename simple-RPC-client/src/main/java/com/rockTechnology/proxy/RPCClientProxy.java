package com.rockTechnology.proxy;

import java.lang.reflect.Proxy;

// todo:泛型？？
// todo：一次调多个方法？
// 代理模式和装饰者模式的区别是，代理模式可以通用，装饰者是装饰一个接口
/**
 * 客户端的代理类
 * 客户端就可以不关心具体是什么接口，直接调用代理类的方法
 */
public class RPCClientProxy {

    public <T> T clientProxy(Class<T> interfaceCls, String host, int port){
        // 代理模式，代理RemoteInvocationHandler类，
        // 代理RemoteInvocationHandler类中的invoke是重写被代理的方法
        // invoke中调用传输层: RPCNetTransport
        return (T) Proxy.newProxyInstance(interfaceCls.getClassLoader(), new Class[]{interfaceCls}, new RemoteInvocationHandler(host, port));

    }
}
