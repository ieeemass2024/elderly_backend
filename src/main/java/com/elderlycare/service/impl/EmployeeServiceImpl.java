package com.elderlycare.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.elderlycare.common.ResponseResult;
import com.elderlycare.entity.Employee;
import com.elderlycare.mapper.EmployeeMapper;
import com.elderlycare.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.elderlycare.utils.StatisticsConstants.MONTH_NUM;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public ResponseResult<Map<String, Object>> getEmployeeTimeStatistics(Integer year) {

        Map<String, Object> statisticsMap = new HashMap<>();

        List<Integer> countHireList = new ArrayList<>();
        List<Integer> countResignList = new ArrayList<>();

        for (int i = 1; i <= MONTH_NUM; i++) {
            countHireList.add(employeeMapper.countEmployeeHireMonth(year, i));
            countResignList.add(employeeMapper.countEmployeeResignMonth(year, i));
        }

        statisticsMap.put("countHireList", countHireList);
        statisticsMap.put("countResignList", countResignList);

        return ResponseResult.success(statisticsMap, "工作人员数据统计完成");
    }

}
