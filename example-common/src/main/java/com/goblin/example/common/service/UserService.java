package com.goblin.example.common.service;

import com.goblin.example.common.model.User;

public interface UserService {
    /**
     *获取用户接口
     * @return
     */
    User getUser(User user);

    default short getNumber(){
        return 1;
    }
}
