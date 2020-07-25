package com.rockTechnology;

import com.rockTechnology.entities.User;
import com.rockTechnology.proxy.RPCClientProxy;
import com.rockTechnology.service.IHelloService;

// todo:客户端重新调用，服务端需要重启？
// todo: saveUser为什么不行
public class ClientApplication {
    public static void main(String[] args) {
        RPCClientProxy proxy = new RPCClientProxy();
        // 调用代理对象中的方法，返回对象
        IHelloService iHelloService = proxy.clientProxy(IHelloService.class, "localhost", 8000);
        // 使用其中的方法
        System.out.println(iHelloService.sayHello("xiaomajia"));
        System.out.println(iHelloService.saveUser(new User("小马甲")));

    }
}
