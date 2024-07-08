package com.elderlycare.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.elderlycare.entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {

    /**
     * @description: 统计某月份入职的工作人员数
     * @param: [year, month]
     * @return: int
     **/
    int countEmployeeHireMonth(@Param("year") Integer year, @Param("month") Integer month);

    /**
     * @description: 统计某月份离职的工作人员数
     * @param: [year, month]
     * @return: int
     **/
    int countEmployeeResignMonth(@Param("year") Integer year, @Param("month") Integer month);

}
