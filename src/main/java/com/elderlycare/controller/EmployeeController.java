package com.elderlycare.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.elderlycare.common.ResponseResult;
import com.elderlycare.entity.Employee;
import com.elderlycare.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api(value = "工作人员相关接口", tags = "工作人员接口")
@Slf4j
@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * @description: 工作人员详情接口
     * @param: [employeeId]
     * @return: com.elderlycare.common.ResponseResult<com.elderlycare.entity.Employee>
     **/
    @GetMapping("/{employeeId}")
    @PreAuthorize("hasAnyAuthority('system:use')")
    @ApiOperation(value = "工作人员详情接口", notes = "根据id查询工作人员信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "employeeId", value = "工作人员id", required = true),
    })
    public ResponseResult<Employee> getEmployeeById(@PathVariable("employeeId") Long employeeId) {
        Employee employee = employeeService.getById(employeeId);
        if (employee != null) {
            return ResponseResult.success(employee, "查询成功！");
        }
        return ResponseResult.error("查询失败！");
    }

    /**
     * @description: 工作人员列表接口
     * @param: [current, size, keyword]
     * @return: com.elderlycare.common.ResponseResult<com.baomidou.mybatisplus.extension.plugins.pagination.Page>
     **/
    @GetMapping("/page")
    @PreAuthorize("hasAnyAuthority('system:use')")
    @ApiOperation(value = "工作人员列表接口", notes = "根据关键词模糊分页查询工作人员信息列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页的页码", required = false),
            @ApiImplicitParam(name = "size", value = "每页的记录条数", required = false),
            @ApiImplicitParam(name = "keyword", value = "查询关键词", required = false)
    })
    public ResponseResult<Page> getEmployeesByKey(@RequestParam(value = "current", defaultValue = "1", required = false) Integer current,
                                                  @RequestParam(value = "size", defaultValue = "10", required = false) Integer size,
                                                  @RequestParam(value = "keyword", defaultValue = "", required = false) String keyword) {
        // 模糊分页查询
        Page<Employee> pageInfo = new Page<>(current, size);
        LambdaQueryWrapper<Employee> employeeLambdaQueryWrapper = new LambdaQueryWrapper<>();
        employeeLambdaQueryWrapper
                .like(Employee::getEmployeeName, keyword)
                .orderByAsc(Employee::getEmployeeId);
        employeeService.page(pageInfo, employeeLambdaQueryWrapper);
        return ResponseResult.success(pageInfo, "查询成功！");
    }

    /**
     * @description: 添加工作人员接口
     * @param: [employee]
     * @return: com.elderlycare.common.ResponseResult<com.elderlycare.entity.Employee>
     **/
    @PostMapping
    @PreAuthorize("hasAuthority('system:use')")
    @ApiOperation(value = "添加工作人员接口", notes = "添加工作人员信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "employee", value = "要添加的工作人员信息", required = true)
    })
    public ResponseResult<Employee> addEmployee(@RequestBody Employee employee) {
        //保存工作人员信息
        if (employeeService.save(employee)) {
            return ResponseResult.success(employee, "添加成功！");
        }
        return ResponseResult.error("添加失败！");
    }

    /**
     * @description: 修改工作人员接口
     * @param: [employee]
     * @return: com.elderlycare.common.ResponseResult<com.elderlycare.entity.Employee>
     **/
    @PutMapping
    @PreAuthorize("hasAnyAuthority('system:use')")
    @ApiOperation(value = "修改工作人员接口", notes = "修改工作人员信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "employee", value = "修改后的工作人员信息", required = true)
    })
    public ResponseResult<Employee> updateEmployee(@RequestBody Employee employee) {
        // 根据id修改工作人员信息
        if (employeeService.updateById(employee)) {
            return ResponseResult.success(null, "保存成功！");
        }
        return ResponseResult.error("保存失败！");
    }

    /**
     * @description: 删除工作人员接口
     * @param: [employeeId]
     * @return: com.elderlycare.common.ResponseResult<java.lang.String>
     **/
    @DeleteMapping("/{employeeId}")
    @PreAuthorize("hasAnyAuthority('system:use')")
    @ApiOperation(value = "删除工作人员接口", notes = "根据id删除工作人员信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "employeeId", value = "被删除工作人员的id", required = true)
    })
    public ResponseResult<String> deleteEmployee(@PathVariable("employeeId") Long employeeId) {
        // 根据id删除工作人员
        if (employeeService.removeById(employeeId)) {
            return ResponseResult.success("删除成功！");
        }
        return ResponseResult.error("删除失败！");
    }

    /**
     * @description: 工作人员离职入职时间数据统计接口
     * @param: [year]
     * @return: com.elderlycare.common.ResponseResult<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    @GetMapping("/statistics/time")
    @PreAuthorize("hasAnyAuthority('system:use')")
    @ApiOperation(value = "工作人员离职入职时间数据统计接口", notes = "根据工作人员离职入职时间数据进行统计")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "year", value = "统计年份", required = true)
    })
    public ResponseResult<Map<String, Object>> getEmployeeTimeStatistics(@RequestParam("year") Integer year) {
        return employeeService.getEmployeeTimeStatistics(year);
    }

}
