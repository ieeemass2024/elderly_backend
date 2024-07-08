package com.elderlycare.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("用户-权限关系")
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("user_role")
public class UserRole {

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("用户名")
    private Long roleId;

}
