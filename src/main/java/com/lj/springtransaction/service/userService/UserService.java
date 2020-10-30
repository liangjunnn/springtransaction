package com.lj.springtransaction.service.userService;

import com.lj.springtransaction.pojo.request.ExamUserRequest;
import com.lj.springtransaction.pojo.response.UserVO;

import java.util.List;

/**
 * @Description:
 * @ClassName: UserService
 * @Author: liang_jun
 * @Date: 2020/10/29 15:42
 */
public interface UserService {
    /**
     * 添加用户
     *
     * @param examUserRequest :  用户对象
     * @return int 返回数据库操作影响行数
     * @author liang_jun
     * @date 2020/10/29 15:45
     */
    int saveUser(ExamUserRequest examUserRequest);

    /**
     * 查询用户信息
     *
     * @return com.lj.springtransaction.common.Result<java.util.List < com.lj.springtransaction.pojo.response.UserVO>>
     * @author liang_jun
     * @date 2020/10/30 9:34
     */
    List<UserVO> ListGetUserAll();
}
