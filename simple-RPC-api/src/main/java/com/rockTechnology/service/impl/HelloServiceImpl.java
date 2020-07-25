package com.rockTechnology.service.impl;


import com.rockTechnology.entities.User;
import com.rockTechnology.service.IHelloService;

public class HelloServiceImpl implements IHelloService {

    public HelloServiceImpl() {
    }

    public String sayHello(String content) {
        return "Hello, " + content;
    }

    public String saveUser(User user) {
//        System.out.println("Save user: " + user.toString());
        return "success";
    }
}
