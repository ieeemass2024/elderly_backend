package com.elderlycare.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.elderlycare.common.ResponseResult;
import com.elderlycare.entity.Elderly;

import java.util.Map;

public interface ElderlyService extends IService<Elderly> {

    /**
     * @description: 根据老人年龄数据进行统计
     * @param: [ageOffset]
     * @return: java.lang.Object
     **/
    ResponseResult<Map<String, Object>> getElderlyAgeStatistics(Integer ageOffset);

    /**
     * @description: 根据老人健康数据进行统计
     * @param: []
     * @return: com.elderlycare.common.ResponseResult<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    ResponseResult<Map<String, Object>> getElderlyHealthStatistics();

}
