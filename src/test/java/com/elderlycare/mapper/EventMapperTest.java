package com.elderlycare.mapper;

import com.elderlycare.entity.Event;
import com.elderlycare.entity.EventStatistic;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Description
 * @Author YuanmingLiu
 * @Date 2023/7/20 10:08
 */
@SpringBootTest
public class EventMapperTest {

    @Autowired
    private EventMapper eventMapper;

    @Test
    void selectEventByIdByStep1() {
        Long eventId = 1676633431392546818L;
        Event event = eventMapper.selectEventByIdByStep1(eventId);
        System.out.println(event);
    }

    @Test
    void countEventMonth() {
        Integer year = 2023;
        Integer month = 7;
        int i = eventMapper.countEventMonth(year, month);
        System.out.println(i);
    }

    @Test
    void countEventLocation() {
        Integer year = 2023;
        List<EventStatistic> eventStatistics = eventMapper.countEventLocation(year);
        eventStatistics.forEach(System.out::println);
    }
}
