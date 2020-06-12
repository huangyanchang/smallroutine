package com.example.smallroutine.mapper;


import com.example.smallroutine.dto.User;

import org.springframework.data.repository.query.Param;

import java.util.List;


public interface UserMapper{
     List<User> selectById(@Param("openid") String openId);

     int  getUserLoginRecord( String userId);

     int insert(@Param("user") User user);

     int updateById(@Param("user") User user);
}
