package com.example.smallroutine.service;

import com.example.smallroutine.dto.Reservation;

public interface ReservationService {
      int createReservation(Reservation reservationData);

      int update(int id, String name, String phone);

      int delete(int id);

      Reservation getItem(int id);
}
