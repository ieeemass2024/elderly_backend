package com.elderlycare.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.elderlycare.common.ResponseResult;
import com.elderlycare.entity.User;

import java.util.Map;

public interface UserService extends IService<User> {

    /**
     * @description: 用户登录
     * @param: [user]
     * @return: com.elderlycare.common.ResponseResult<java.util.Map < java.lang.String, java.lang.Object>>
     **/
    ResponseResult<Map<String, Object>> login(User user);

    /**
     * @description: 用户退出登录
     * @param: []
     * @return: com.elderlycare.common.ResponseResult<java.lang.String>
     **/
    ResponseResult<String> logout();

    /**
     * @description: 用户注册
     * @param: [user]
     * @return: com.elderlycare.entity.User
     **/
    User register(User user);

    /**
     * @description: 修改密码
     * @param: [userMap]
     * @return: int
     **/
    int modifyPassword(Map<String, Object> userMap);

    /**
     * @description: 修改用户信息
     * @param: [userMap]
     * @return: int
     **/
    int modifyInfo(Map<String, Object> userMap);
}
