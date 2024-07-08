package com.elderlycare.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Description
 * @Author YuanmingLiu
 * @Date 2023/7/20 10:13
 */
@SpringBootTest
public class MenuMapperTest {

    @Autowired
    private MenuMapper menuMapper;

    @Test
    void selectPermsByUserId() {
        Long userId = 1L;
        List<String> list = menuMapper.selectPermsByUserId(userId);
        list.forEach(System.out::println);
    }
}
