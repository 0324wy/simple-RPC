package com.rockTechnology.request;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 用于定义客户端调用的请求
 * 哪个类，哪个方法，哪个参数
 */
public class RPCRequest implements Serializable {
    private static final long serialVersionUID = 4278659527083127315L;
    String className;
    String methodName;

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }

    Object[] parameters;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    @Override
    public String toString() {
        return "RPCRequest{" +
                "className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", parameters=" + Arrays.toString(parameters) +
                '}';
    }
}
