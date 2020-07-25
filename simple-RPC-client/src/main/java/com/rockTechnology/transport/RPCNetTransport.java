package com.rockTechnology.transport;

import com.rockTechnology.request.RPCRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * 客户端负责发送、接收请求的类
 */
public class RPCNetTransport {
    String post;
    int port;

    public RPCNetTransport(String post, int port) {
        this.post = post;
        this.port = port;
    }

    /**
     * 用于创建一个socket
     *
     * @return  返回socket对象
     */
    private Socket newSocket(){
        // 建立连接
        System.out.println("建立连接");
        Socket socket = null;
        try {
            socket = new Socket(post, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return socket;
    }

    public Object sentRequest(RPCRequest rpcRequest){
        Socket socket = newSocket();
        ObjectOutputStream outputStream = null;
        ObjectInputStream inputStream = null;
        Object result = null;

        try {
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(rpcRequest);
            outputStream.flush();

            inputStream = new ObjectInputStream(socket.getInputStream());
            result = inputStream.readObject();
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
        return result;
    }

}
