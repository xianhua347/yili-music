package com.bilitech.yilimusic.utils;

import cn.hutool.json.JSONUtil;
import com.bilitech.yilimusic.enums.ExceptionType;
import com.bilitech.yilimusic.exception.ErrorResponse;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
/**
 * 响应工具类，提供发送错误响应的方法
 */
public class ResponseUtil {
  /**
   * 发送错误响应
   *
   * @param response 响应对象
   * @param status   响应状态码
   * @param type     错误类型枚举值
   */
  public static void sendErrorResponse(HttpServletResponse response, HttpStatus status, ExceptionType type) throws IOException {
    response.setHeader("Access-Control-Allow-Origin", "*");
    response.setHeader("Cache-Control", "no-cache");
    response.setCharacterEncoding("UTF-8");
    response.setContentType("application/json");
    response.setStatus(status.value());
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setCode(type.getCode());
    errorResponse.setMessage(type.getMessage());
    response.getWriter().println(JSONUtil.parse(errorResponse));
    response.getWriter().flush();
  }
}
