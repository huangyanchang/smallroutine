
package com.example.smallroutine.service.impl;

import com.example.smallroutine.mapper.UserMapper;
import com.example.smallroutine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public int recommendUserLoginRecord(String userId) {
        return userMapper.getUserLoginRecord(userId);
    }
}

