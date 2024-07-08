package com.elderlycare.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.elderlycare.entity.Elderly;
import com.elderlycare.entity.Event;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ElderlyMapper extends BaseMapper<Elderly> {

    /**
     * @description: 根据事件id查询事件信息第二步
     * @param: [elderlyId]
     * @return: com.elderlycare.entity.Elderly
     **/
    Elderly selectEventByIdByStep2(@Param("elderlyId") Long elderlyId);

    /**
     * @description: 统计小于某个年龄的老人数量
     * @param: [elderlyAgeLowerLimits]
     * @return: int
     **/
    int countByAgeLessThan(@Param("elderlyAgeLowerLimits") Integer elderlyAgeLowerLimits);

    /**
     * @description: 统计大于等于某个年龄的老人数量
     * @param: [elderlyAgeUpperLimits]
     * @return: int
     **/
    int countByAgeGreaterThanEqual(@Param("elderlyAgeUpperLimits") Integer elderlyAgeUpperLimits);

    /**
     * @description: 统计某个年龄区间内的老人数量
     * @param: [elderlyAgeLow, elderlyAgeHigh]
     * @return: int
     **/
    int countByAgeBetween(@Param("elderlyAgeLow") Integer elderlyAgeLow,
                          @Param("elderlyAgeHigh") Integer elderlyAgeHigh);
}
