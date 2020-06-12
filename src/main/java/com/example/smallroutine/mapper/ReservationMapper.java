package com.example.smallroutine.mapper;

import com.example.smallroutine.dto.Reservation;

public interface ReservationMapper {

    int insertReservation(Reservation record);

    int updateByPrimaryKeySelective(Reservation record);

    int deleteByPrimaryKey(int id);

    Reservation selectByPrimaryKey(int id);

    Reservation selectByUserId(String userId);
}
