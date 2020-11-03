package com.lj.springtransaction.service.userService;

import com.lj.springtransaction.pojo.request.ExamUserRequest;
import com.lj.springtransaction.pojo.response.UserVO;

import java.util.List;

/**
 * @Description: 用户服务
 * @ClassName: {@link UserService}
 * @Author: liang_jun
 * @Date: 2020/10/29 15:42
 */
public interface UserService {
    /**
     * 添加用户
     *
     * @param examUserRequest :用户对象
     * @return int 返回数据库操作影响行数
     * @author liang_jun
     * @date 2020/10/30 10:24
     */
    int saveUser(ExamUserRequest examUserRequest);

    /**
     * 查询用户信息
     *
     * @return {@link com.lj.springtransaction.common.Result<java.util.List<com.lj.springtransaction.pojo.response.UserVO>>} 返回用户信息
     * @author liang_jun
     * @date 2020/10/30 10:21
     */
    List<UserVO> ListGetUserAll();

    /**
     * 根据用户唯一编号查询用户详情
     *
     * @param userId :  用户唯一编号
     * @return {@link com.lj.springtransaction.common.Result<com.lj.springtransaction.pojo.response.UserVO>} 返回用户详情
     * @author liang_jun
     * @date 2020/10/30 16:59
     */
    UserVO findUserById(Long userId);

    /**
     * 通过用户名称查询用户
     *
     * @param name :  用户名称
     * @param userName
     * @return {@link com.lj.springtransaction.common.Result<com.lj.springtransaction.pojo.response.UserVO>} 返回用户信息
     * @author liang_jun
     * @date 2020/11/2 14:16
     */
    UserVO findUserByName(String name,String userName);

    /**
     * 模糊查询用户信息
     *
     * @param name : 用户名称
     * @return {@link com.lj.springtransaction.common.Result<com.lj.springtransaction.pojo.response.UserVO>} 返回用户信息
     * @author liang_jun
     * @date 2020/11/3 10:52
     */
    List<UserVO> indistinctFindUser(String name);
}
