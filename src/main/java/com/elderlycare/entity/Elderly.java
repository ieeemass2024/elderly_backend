package com.elderlycare.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("老人")
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("elderly")
public class Elderly {

    @ApiModelProperty("老人id")
    @TableId
    private Long elderlyId;

    @ApiModelProperty("老人姓名")
    private String elderlyName;

    @ApiModelProperty("性别")
    private String gender;

    @ApiModelProperty("电话")
    private String phone;

    @ApiModelProperty("身份证号")
    private String idCard;

    @ApiModelProperty("出生日期")
    private String birthday;

    @ApiModelProperty("入养老院日期")
    private String checkinDate;

    @ApiModelProperty("离开养老院日期")
    private String checkoutDate;

    @ApiModelProperty("图像目录")
    private String imgsetDir;

    @ApiModelProperty("头像路径")
    private String profilePhoto;

    @ApiModelProperty("房间号")
    private String roomNumber;

    @ApiModelProperty("第一监护人姓名")
    private String firstGuardianName;

    @ApiModelProperty("与第一监护人关系")
    private String firstGuardianRelationship;

    @ApiModelProperty("第一监护人电话")
    private String firstGuardianPhone;

    @ApiModelProperty("第一监护人微信")
    private String firstGuardianWechat;

    @ApiModelProperty("第二监护人姓名")
    private String secondGuardianName;

    @ApiModelProperty("与第二监护人关系")
    private String secondGuardianRelationship;

    @ApiModelProperty("第二监护人电话")
    private String secondGuardianPhone;

    @ApiModelProperty("第二监护人微信")
    private String secondGuardianWechat;

    @ApiModelProperty("健康状况")
    private String healthState;

}
