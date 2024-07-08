package com.elderlycare.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.elderlycare.common.ResponseResult;
import com.elderlycare.entity.Employee;

import java.util.Map;

public interface EmployeeService extends IService<Employee> {

    /**
     * @description: 根据工作人员离职入职时间数据进行统计
     * @param: []
     * @return: com.elderlycare.common.ResponseResult<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    ResponseResult<Map<String, Object>> getEmployeeTimeStatistics(Integer year);

}
