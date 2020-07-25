package com.rockTechnology.service;

import com.rockTechnology.entities.User;

public interface IHelloService {

    public String sayHello(String content);
    public String saveUser(User user);

}
