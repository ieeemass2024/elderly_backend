package com.elderlycare.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.elderlycare.common.ResponseResult;
import com.elderlycare.entity.Elderly;
import com.elderlycare.entity.Event;

import java.util.Map;

public interface EventService extends IService<Event> {

    /**
     * @description: 根据事件id查询事件信息
     * @param: [eventId]
     * @return: com.elderlycare.entity.Event
     **/
    Event getEventInfoById(Long eventId);

    /**
     * @description: 根据事件发生时间数据进行统计
     * @param: [year]
     * @return: com.elderlycare.common.ResponseResult<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    ResponseResult<Map<String, Object>> getEventTimeStatistics(Integer year);

    /**
     * @description: 根据事件发生地点数据进行统计
     * @param: []
     * @return: com.elderlycare.common.ResponseResult<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    ResponseResult<Map<String, Object>> getEventLocationStatistics(Integer year);

    ResponseResult<Map<String, Object>> getEventTypeStatistics(Integer year);
}
