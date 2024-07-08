package com.elderlycare.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.elderlycare.common.ResponseResult;
import com.elderlycare.entity.Camera;
import com.elderlycare.service.CameraService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api(value = "摄像头相关接口", tags = "摄像头接口")
@Slf4j
@RestController
@RequestMapping("/api/v1/camera")
public class CameraController {

    @Autowired
    private CameraService cameraService;

    /**
     * @description: 摄像头详情接口
     * @param: [cameraId]
     * @return: com.elderlycare.common.ResponseResult<com.elderlycare.entity.Camera>
     **/
    @GetMapping("/{cameraId}")
    @PreAuthorize("hasAnyAuthority('system:use')")
    @ApiOperation(value = "摄像头详情接口", notes = "根据id查询摄像头信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cameraId", value = "摄像头id", required = true),
    })
    public ResponseResult<Camera> getCameraById(@PathVariable("cameraId") Long cameraId) {
        Camera camera = cameraService.getById(cameraId);
        if (camera != null) {
            return ResponseResult.success(camera, "查询成功！");
        }
        return ResponseResult.error("查询失败！");
    }

    /**
     * @description: 摄像头列表接口
     * @param: [current, size, keyword]
     * @return: com.elderlycare.common.ResponseResult<com.baomidou.mybatisplus.extension.plugins.pagination.Page>
     **/
    @GetMapping("/page")
    @PreAuthorize("hasAnyAuthority('system:use')")
    @ApiOperation(value = "摄像头列表接口", notes = "根据关键词模糊分页查询摄像头信息列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页的页码", required = false),
            @ApiImplicitParam(name = "size", value = "每页的记录条数", required = false),
            @ApiImplicitParam(name = "keyword", value = "查询关键词", required = false)
    })
    public ResponseResult<Page> getCameraByKey(@RequestParam(value = "current", defaultValue = "1", required = false) Integer current,
                                                @RequestParam(value = "size", defaultValue = "10", required = false) Integer size,
                                                @RequestParam(value = "keyword", defaultValue = "", required = false) String keyword) {
        // 模糊分页查询
        Page<Camera> pageInfo = new Page<>(current, size);
        LambdaQueryWrapper<Camera> cameraLambdaQueryWrapper = new LambdaQueryWrapper<>();
        cameraLambdaQueryWrapper
                .like(Camera::getCameraName, keyword)
                .orderByAsc(Camera::getCameraId);
        cameraService.page(pageInfo, cameraLambdaQueryWrapper);
        return ResponseResult.success(pageInfo, "查询成功！");
    }

    /**
     * @description: 添加摄像头接口
     * @param: [camera]
     * @return: com.elderlycare.common.ResponseResult<com.elderlycare.entity.Camera>
     **/
    @PostMapping
    @PreAuthorize("hasAuthority('system:use')")
    @ApiOperation(value = "添加摄像头接口", notes = "添加摄像头信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "camera", value = "要添加的摄像头信息", required = true)
    })
    public ResponseResult<Camera> addCamera(@RequestBody Camera camera) {
        //保存摄像头信息
        if (cameraService.save(camera)) {
            return ResponseResult.success(camera, "添加成功！");
        }
        return ResponseResult.error("添加失败！");
    }

    /**
     * @description: 修改摄像头接口
     * @param: [camera]
     * @return: com.elderlycare.common.ResponseResult<com.elderlycare.entity.Camera>
     **/
    @PutMapping
    @PreAuthorize("hasAnyAuthority('system:use')")
    @ApiOperation(value = "修改摄像头接口", notes = "修改摄像头信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "camera", value = "修改后的摄像头信息", required = true)
    })
    public ResponseResult<Camera> updateCamera(@RequestBody Camera camera) {
        // 根据id修改事件信息
        if (cameraService.updateById(camera)) {
            return ResponseResult.success(null, "保存成功！");
        }
        return ResponseResult.error("保存失败！");
    }

    /**
     * @description: 删除摄像头接口
     * @param: [cameraId]
     * @return: com.elderlycare.common.ResponseResult<java.lang.String>
     **/
    @DeleteMapping("/{cameraId}")
    @PreAuthorize("hasAnyAuthority('system:use')")
    @ApiOperation(value = "删除摄像头接口", notes = "根据id删除摄像头信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cameraId", value = "被删除摄像头的id", required = true)
    })
    public ResponseResult<String> deleteCamera(@PathVariable("cameraId") Long cameraId) {
        // 根据id删除摄像头
        if (cameraService.removeById(cameraId)) {
            return ResponseResult.success("删除成功！");
        }
        return ResponseResult.error("删除失败！");
    }

}
