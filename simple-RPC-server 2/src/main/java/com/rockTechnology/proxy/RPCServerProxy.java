package com.rockTechnology.proxy;

import com.rockTechnology.proxy.handler.ProcessorHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 代理类：用于封装server端的方法
 */
public class RPCServerProxy {

    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 100, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2));

    public void publish(Object service, int port) {
        ServerSocket serverSocket = null;

        try {
            // 建立服务器端的套接字对象，用于接受和发送请求
            // 端口号为服务端进程的端口号，0为自动分配
            serverSocket = new ServerSocket(port);
            Socket clientSocket = serverSocket.accept();
            threadPoolExecutor.execute(new ProcessorHandler(clientSocket, service));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
