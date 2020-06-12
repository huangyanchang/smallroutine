package com.example.smallroutine.mapper;

import com.example.smallroutine.dto.Sign;

public interface SignMapper {
    int insertSign(Sign record);

    int updateByPrimaryKeySelective(Sign record);

    int deleteByPrimaryKey(int record);

    Sign selectByPrimaryKey(int id);

    Sign selectByUserId(String userId);

}
