package com.example.smallroutine.dto;







import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class User {
    private String id ;
    private String nickName ;
    private String location ;
    private String openId ;
    private Date createTime;
    private Date lastLoginTime;
}
