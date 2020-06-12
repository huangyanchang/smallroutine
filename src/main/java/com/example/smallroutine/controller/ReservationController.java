package com.example.smallroutine.controller;

import com.example.smallroutine.common.api.CommonResult;
import com.example.smallroutine.dto.Reservation;
import com.example.smallroutine.service.ReservationService;
import com.example.smallroutine.service.UserService;
import com.example.smallroutine.service.impl.UserServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/reservation")
@Controller
public class ReservationController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserService userService;
    @Autowired
    private ReservationService reservationService;


    @ApiOperation("添加预约信息")
    @RequestMapping(value = "/add" , method = RequestMethod.POST)
    @ResponseBody
    public CommonResult commitReservation(@RequestBody Reservation  reservationData) {
        CommonResult commonResult;
        int count;
        count = reservationService.createReservation(reservationData);
        if(count == 1){
            commonResult = CommonResult.success("预约信息保存成功");
        } else {
            commonResult = CommonResult.failed();
        }
        return commonResult;
    }

    @ApiOperation("修改预约信息")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable int id, @RequestParam String name,@RequestParam String phone) {
        int count = reservationService.update(id, name, phone);
        if (count == 1) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("删除单个预约信息")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@PathVariable int id) {
        int count = reservationService.delete(id);
        if (count == 1) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed("没有找到预约记录");
        }
    }

    @ApiOperation("获取单个预约信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Reservation> getItem(@PathVariable int id) {
        Reservation reservation = reservationService.getItem(id);
        if(reservation !=null) {
            return CommonResult.success(reservation);
        }else {
            return CommonResult.failed("没有找到预约信息");
        }

    }
}