package com.elderlycare.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("事件统计实体")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EventStatistic {

//    @ApiModelProperty("事件发生的时间")
//    private String eventMonth;

    @ApiModelProperty("事件发生的地点")
    private String eventLocation;

    @ApiModelProperty("事件数量")
    private Integer eventCount;

    @ApiModelProperty("事件类型")
    private String eventType;

}
