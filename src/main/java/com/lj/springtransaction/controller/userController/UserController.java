package com.lj.springtransaction.controller.userController;

import com.lj.springtransaction.common.Result;
import com.lj.springtransaction.common.enums.ErrorMsgEnum;
import com.lj.springtransaction.pojo.request.ExamUserRequest;
import com.lj.springtransaction.pojo.response.UserVO;
import com.lj.springtransaction.service.userService.UserService;
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
        if (null == examUserRequest) {
            return new Result<Object>(ErrorMsgEnum.PARAMETER_EXCEPTION.getCode(), ErrorMsgEnum.PARAMETER_EXCEPTION.getCode());
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
        List<UserVO> userVOS = userService.ListGetUserAll();
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
        Optional<Long> id = Optional.ofNullable(userId);
        if (!id.isPresent()) {
            return new Result<UserVO>(ErrorMsgEnum.PARAMETER_EXCEPTION.getCode()
                    , ErrorMsgEnum.PARAMETER_EXCEPTION.getMsg());
        }
        UserVO userInfo = userService.findUserById(userId);
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
    public Result<UserVO> queryUserByName(String name) {
        boolean present = Optional.ofNullable(name).isPresent();
        if (!present) {
            return new Result<UserVO>(ErrorMsgEnum.PARAMETER_EXCEPTION.getCode(), ErrorMsgEnum.PARAMETER_EXCEPTION.getMsg());
        }

        UserVO userInfo = userService.findUserByName(name);
        return new Result<UserVO>(userInfo);
    }

}
