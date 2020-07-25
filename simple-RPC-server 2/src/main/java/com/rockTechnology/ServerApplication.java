package com.rockTechnology;

import com.rockTechnology.proxy.RPCServerProxy;
import com.rockTechnology.service.IHelloService;
import com.rockTechnology.service.impl.HelloServiceImpl;

import java.lang.reflect.Method;

/**
 * 新建proxy对象，proxy对象中开启socket进行监听客户端调用
 *
 */
public class ServerApplication {
    public static void main(String[] args) {
        IHelloService helloService = new HelloServiceImpl();
        RPCServerProxy proxy = new RPCServerProxy();
        proxy.publish(helloService, 8000);

    }
}
