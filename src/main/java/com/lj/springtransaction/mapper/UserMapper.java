package com.lj.springtransaction.mapper;

import com.lj.springtransaction.model.ExamUser;
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
}
