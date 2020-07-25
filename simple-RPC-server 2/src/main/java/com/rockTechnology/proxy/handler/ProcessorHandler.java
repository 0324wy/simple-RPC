package com.rockTechnology.proxy.handler;

import com.rockTechnology.request.RPCRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * 真正用于处理客户端请求的类
 *
 */
public class ProcessorHandler implements Runnable{
    // 对方socket中存着IP和端口号
    Socket clientSocket;
    // 对方调用的是哪个接口
    Object service;

    public ProcessorHandler(Socket socket, Object service) {
        this.clientSocket = socket;
        this.service = service;
    }

    @Override
    public void run() {
        System.out.println("开始处理客户端请求");
        ObjectInputStream inputStream = null;
        ObjectOutputStream outputStream = null;
        try {
            // 通过socket拿到对方输入流
            inputStream = new ObjectInputStream(clientSocket.getInputStream());
            // 读取对方流中的信息，并转化成一个RpcRequest类
            // readObject是从对方流中读取，并反序列化为一个Object类型
            RPCRequest rpcRequest = (RPCRequest) inputStream.readObject();
            Object result = invokeMethod(rpcRequest);
            outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            outputStream.writeObject(result);
            System.out.println("返回给客户端结果：" + clientSocket.getPort());
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 获取了对方的流中的信息，调用实际的方法
     * @param rpcRequest
     * @return
     */
    private Object invokeMethod(RPCRequest rpcRequest){
        // 获得对方流中的参数，参数类型，方法
        Object[] parameters = rpcRequest.getParameters();
        Class<?>[] types = new Class[parameters.length];
        for (int i = 0; i < types.length; i++) {
            types[i] = parameters[i].getClass();
        }
        try {
            // 获得了对方流中的信息，通过反射调用实际的方法
            // todo:直接从流中获得对方调用的接口
            // todo:不使用反射
            Method method = service.getClass().getMethod(rpcRequest.getMethodName(), types);
            Object result = method.invoke(service, parameters);
            return result;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

}
