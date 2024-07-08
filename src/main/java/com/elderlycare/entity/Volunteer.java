package com.elderlycare.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 义工
 */
@ApiModel("义工")
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("volunteer")
public class Volunteer {

    @ApiModelProperty("义工id")
    @TableId(type = IdType.AUTO)
    private Long volunteerId;

    @ApiModelProperty("义工姓名")
    private String volunteerName;

    @ApiModelProperty("性别")
    private String gender;

    @ApiModelProperty("电话")
    private String phone;

    @ApiModelProperty("身份证号")
    private String idCard;

    @ApiModelProperty("出生日期")
    private String birthday;

    @ApiModelProperty("访问日期")
    private String checkinDate;

    @ApiModelProperty("离开日期")
    private String checkoutDate;

    @ApiModelProperty("图像目录")
    private String imgsetDir;

    @ApiModelProperty("头像路径")
    private String profilePhoto;

}
