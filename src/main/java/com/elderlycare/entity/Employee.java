package com.elderlycare.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("工作人员")
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("employee")
public class Employee {

    @ApiModelProperty("工作人员id")
    @TableId(type = IdType.AUTO)
    private Long employeeId;

    @ApiModelProperty("工作人员姓名")
    private String employeeName;

    @ApiModelProperty("性别")
    private String gender;

    @ApiModelProperty("电话")
    private String phone;

    @ApiModelProperty("身份证号")
    private String idCard;

    @ApiModelProperty("出生日期")
    private String birthday;

    @ApiModelProperty("入职日期")
    private String hireDate;

    @ApiModelProperty("离职日期")
    private String resignDate;

    @ApiModelProperty("图像目录")
    private String imgsetDir;

    @ApiModelProperty("头像路径")
    private String profilePhoto;

}
