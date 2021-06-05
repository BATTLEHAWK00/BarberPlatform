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
import org.springframework.web.servlet.ModelAndView;
import pojo.Session;
import service.SessionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class TokenInterceptor implements HandlerInterceptor {
    @Autowired
    @Setter
    private SessionService sessionService;

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset:utf-8");
        String token = req.getHeader("accessToken");
        if (token == null || !sessionService.validateToken(token)) {
            return401(resp);
            return false;
        }
        sessionService.updateLastAccessTime(token);
        return true;
    }

    void return401(HttpServletResponse resp) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Response response = new Response();
        response.setMsg("登录会话失效，请重新登录!");
        response.setCode(401);
        resp.setStatus(401);
        resp.getWriter().print(objectMapper.writeValueAsString(response));
    }
}
