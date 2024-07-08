package com.elderlycare.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.elderlycare.common.ResponseResult;
import com.elderlycare.entity.Event;
import com.elderlycare.service.EventService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api(value = "事件相关接口", tags = "事件接口")
@Slf4j
@RestController
@RequestMapping("/api/v1/event")
public class EventController {

    @Autowired
    private EventService eventService;

    /**
     * @description: 事件详情接口
     * @param: [eventId]
     * @return: com.elderlycare.common.ResponseResult<com.elderlycare.entity.Event>
     **/
    @GetMapping("/{eventId}")
    @PreAuthorize("hasAnyAuthority('system:use')")
    @ApiOperation(value = "事件详情接口", notes = "根据id查询事件信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "eventId", value = "事件id", required = true),
    })
    public ResponseResult<Event> getEventById(@PathVariable("eventId") Long eventId) {
//        Event event = eventService.getById(eventId);
        Event event = eventService.getEventInfoById(eventId);
        if (event != null) {
            return ResponseResult.success(event, "查询成功！");
        }
        return ResponseResult.error("查询失败！");
    }

    /**
     * @description: 事件列表接口
     * @param: [current, size, keyword]
     * @return: com.elderlycare.common.ResponseResult<com.baomidou.mybatisplus.extension.plugins.pagination.Page>
     **/
    @GetMapping("/page")
    @PreAuthorize("hasAnyAuthority('system:use')")
    @ApiOperation(value = "事件列表接口", notes = "根据关键词模糊分页查询事件信息列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页的页码", required = false),
            @ApiImplicitParam(name = "size", value = "每页的记录条数", required = false),
            @ApiImplicitParam(name = "keyword", value = "查询关键词", required = false),
            @ApiImplicitParam(name = "eventType", value = "筛选事件类型", required = false)
    })
    public ResponseResult<Page> getEventsByInfo(@RequestParam(value = "current", defaultValue = "1", required = false) Integer current,
                                                @RequestParam(value = "size", defaultValue = "10", required = false) Integer size,
                                                @RequestParam(value = "keyword", defaultValue = "", required = false) String keyword,
                                                @RequestParam(value = "eventType", defaultValue = "", required = false) String eventType) {
        // 模糊分页查询
        Page<Event> pageInfo = new Page<>(current, size);
        LambdaQueryWrapper<Event> eventLambdaQueryWrapper = new LambdaQueryWrapper<>();
        eventLambdaQueryWrapper
                .select(Event::getEventId, Event::getEventType, Event::getEventDate, Event::getEventLocation, Event::getEventDescription)
                .like(StringUtils.isNotBlank(eventType), Event::getEventType, eventType)
                .and(wrapper -> wrapper.like(Event::getEventDescription, keyword).or().like(Event::getEventLocation, keyword))
                .orderByAsc(Event::getEventId);
        eventService.page(pageInfo, eventLambdaQueryWrapper);
        return ResponseResult.success(pageInfo, "查询成功！");
    }

    /**
     * @description: 添加事件接口
     * @param: [event]
     * @return: com.elderlycare.common.ResponseResult<com.elderlycare.entity.Event>
     **/
    @PostMapping
    @PreAuthorize("hasAuthority('system:use')")
    @ApiOperation(value = "添加事件接口", notes = "添加事件信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "event", value = "要添加的事件信息", required = true)
    })
    public ResponseResult<Event> addEvent(@RequestBody Event event) {
        //保存事件信息
        if (eventService.save(event)) {
            return ResponseResult.success(null, "添加成功！");
        }
        return ResponseResult.error("添加失败！");
    }

    /**
     * @description: 修改事件接口
     * @param: [event]
     * @return: com.eventcare.common.ResponseResult<com.eventcare.entity.Event>
     **/
    @PutMapping
    @PreAuthorize("hasAnyAuthority('system:use')")
    @ApiOperation(value = "修改事件接口", notes = "修改事件信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "event", value = "修改后的事件信息", required = true)
    })
    public ResponseResult<Event> updateEvent(@RequestBody Event event) {
        // 根据id修改事件信息
        if (eventService.updateById(event)) {
            return ResponseResult.success(null, "保存成功！");
        }
        return ResponseResult.error("保存失败！");
    }

    /**
     * @description: 删除事件接口
     * @param: [eventId]
     * @return: com.eventcare.common.ResponseResult<java.lang.String>
     **/
    @DeleteMapping("/{eventId}")
    @PreAuthorize("hasAnyAuthority('system:use')")
    @ApiOperation(value = "删除事件接口", notes = "根据id删除事件信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "eventId", value = "被删除事件的id", required = true)
    })
    public ResponseResult<String> deleteEvent(@PathVariable("eventId") Long eventId) {
        // 根据id删除事件
        if (eventService.removeById(eventId)) {
            return ResponseResult.success("删除成功！");
        }
        return ResponseResult.error("删除失败！");
    }

    /**
     * @description: 事件发生时间数据统计接口
     * @param: [year]
     * @return: com.elderlycare.common.ResponseResult<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    @GetMapping("/statistics/time")
    @PreAuthorize("hasAnyAuthority('system:use')")
    @ApiOperation(value = "事件发生时间数据统计接口", notes = "根据事件发生时间数据进行统计")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "year", value = "统计年份", required = true)
    })
    public ResponseResult<Map<String, Object>> getEventTimeStatistics(Integer year) {
        return eventService.getEventTimeStatistics(year);
    }

    /**
     * @description: 事件发生地点数据统计接口
     * @param: [year]
     * @return: com.elderlycare.common.ResponseResult<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    @GetMapping("/statistics/location")
    @PreAuthorize("hasAnyAuthority('system:use')")
    @ApiOperation(value = "事件发生地点数据统计接口", notes = "根据事件发生地点数据进行统计")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "year", value = "统计年份", required = true),
    })
    public ResponseResult<Map<String, Object>> getEventLocationStatistics(@RequestParam("year") Integer year) {
        return eventService.getEventLocationStatistics(year);
    }

    /**
     * @description: 事件发生类型数据统计接口
     * @param: [year]
     * @return: com.elderlycare.common.ResponseResult<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    @GetMapping("/statistics/type")
    @PreAuthorize("hasAnyAuthority('system:use')")
    @ApiOperation(value = "事件发生类型数据统计接口", notes = "根据事件发生类型数据进行统计")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "year", value = "统计年份", required = true),
    })
    public ResponseResult<Map<String, Object>> getEventTypeStatistics(@RequestParam("year") Integer year) {
        return eventService.getEventTypeStatistics(year);
    }

}
