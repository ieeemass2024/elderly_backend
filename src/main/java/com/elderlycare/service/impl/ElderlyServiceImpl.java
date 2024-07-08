package com.elderlycare.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.elderlycare.common.ResponseResult;
import com.elderlycare.entity.Elderly;
import com.elderlycare.mapper.ElderlyMapper;
import com.elderlycare.service.ElderlyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.elderlycare.utils.StatisticsConstants.ELDERLY_AGE_LOWER_LIMITS;
import static com.elderlycare.utils.StatisticsConstants.ELDERLY_AGE_UPPER_LIMITS;

@Service
public class ElderlyServiceImpl extends ServiceImpl<ElderlyMapper, Elderly> implements ElderlyService {

    @Autowired
    private ElderlyMapper elderlyMapper;

    @Override
    public ResponseResult<Map<String, Object>> getElderlyAgeStatistics(Integer ageOffset) {

        Map<String, Object> statisticsMap = new HashMap<String, Object>();

        List<String> labelList = new ArrayList<>();
        List<Integer> countList = new ArrayList<>();

        // 左闭右开的方式处理年龄统计
        // 小于年龄下限
        labelList.add(ELDERLY_AGE_LOWER_LIMITS + "岁以下");
        countList.add(elderlyMapper.countByAgeLessThan(ELDERLY_AGE_LOWER_LIMITS));

        // 区间中的数据
        for (int low = ELDERLY_AGE_LOWER_LIMITS; low < ELDERLY_AGE_UPPER_LIMITS; low += ageOffset) {
            int high = (low + ageOffset - 1 >= ELDERLY_AGE_UPPER_LIMITS) ? (ELDERLY_AGE_UPPER_LIMITS - 1) : (low + ageOffset - 1);

            labelList.add(low + "-" + high + "岁");
            countList.add(elderlyMapper.countByAgeBetween(low, high));
        }

        // 大于等于年龄上限
        labelList.add(ELDERLY_AGE_UPPER_LIMITS + "岁以下");
        countList.add(elderlyMapper.countByAgeGreaterThanEqual(ELDERLY_AGE_UPPER_LIMITS));

        statisticsMap.put("labelList", labelList);
        statisticsMap.put("countList", countList);

        return ResponseResult.success(statisticsMap, "老人年龄数据统计完成");
    }

    @Override
    public ResponseResult<Map<String, Object>> getElderlyHealthStatistics() {

        Map<String, Object> map = new HashMap<>();

        LambdaQueryWrapper<Elderly> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(Elderly::getHealthState, "健康");
        map.put("健康", elderlyMapper.selectCount(queryWrapper1));

        LambdaQueryWrapper<Elderly> queryWrapper2 = new LambdaQueryWrapper<>();
        queryWrapper2.eq(Elderly::getHealthState, "身体不适");
        map.put("身体不适", elderlyMapper.selectCount(queryWrapper2));

        return ResponseResult.success(map, "老人健康数据统计完成");
    }
}
