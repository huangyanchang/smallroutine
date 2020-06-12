package com.example.smallroutine.service;

import com.example.smallroutine.dto.Sign;

public interface SignService {
    int create(Sign sign);

    int update(int id,Sign sign);

    int delete(int id);

    Sign getItem(int id);

}
