package com.elderlycare.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.elderlycare.entity.Event;
import com.elderlycare.entity.EventStatistic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EventMapper extends BaseMapper<Event> {

    /**
     * @description: 根据事件id查询事件信息第一步
     * @param: [eventId]
     * @return: com.elderlycare.entity.Event
     **/
    Event selectEventByIdByStep1(@Param("eventId") Long eventId);

    /**
     * @description: 统计某月份发生的事件数
     * @param: [year, month]
     * @return: int
     **/
    int countEventMonth(@Param("year") Integer year, @Param("month") Integer month);

    /**
     * @description: 统计各个地点发生的事件数
     * @param: [year]
     * @return: java.util.List<com.elderlycare.entity.EventStatistic>
     **/
    List<EventStatistic> countEventLocation(@Param("year") Integer year);
    List<EventStatistic> countEventType(@Param("year") Integer year);
}
