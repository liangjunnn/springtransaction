package com.lj.springtransaction.controller.userController;

import com.alibaba.fastjson.JSON;
import com.lj.springtransaction.common.Result;
import com.lj.springtransaction.common.enums.ErrorMsgEnum;
import com.lj.springtransaction.pojo.request.ExamUserRequest;
import com.lj.springtransaction.pojo.response.UserVO;
import com.lj.springtransaction.service.userService.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.omg.PortableInterceptor.LOCATION_FORWARD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @Description: 用户控制器
 * @ClassName: {@link UserController}
 * @Author: liang_jun
 * @Date: 2020/10/29 15:34
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    /**
     * 用户服务
     */
    private UserService userService;

    /**
     * 构造器注入
     *
     * @param userService 用户服务
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 添加用户
     *
     * @param examUserRequest :  用户对象
     * @return {@link com.lj.springtransaction.common.Result<java.lang.Object>}
     * @author liang_jun
     * @date 2020/10/29 15:39
     */
    @PostMapping("/saveUser")
    public Result<Object> saveUser(@RequestBody ExamUserRequest examUserRequest) {
        log.info("添加用户 examUserRequest:{}", JSON.toJSONString(examUserRequest));
        if (null == examUserRequest) {
            return new Result<Object>(ErrorMsgEnum.REQUEST_PARAMETER_EXCEPTION.getCode(), ErrorMsgEnum.REQUEST_PARAMETER_EXCEPTION.getCode());
        }
        userService.saveUser(examUserRequest);
        return new Result<Object>();
    }

    /**
     * 查询用户信息
     *
     * @return {@link com.lj.springtransaction.common.Result<java.util.List<com.lj.springtransaction.pojo.response.UserVO>>} 返回用户信息
     * @author liang_jun
     * @date 2020/10/30 10:21
     */
    @GetMapping("/queryUserAll")
    public Result<List<UserVO>> queryUserAll() {
        log.info("查询用户信息");
        List<UserVO> userVOS = userService.ListGetUserAll();
        log.info("查询用户信息返回userVOS:{}", JSON.toJSONString(userVOS));
        return new Result<List<UserVO>>(userVOS);
    }

    /**
     * 根据用户唯一编号查询用户详情
     *
     * @param userId :  用户唯一编号
     * @return {@link com.lj.springtransaction.common.Result<com.lj.springtransaction.pojo.response.UserVO>} 返回用户详情
     * @author liang_jun
     * @date 2020/10/30 16:59
     */
    @GetMapping("/queryUserById")
    public Result<UserVO> queryUserById(Long userId) {
        log.info("根据用户唯一编号查询用户详情 userId:{}", userId);
        if (null == userId) {
            return new Result<UserVO>(ErrorMsgEnum.REQUEST_PARAMETER_EXCEPTION.getCode()
                    , ErrorMsgEnum.REQUEST_PARAMETER_EXCEPTION.getMsg());
        }
        UserVO userInfo = userService.findUserById(userId);
        log.info("根据用户唯一编号查询用户详情返回userInfo:{}", JSON.toJSONString(userInfo));
        return new Result<UserVO>(userInfo);
    }

    /**
     * 通过用户名称查询用户
     *
     * @param name :  用户名称
     * @return {@link com.lj.springtransaction.common.Result<com.lj.springtransaction.pojo.response.UserVO>} 返回用户信息
     * @author liang_jun
     * @date 2020/11/2 14:16
     */
    @GetMapping("/queryUserByName")
    public Result<UserVO> queryUserByName(String name, String userName) {
        log.info("通过用户名称查询用户 name:{},userName:{}", name, userName);
        if (StringUtils.isBlank(name) || StringUtils.isBlank(userName)) {
            return new Result<UserVO>(ErrorMsgEnum.REQUEST_PARAMETER_EXCEPTION.getCode(), ErrorMsgEnum.REQUEST_PARAMETER_EXCEPTION.getMsg());
        }
        UserVO userInfo = userService.findUserByName(name, userName);
        log.info("通过用户名称查询用户返回 userInfo：{}", JSON.toJSONString(userInfo));
        return new Result<UserVO>(userInfo);
    }

    /**
     * 模糊查询用户信息
     *
     * @param name : 用户名称
     * @return {@link com.lj.springtransaction.common.Result<com.lj.springtransaction.pojo.response.UserVO>} 返回用户信息
     * @author liang_jun
     * @date 2020/11/3 10:52
     */
    @GetMapping("/indistinctQueryUser")
    public Result<List<UserVO>> indistinctQueryUser(String name) {
        log.info("模糊查询用户信息 name:{}", name);
        if (StringUtils.isBlank(name)) {
            return new Result<List<UserVO>>(ErrorMsgEnum.REQUEST_PARAMETER_EXCEPTION.getCode(), ErrorMsgEnum.REQUEST_PARAMETER_EXCEPTION.getMsg());
        }
        List<UserVO> userVOS = userService.indistinctFindUser(name);
        log.info("模糊查询用户信息userVOS返回:{}", JSON.toJSONString(userVOS));
        return new Result<List<UserVO>>(userVOS);
    }

}
