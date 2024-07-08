package com.elderlycare.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.elderlycare.entity.Volunteer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface VolunteerMapper extends BaseMapper<Volunteer> {

    /**
     * @description: 统计某月份访问的义工数
     * @param: [year, month]
     * @return: int
     **/
    int countVolunteerCheckInMonth(@Param("year") Integer year, @Param("month") Integer month);

    /**
     * @description: 统计某月份离开的义工数
     * @param: [year, month]
     * @return: int
     **/
    int countVolunteerCheckOutMonth(@Param("year") Integer year, @Param("month") Integer month);
}
