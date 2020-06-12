package com.example.smallroutine.controller;
import com.example.smallroutine.common.api.CommonResult;
import com.example.smallroutine.dto.Sign;
import com.example.smallroutine.service.SignService;
import com.example.smallroutine.service.impl.UserServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/sign")
@Controller
public class SignController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private SignService signService;

    @ApiOperation("添加报名信息")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult addSign(@RequestBody Sign sign){
        int count = signService.create(sign);
        if(count == 1 ){
            return CommonResult.success(count);
        }
        return CommonResult.failed("添加失败");
    }
    //可以参考mall的修改场次
    @ApiOperation("修改报名信息")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable("id") int id ,@RequestBody Sign sign){
        int count = signService.update(id,sign);
        if(count == 1){
            return CommonResult.success(count);
        }
        return CommonResult.failed("修改失败");
    }

    @ApiOperation("删除报名信息")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@PathVariable int id){
        int count = signService.delete(id);
        if(count ==1){
            return CommonResult.success(count);
        }
        return CommonResult.failed("删除失败");
    }

    @ApiOperation("查询报名信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Sign> getItem(@PathVariable int id){
        Sign sign = signService.getItem(id);
        if(sign !=null){
            return CommonResult.success(sign);
        }
        return CommonResult.failed("查询失败");
    }
}
