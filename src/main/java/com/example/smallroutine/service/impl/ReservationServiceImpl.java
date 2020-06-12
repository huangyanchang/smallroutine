package com.example.smallroutine.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.smallroutine.dto.Reservation;
import com.example.smallroutine.mapper.ReservationMapper;
import com.example.smallroutine.mapper.UserMapper;
import com.example.smallroutine.service.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ReservationServiceImpl implements ReservationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);

    @Autowired
    private ReservationMapper reservationMapper;

    @Override
    public int createReservation(Reservation reservation) {
        Reservation findReservation = reservationMapper.selectByUserId(reservation.getUserId());
        int count = 0;
        if(findReservation == null){
            reservation.setReservationTime(new Date());
             reservationMapper.insertReservation(reservation);
             count = 1;
        }
       return count;
    }

    @Override
    public int update(int id, String name, String phone) {
        Reservation rv = new Reservation();
        rv.setName(name);
        rv.setPhone(phone);
        rv.setId(id);
        rv.setReservationTime(new Date());
        return reservationMapper.updateByPrimaryKeySelective(rv);
    }

    @Override
    public int delete(int id) {
        return reservationMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Reservation getItem(int id) {
        return reservationMapper.selectByPrimaryKey(id);
    }
}
