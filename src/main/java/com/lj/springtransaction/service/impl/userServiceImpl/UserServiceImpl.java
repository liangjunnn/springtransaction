package com.lj.springtransaction.service.impl.userServiceImpl;

import com.lj.springtransaction.common.Result;
import com.lj.springtransaction.common.annotation.MyTransaction;
import com.lj.springtransaction.mapper.UserMapper;
import com.lj.springtransaction.model.ExamUser;
import com.lj.springtransaction.pojo.request.ExamUserRequest;
import com.lj.springtransaction.pojo.response.UserVO;
import com.lj.springtransaction.service.userService.UserService;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.apache.catalina.User;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Description: 用户逻辑实现
 * @ClassName: {@link UserServiceImpl} 用户逻辑实现
 * @Author: liang_jun
 * @Date: 2020/10/29 15:43
 */
//@MyTransaction
@Service
public class UserServiceImpl implements UserService {
    /**
     * 用户服务
     */
    private UserMapper userMapper;

    /**
     * 构造器注入
     *
     * @param userMapper 用户服务
     */
    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * 添加用户
     *
     * @param examUserRequest :用户对象
     * @return int 返回数据库操作影响行数
     * @author liang_jun
     * @date 2020/10/30 10:24
     */
    @Override
    @MyTransaction
    public int saveUser(ExamUserRequest examUserRequest) {
        ExamUser examUser = new ExamUser();
        examUserRequest.setIsDeleted(1);
        BeanUtils.copyProperties(examUserRequest, examUser);
        int i = userMapper.saveUser(examUser);
//        try {
//            int y = 1 / 0;
//        }catch (Exception e){
//            e.getMessage();
//            throw e;
//        }
        return i;
    }

    /**
     * 查询用户信息
     *
     * @return {@link com.lj.springtransaction.common.Result<java.util.List<com.lj.springtransaction.pojo.response.UserVO>>} 返回用户信息
     * @author liang_jun
     * @date 2020/10/30 10:21
     */
    @Override
    public List<UserVO> ListGetUserAll() {
        List<ExamUser> examUsers = userMapper.ListSelectUserAll();
        List<UserVO> userVOS = new ArrayList<UserVO>(examUsers.size());
        if (CollectionUtils.isNotEmpty(examUsers)) {
            examUsers.forEach(examUser -> {
                UserVO userVO = new UserVO();
                BeanUtils.copyProperties(examUser, userVO);
                userVOS.add(userVO);
            });
        }
        return userVOS;
    }

    /**
     * 根据用户唯一编号查询用户详情
     *
     * @param userId :  用户唯一编号
     * @return {@link Result< UserVO>} 返回用户详情
     * @author liang_jun
     * @date 2020/10/30 16:59
     */
    @Override
    public UserVO findUserById(Long userId) {
        ExamUser examUser = userMapper.selectUserById(userId);
        boolean present = Optional.ofNullable(examUser).isPresent();
        if (!present) {
            return null;
        }

        UserVO userVO = new UserVO();
        Optional.ofNullable(examUser).ifPresent(examUser1 -> {
            BeanUtils.copyProperties(examUser, userVO);
        });
        return userVO;
    }

    /**
     * 通过用户名称查询用户
     *
     * @param name :  用户名称
     * @return {@link com.lj.springtransaction.common.Result<com.lj.springtransaction.pojo.response.UserVO>} 返回用户信息
     * @author liang_jun
     * @date 2020/11/2 14:16
     */
    @Override
    public UserVO findUserByName(String name) {
        UserVO userVO = new UserVO();
        ExamUser examUser = userMapper.selectUserByName(name);
        if (null != examUser) {
            BeanUtils.copyProperties(examUser, userVO);
            return userVO;
        }
        return null;
    }

}
