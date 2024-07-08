package com.elderlycare.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.elderlycare.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * @description: 根据用户id查询对应的权限信息
     * @param: [userId]
     * @return: java.util.List<java.lang.String>
     **/
    List<String> selectPermsByUserId(@Param("userId") Long userId);

}
