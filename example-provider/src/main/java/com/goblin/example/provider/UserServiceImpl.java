package com.goblin.example.provider;

import com.goblin.example.common.model.User;
import com.goblin.example.common.service.UserService;

public class UserServiceImpl implements UserService {

    @Override
    public User getUser(User user) {
        System.out.println("用户名为：" + user.getName());
        return user;
    }
}
