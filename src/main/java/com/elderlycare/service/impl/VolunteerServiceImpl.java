package com.elderlycare.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.elderlycare.common.ResponseResult;
import com.elderlycare.entity.Volunteer;
import com.elderlycare.mapper.VolunteerMapper;
import com.elderlycare.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.elderlycare.utils.StatisticsConstants.MONTH_NUM;

@Service
public class VolunteerServiceImpl extends ServiceImpl<VolunteerMapper, Volunteer> implements VolunteerService {

    @Autowired
    private VolunteerMapper volunteerMapper;

    @Override
    public ResponseResult<Map<String, Object>> getVolunteerTimeStatistics(Integer year) {

        Map<String, Object> statisticsMap = new HashMap<>();

        List<Integer> countCheckInList = new ArrayList<>();
        List<Integer> countCheckOutList = new ArrayList<>();

        for (int i = 1; i <= MONTH_NUM; i++) {
            countCheckInList.add(volunteerMapper.countVolunteerCheckInMonth(year, i));
            countCheckOutList.add(volunteerMapper.countVolunteerCheckOutMonth(year, i));
        }

        statisticsMap.put("countCheckInList", countCheckInList);
        statisticsMap.put("countCheckOutList", countCheckOutList);

        return ResponseResult.success(statisticsMap, "义工数据统计完成");
    }

}
