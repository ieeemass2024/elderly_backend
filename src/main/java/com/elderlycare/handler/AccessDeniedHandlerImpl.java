package com.elderlycare.handler;

import com.alibaba.fastjson.JSON;
import com.elderlycare.common.ResponseResult;
import com.elderlycare.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        ResponseResult result = ResponseResult.error("权限不足");
        result.setCode(HttpStatus.FORBIDDEN.value());
        String json = JSON.toJSONString(result);
        WebUtils.renderString(httpServletResponse, json);
    }
}
