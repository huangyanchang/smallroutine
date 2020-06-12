package com.example.smallroutine.controller;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.smallroutine.common.api.CommonResult;
import com.example.smallroutine.common.api.GlobalResult;
import com.example.smallroutine.common.api.WechatUtil;
import com.example.smallroutine.dto.User;
import com.example.smallroutine.mapper.UserMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author hyc
 * @desc
 *
 */
@Controller
public class UserController {

    @Autowired
    private UserMapper userMapper;

    /**
     * 微信用户登录详情
     */
    @ApiOperation("用户登录")
    @PostMapping("wx/login")
    @ResponseBody
    public CommonResult user_login(@RequestParam(value = "code", required = false) String code,
                                   @RequestParam(value = "rawData", required = false) String rawData,
                                   @RequestParam(value = "signature", required = false) String signature,
                                   @RequestParam(value = "encrypteData", required = false) String encrypteData,
                                   @RequestParam(value = "iv", required = false) String iv) {
        // 用户非敏感信息：rawData
        // 签名：signature
        JSONObject rawDataJson = JSON.parseObject(rawData);
        // 1.接收小程序发送的code
        // 2.开发者服务器 登录凭证校验接口 appi + appsecret + code
        JSONObject SessionKeyOpenId = WechatUtil.getSessionKeyOrOpenId(code);
        // 3.接收微信接口服务 获取返回的参数
        //String openid = SessionKeyOpenId.getString("openid");
        String openId = "";
        String sessionKey = SessionKeyOpenId.getString("session_key");
        // 4.校验签名 小程序发送的签名signature与服务器端生成的签名signature2 = sha1(rawData + sessionKey)
        /*String signature2 = DigestUtils.sha1Hex(rawData + sessionKey);
        if (!signature.equals(signature2)) {
            return GlobalResult.build(500, "签名校验失败", null);
        }*/
        // 5.根据返回的User实体类，判断用户是否是新用户，是的话，将用户信息存到数据库；不是的话，更新最新登录时间
        List<User> userList= this.userMapper.selectById(openId);
        // uuid生成唯一key，用于维护微信小程序用户与服务端的会话
        String userId = UUID.randomUUID().toString();
        // 用户信息入库
        String nickName = rawDataJson.getString("nickName");
        String location = rawDataJson.getString("city");
        //String avatarUrl = rawDataJson.getString("avatarUrl");
        //String gender = rawDataJson.getString("gender");
        //String country = rawDataJson.getString("country");
        //String province = rawDataJson.getString("province");
        if (userList.isEmpty()) {
            User user = new User();
            user.setOpenId(openId);
            user.setId(userId);
            user.setNickName(nickName);
            user.setLocation(location);
            user.setCreateTime(new Date());
            user.setLastLoginTime(new Date());
       /*
            user.setSessionKey(sessionKey);
            user.setProvince(province);
            user.setCountry(country);
            user.setAvatarUrl(avatarUrl);
            user.setGender(Integer.parseInt(gender));*/
            this.userMapper.insert(user);
        } else {
            // 已存在，更新用户登录时间
            //user.setLastVisitTime(new Date());
        for(int i = 0;i<userList.size();i++){
            User user =  userList.get(i);
            user.setNickName(nickName);
            user.setLocation(location);
            user.setLastLoginTime(new Date());
            this.userMapper.updateById(user);
            }
        }
        //encrypteData比rowData多了appid和openid
        //JSONObject userInfo = WechatUtil.getUserInfo(encrypteData, sessionKey, iv);
        //6. 把新的skey返回给小程序
        return CommonResult.success(userId);
    }
}
