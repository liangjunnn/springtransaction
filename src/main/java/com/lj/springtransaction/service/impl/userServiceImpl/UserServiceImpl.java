package com.lj.springtransaction.service.impl.userServiceImpl;

import com.lj.springtransaction.common.Result;
import com.lj.springtransaction.common.annotation.MyTransaction;
import com.lj.springtransaction.mapper.UserMapper;
import com.lj.springtransaction.model.ExamUser;
import com.lj.springtransaction.pojo.request.ExamUserRequest;
import com.lj.springtransaction.pojo.response.UserVO;
import com.lj.springtransaction.service.userService.UserService;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @ClassName: UserServiceImpl
 * @Author: liang_jun
 * @Date: 2020/10/29 15:43
 */
//@MyTransaction
@Service
public class UserServiceImpl implements UserService {
    private UserMapper userMapper;

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
//        int y = 1 / 0;
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

}
