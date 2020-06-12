package com.example.smallroutine.dto;



import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Reservation {
    private int id ;
    private Date reservationTime ;
    private String name ;
    private String phone ;
    private String userId ;
}
