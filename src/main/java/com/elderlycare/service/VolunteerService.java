package com.elderlycare.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.elderlycare.common.ResponseResult;
import com.elderlycare.entity.Volunteer;

import java.util.Map;

public interface VolunteerService extends IService<Volunteer> {

    /**
     * @description: 根据义工访问离开时间数据进行统计
     * @param: [year]
     * @return: com.elderlycare.common.ResponseResult<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    ResponseResult<Map<String, Object>> getVolunteerTimeStatistics(Integer year);
}
