package com.example.smallroutine.service.impl;

import com.example.smallroutine.dto.Sign;
import com.example.smallroutine.mapper.SignMapper;
import com.example.smallroutine.service.SignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignServiceImpl implements SignService {

    @Autowired
    private SignMapper signMapper;

    @Override
    public int create(Sign sign) {
        int count = 0;
        Sign findSign  = signMapper.selectByUserId(sign.getUserId());
        if(findSign == null){
            signMapper.insertSign(sign);
            count = 1;
        }
        return count;
    }

    @Override
    public int update(int id,Sign sign) {
        sign.setId(id);
        return signMapper.updateByPrimaryKeySelective(sign);
    }

    @Override
    public int delete(int id) {
        return signMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Sign getItem(int id) {
        return signMapper.selectByPrimaryKey(id);
    }
}
