package com.rockTechnology.proxy;

import com.rockTechnology.request.RPCRequest;
import com.rockTechnology.transport.RPCNetTransport;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 客户端调用RPCNetTransport
 */
public class RemoteInvocationHandler implements InvocationHandler {
    String host;
    int port;

    public RemoteInvocationHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] parameters) throws Throwable {
        RPCRequest rpcRequest = new RPCRequest();
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setParameters(parameters);
        RPCNetTransport rpcNetTransport = new RPCNetTransport(host, port);
        Object result = rpcNetTransport.sentRequest(rpcRequest);
        return result;
    }
}
