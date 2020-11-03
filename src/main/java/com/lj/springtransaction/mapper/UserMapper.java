package com.lj.springtransaction.mapper;

import com.lj.springtransaction.common.Result;
import com.lj.springtransaction.model.ExamUser;
import com.lj.springtransaction.pojo.response.UserVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * @ClassName: UserMapper
 * @Author: liang_jun
 * @Date: 2020/10/29 15:44
 */
public interface UserMapper {
    /**
     * 添加用户
     *
     * @param examUser :用户对象
     * @return int 返回数据库操作影响行数
     * @author liang_jun
     * @date 2020/10/30 10:24
     */
    int saveUser(@Param("examUser") ExamUser examUser);

    /**
     * 查询用户信息
     *
     * @return {@link com.lj.springtransaction.common.Result<java.util.List<com.lj.springtransaction.model.ExamUser>>} 返回用户信息
     * @author liang_jun
     * @date 2020/10/30 10:21
     */
    List<ExamUser> ListSelectUserAll();

    /**
     * 根据用户唯一编号查询用户详情
     *
     * @param userId :  用户唯一编号
     * @return {@link Result < UserVO>} 返回用户详情
     * @author liang_jun
     * @date 2020/10/30 16:59
     */
    ExamUser selectUserById(Long userId);

    /**
     * 根据用户名称查询用户信息
     *
     * @param name :  用户名称
     * @return com.lj.springtransaction.model.ExamUser
     * @author liang_jun
     * @date 2020/11/2 14:19
     */
    ExamUser selectUserByName(@Param("name") String name, @Param("userName") String userName);

    /**
     * 模糊查询用户信息
     *
     * @param name : 用户名称
     * @return {@link com.lj.springtransaction.common.Result<com.lj.springtransaction.model.ExamUser>} 返回用户信息
     * @author liang_jun
     * @date 2020/11/3 10:52
     */
    List<ExamUser> indistinctSelectUser(@Param("name") String name);
}
