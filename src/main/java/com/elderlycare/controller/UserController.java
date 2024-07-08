package com.elderlycare.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.elderlycare.common.ResponseResult;
import com.elderlycare.entity.LoginUser;
import com.elderlycare.entity.User;
import com.elderlycare.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api(tags = "用户接口")
@Slf4j
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @description: 根据id查询用户
     * @param: []
     * @return: com.elderlycare.common.ResponseResult<com.elderlycare.entity.User>
     **/
    @GetMapping("/info")
    @ApiOperation(value = "根据id查询用户")
    @ApiImplicitParams({
    })
    public ResponseResult<User> getUserById() {

        // 获取SecurityContextHolder中的用户id
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUser().getUserId();

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(User::getUserId, userId)
                .select(User::getUserId, User::getUsername, User::getEmail, User::getPhone, User::getGender, User::getNickname, User::getRealname);
        User user = userService.getOne(queryWrapper);
        if (user != null) {
            return ResponseResult.success(user, "用户信息查询成功！");
        }
        return ResponseResult.error("用户信息查询失败！");

    }

//    @GetMapping
//    @ApiOperation(value = "查询所有用户接口")
//    @ApiImplicitParams({
//    })
//    public ResponseResult<List<User>> getAllUsers() {
//        return ResponseResult.success(userService.list(null));
//    }

    /**
     * @description: 用户登录
     * @param: [user]
     * @return: com.elderlycare.common.ResponseResult<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    @PostMapping("/login")
    @ApiOperation(value = "用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "用户登录信息", required = true)
    })
    public ResponseResult<Map<String, Object>> login(@RequestBody User user) {
        // 登录
        return userService.login(user);
    }

    /**
     * @description: 用户退出登录
     * @param: []
     * @return: com.elderlycare.common.ResponseResult<java.lang.String>
     **/
    @PostMapping("/logout")
    @ApiOperation(value = "用户退出登录")
    @ApiImplicitParams({
    })
    public ResponseResult<String> logout() {
        return userService.logout();
    }

    /**
     * @description: 用户注册
     * @param: [user]
     * @return: com.elderlycare.common.ResponseResult<com.elderlycare.entity.User>
     **/
    @PostMapping("/register")
    @ApiOperation(value = "用户注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "用户注册信息", required = true)
    })
    public ResponseResult<User> register(@RequestBody User user) {
        userService.register(user);
        return ResponseResult.success(null, "注册成功！");
    }


    /**
     * @description: 修改密码
     * @param: [userMap]
     * @return: com.elderlycare.common.ResponseResult<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    @PutMapping("/pwd")
    @ApiOperation(value = "修改密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userMap", value = "需要修改密码的用户信息", required = true)
    })
    public ResponseResult<Map<String, Object>> modifyPassword(@RequestBody Map<String, Object> userMap) {
        if (userService.modifyPassword(userMap) > 0) {
            return ResponseResult.success(null, "密码修改成功！");
        }
        return ResponseResult.error("修改失败！");
    }

    /**
     * @description: 修改用户信息
     * @param: [userMap]
     * @return: com.elderlycare.common.ResponseResult<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    @PutMapping("/info")
    @ApiOperation(value = "修改用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userMap", value = "需要修改密码的用户信息", required = true)
    })
    public ResponseResult<Map<String, Object>> modifyInfo(@RequestBody Map<String, Object> userMap) {
        if (userService.modifyInfo(userMap) > 0) {
            return ResponseResult.success(null, "修改信息成功！");
        }
        return ResponseResult.error("修改失败！");
    }

}
