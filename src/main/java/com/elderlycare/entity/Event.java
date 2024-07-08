package com.elderlycare.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("事件")
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("event")
public class Event {

    @ApiModelProperty("事件id")
    @TableId(type = IdType.AUTO)
    private Long eventId;

    @ApiModelProperty("事件类型：0代表情感检测，1代表义工交互检测，2代表陌生人检测，3代表摔倒检测，4代表禁止区域入侵检测")
    private String eventType;

    @ApiModelProperty("事件发生的时间")
    private String eventDate;

    @ApiModelProperty("事件发生的地点")
    private String eventLocation;

    @ApiModelProperty("事件描述")
    @TableField("event_desc")
    private String eventDescription;

    @ApiModelProperty("事件抓拍照片")
    private String eventImg;

}
