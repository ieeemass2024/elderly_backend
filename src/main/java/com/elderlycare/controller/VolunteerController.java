package com.elderlycare.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.elderlycare.common.ResponseResult;
import com.elderlycare.entity.Volunteer;
import com.elderlycare.service.VolunteerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(value = "义工相关接口", tags = "义工接口")
@Slf4j
@RestController
@RequestMapping("/api/v1/volunteer")
public class VolunteerController {

    @Autowired
    private VolunteerService volunteerService;

    /**
     * @description: 义工详情接口
     * @param: [volunteerId]
     * @return: com.elderlycare.common.ResponseResult<com.elderlycare.entity.Volunteer>
     **/
    @GetMapping("/{volunteerId}")
    @PreAuthorize("hasAnyAuthority('system:use')")
    @ApiOperation(value = "义工详情接口", notes = "根据id查询义工信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "volunteerId", value = "义工id", required = true)
    })
    public ResponseResult<Volunteer> getVolunteerById(@PathVariable("volunteerId") Long volunteerId) {
        Volunteer volunteer = volunteerService.getById(volunteerId);
        if (volunteer != null) {
            return ResponseResult.success(volunteer, "查询成功！");
        }
        return ResponseResult.error("查询失败！");
    }

    /**
     * @description: 义工列表接口
     * @param: [current, size, keyword]
     * @return: com.elderlycare.common.ResponseResult<com.baomidou.mybatisplus.extension.plugins.pagination.Page>
     **/
    @GetMapping("/page")
    @PreAuthorize("hasAnyAuthority('system:use')")
    @ApiOperation(value = "义工列表接口", notes = "根据关键词模糊分页查询义工信息列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页的页码", required = false),
            @ApiImplicitParam(name = "size", value = "每页的记录条数", required = false),
            @ApiImplicitParam(name = "keyword", value = "查询关键词", required = false)
    })
    public ResponseResult<Page> getVolunteersByKey(@RequestParam(value = "current", defaultValue = "1", required = false) Integer current,
                                                   @RequestParam(value = "size", defaultValue = "10", required = false) Integer size,
                                                   @RequestParam(value = "keyword", defaultValue = "", required = false) String keyword) {
        // 模糊分页查询
        Page<Volunteer> pageInfo = new Page<>(current, size);
        LambdaQueryWrapper<Volunteer> volunteerLambdaQueryWrapper = new LambdaQueryWrapper<>();
        volunteerLambdaQueryWrapper
                .like(Volunteer::getVolunteerName, keyword)
                .orderByAsc(Volunteer::getVolunteerId);
        volunteerService.page(pageInfo, volunteerLambdaQueryWrapper);
        return ResponseResult.success(pageInfo, "查询成功！");
    }

    /**
     * @description: 添加义工接口
     * @param: [volunteer]
     * @return: com.elderlycare.common.ResponseResult<com.elderlycare.entity.Volunteer>
     **/
    @PostMapping
    @PreAuthorize("hasAuthority('system:use')")
    @ApiOperation(value = "添加义工接口", notes = "添加义工信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "volunteer", value = "要添加的义工信息", required = true)
    })
    public ResponseResult<Volunteer> addVolunteer(@RequestBody Volunteer volunteer) {
        //保存义工信息
        if (volunteerService.save(volunteer)) {
            return ResponseResult.success(volunteer, "添加成功！");
        }
        return ResponseResult.error("添加失败！");
    }

    /**
     * @description: 修改义工接口
     * @param: [volunteer]
     * @return: com.elderlycare.common.ResponseResult<com.elderlycare.entity.Volunteer>
     **/
    @PutMapping
    @PreAuthorize("hasAnyAuthority('system:use')")
    @ApiOperation(value = "修改义工接口", notes = "修改义工信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "volunteer", value = "修改后的义工信息", required = true)
    })
    public ResponseResult<Volunteer> updateVolunteer(@RequestBody Volunteer volunteer) {
        // 根据id修改义工信息
        if (volunteerService.updateById(volunteer)) {
            return ResponseResult.success(null, "保存成功！");
        }
        return ResponseResult.error("保存失败！");
    }

    /**
     * @description: 删除义工接口
     * @param: [volunteerId]
     * @return: com.elderlycare.common.ResponseResult<java.lang.String>
     **/
    @DeleteMapping("/{volunteerId}")
    @PreAuthorize("hasAnyAuthority('system:use')")
    @ApiOperation(value = "删除义工接口", notes = "根据id删除义工信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "volunteerId", value = "被删除义工的id", required = true)
    })
    public ResponseResult<String> deleteVolunteer(@PathVariable("volunteerId") Long volunteerId) {
        // 根据id删除义工
        if (volunteerService.removeById(volunteerId)) {
            return ResponseResult.success("删除成功！");
        }
        return ResponseResult.error("删除失败！");
    }

    /**
     * @description: 义工访问离开时间数据统计接口
     * @param: [year]
     * @return: com.elderlycare.common.ResponseResult<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    @GetMapping("/statistics/time")
    @PreAuthorize("hasAnyAuthority('system:use')")
    @ApiOperation(value = "义工访问离开时间数据统计接口", notes = "根据义工访问离开时间数据进行统计")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "year", value = "统计年份", required = true)
    })
    public ResponseResult<Map<String, Object>> getVolunteerTimeStatistics(@RequestParam("year") Integer year) {

        return volunteerService.getVolunteerTimeStatistics(year);
    }
}
