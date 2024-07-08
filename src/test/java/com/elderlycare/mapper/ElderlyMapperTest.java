package com.elderlycare.mapper;

import com.elderlycare.entity.Elderly;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Description
 * @Author YuanmingLiu
 * @Date 2023/7/20 10:02
 */
@SpringBootTest
public class ElderlyMapperTest {

    @Autowired
    private ElderlyMapper elderlyMapper;

    @Test
    void selectEventByIdByStep2() {
        Long elderlyId = 1676623591240806402L;
        Elderly elderly = elderlyMapper.selectEventByIdByStep2(elderlyId);
        System.out.println(elderly);
    }

    @Test
    void countByAgeLessThan() {
        Integer elderlyAgeLowerLimits = 60;
        int i = elderlyMapper.countByAgeLessThan(elderlyAgeLowerLimits);
        System.out.println(i);
    }

    @Test
    void countByAgeGreaterThanEqual() {
        Integer elderlyAgeUpperLimits = 90;
        int i = elderlyMapper.countByAgeGreaterThanEqual(elderlyAgeUpperLimits);
        System.out.println(i);
    }

    @Test
    void countByAgeBetween() {
        Integer elderlyAgeLow = 60;
        Integer elderlyAgeHigh = 90;
        int i = elderlyMapper.countByAgeBetween(elderlyAgeLow, elderlyAgeHigh);
        System.out.println(i);
    }
}
