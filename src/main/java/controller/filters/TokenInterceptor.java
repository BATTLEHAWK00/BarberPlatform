package controller.filters;

import bean.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import dao.SessionMapper;
import exceptions.ServiceException;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import pojo.Session;
import service.SessionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class TokenInterceptor implements HandlerInterceptor {
    public static final int SESSION_TIMEOUT = 3;

    @Autowired
    @Setter
    private SessionMapper sessionMapper;

    @Autowired
    @Setter
    private SessionService sessionService;

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        String token = req.getHeader("accessToken");
        if (token == null || !sessionService.validateToken(token)) {
            return403(resp);
            return false;
        }
        sessionService.updateLastAccessTime(token);
        return true;
    }

    void return403(HttpServletResponse resp) throws IOException {
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset:utf-8");
        ObjectMapper objectMapper = new ObjectMapper();
        Response response = new Response();
        response.setMsg("您没有权限进行此操作!");
        response.setCode(403);
        resp.getWriter().print(objectMapper.writeValueAsString(response));
    }
}
