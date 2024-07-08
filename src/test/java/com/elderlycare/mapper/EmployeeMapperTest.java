package com.elderlycare.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Description
 * @Author YuanmingLiu
 * @Date 2023/7/20 10:07
 */
@SpringBootTest
public class EmployeeMapperTest {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Test
    void countEmployeeHireMonth() {
        Integer year = 2023;
        Integer month = 7;
        int i = employeeMapper.countEmployeeHireMonth(year, month);
        System.out.println(i);
    }

    @Test
    void countEmployeeResignMonth() {
        Integer year = 2023;
        Integer month = 7;
        int i = employeeMapper.countEmployeeResignMonth(year, month);
        System.out.println(i);
    }
}
