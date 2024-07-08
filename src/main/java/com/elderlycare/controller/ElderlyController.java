package com.elderlycare.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.elderlycare.common.ResponseResult;
import com.elderlycare.entity.Elderly;
import com.elderlycare.service.ElderlyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Api(value = "老人相关接口", tags = "老人接口")
@Slf4j
@RestController
@RequestMapping("/api/v1/elderly")
public class ElderlyController {


    @Autowired
    private ElderlyService elderlyService;

    /**
     * @description: 老人详情接口
     * @param: [elderlyId]
     * @return: com.elderlycare.common.ResponseResult<com.elderlycare.entity.Elderly>
     **/
    @GetMapping("/{elderlyId}")
    @PreAuthorize("hasAnyAuthority('system:use')")
    @ApiOperation(value = "老人详情接口", notes = "根据id查询老人信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "elderlyId", value = "老人id", required = true),
    })
    public ResponseResult<Elderly> getElderlyById(@PathVariable("elderlyId") Long elderlyId) {
        Elderly elderly = elderlyService.getById(elderlyId);
        if (elderly != null) {
            return ResponseResult.success(elderly, "查询成功！");
        }
        return ResponseResult.error("查询失败！");
    }

    /**
     * @description: 老人列表接口
     * @param: [current, size, keyword]
     * @return: com.elderlycare.common.ResponseResult<com.baomidou.mybatisplus.extension.plugins.pagination.Page>
     **/
    @GetMapping("/page")
    @PreAuthorize("hasAnyAuthority('system:use')")
    @ApiOperation(value = "老人列表接口", notes = "根据关键词模糊分页查询老人信息列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页的页码", required = false),
            @ApiImplicitParam(name = "size", value = "每页的记录条数", required = false),
            @ApiImplicitParam(name = "keyword", value = "查询关键词", required = false)
    })
    public ResponseResult<Page> getElderlyByKey(@RequestParam(value = "current", defaultValue = "1", required = false) Integer current,
                                                @RequestParam(value = "size", defaultValue = "10", required = false) Integer size,
                                                @RequestParam(value = "keyword", defaultValue = "", required = false) String keyword) {
        // 模糊分页查询
        Page<Elderly> pageInfo = new Page<>(current, size);
        LambdaQueryWrapper<Elderly> elderlyLambdaQueryWrapper = new LambdaQueryWrapper<>();
        elderlyLambdaQueryWrapper
                .like(Elderly::getElderlyName, keyword)
                .orderByAsc(Elderly::getElderlyId);
        elderlyService.page(pageInfo, elderlyLambdaQueryWrapper);
        return ResponseResult.success(pageInfo, "查询成功！");
    }

    /**
     * @description: 添加老人接口
     * @param: [elderly]
     * @return: com.elderlycare.common.ResponseResult<com.elderlycare.entity.Elderly>
     **/
    @PostMapping
    @PreAuthorize("hasAuthority('system:use')")
    @ApiOperation(value = "添加老人接口", notes = "添加老人信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "elderly", value = "要添加的老人信息", required = true)
    })
    public ResponseResult<Elderly> addElderly(@RequestBody Elderly elderly) {
        //保存老人信息
        if (elderlyService.save(elderly)) {
            return ResponseResult.success(elderly, "添加成功！");
        }
        return ResponseResult.error("添加失败！");
    }

    /**
     * @description: 修改老人接口
     * @param: [elderly]
     * @return: com.elderlycare.common.ResponseResult<com.elderlycare.entity.Elderly>
     **/
    @PutMapping
    @PreAuthorize("hasAnyAuthority('system:use')")
    @ApiOperation(value = "修改老人接口", notes = "修改老人信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "elderly", value = "修改后的老人信息", required = true)
    })
    public ResponseResult<Elderly> updateElderly(@RequestBody Elderly elderly) {
        // 根据id修改事件信息
        if (elderlyService.updateById(elderly)) {
            return ResponseResult.success(null, "保存成功！");
        }
        return ResponseResult.error("保存失败！");
    }

    /**
     * @description: 删除老人接口
     * @param: [elderlyId]
     * @return: com.elderlycare.common.ResponseResult<java.lang.String>
     **/
    @DeleteMapping("/{elderlyId}")
    @PreAuthorize("hasAnyAuthority('system:use')")
    @ApiOperation(value = "删除老人接口", notes = "根据id删除老人信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "elderlyId", value = "被删除老人的id", required = true)
    })
    public ResponseResult<String> deleteElderly(@PathVariable("elderlyId") Long elderlyId) {
        // 根据id删除老人
        if (elderlyService.removeById(elderlyId)) {
            return ResponseResult.success("删除成功！");
        }
        return ResponseResult.error("删除失败！");
    }

    /**
     * @description: 老人年龄数据统计接口
     * @param: [ageOffset]
     * @return: com.elderlycare.common.ResponseResult<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    @GetMapping("/statistics/age")
    @PreAuthorize("hasAnyAuthority('system:use')")
    @ApiOperation(value = "老人年龄数据统计接口", notes = "根据老人年龄数据进行统计")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ageOffset", value = "统计老人年龄间隔", required = true)
    })
    public ResponseResult<Map<String, Object>> getElderlyAgeStatistics(@RequestParam("ageOffset") Integer ageOffset) {

        return elderlyService.getElderlyAgeStatistics(ageOffset);
    }

    /**
     * @description: 老人健康情况数据统计接口
     * @param: []
     * @return: com.elderlycare.common.ResponseResult<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    @GetMapping("/statistics/health")
    @PreAuthorize("hasAnyAuthority('system:use')")
    @ApiOperation(value = "老人健康情况数据统计接口", notes = "根据老人健康数据进行统计")
    @ApiImplicitParams({
    })
    public ResponseResult<Map<String, Object>> getElderlyHealthStatistics() {

        return elderlyService.getElderlyHealthStatistics();
    }

}
