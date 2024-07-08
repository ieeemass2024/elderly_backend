package com.elderlycare.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Description
 * @Author YuanmingLiu
 * @Date 2023/7/20 10:14
 */
@SpringBootTest
public class VolunteerMapperTest {

    @Autowired
    private VolunteerMapper volunteerMapper;

    @Test
    void countVolunteerCheckInMonth() {
        Integer year = 2023;
        Integer month = 7;
        int i = volunteerMapper.countVolunteerCheckInMonth(year, month);
        System.out.println(i);
    }

    @Test
    void countVolunteerCheckOutMonth() {
        Integer year = 2023;
        Integer month = 7;
        int i = volunteerMapper.countVolunteerCheckOutMonth(year, month);
        System.out.println(i);
    }
}
