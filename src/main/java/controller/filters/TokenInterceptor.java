package controller.filters;

import bean.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import service.SessionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenInterceptor implements HandlerInterceptor {
    @Autowired
    @Setter
    private SessionService sessionService;

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        if ("OPTIONS".equals(req.getMethod().toUpperCase())) {
            return true;
        }
        String token = req.getHeader("accessToken");
        if (token == null || !sessionService.validateToken(token)) {
            return401(resp);
            return false;
        }
        sessionService.updateLastAccessTime(token);
        return true;
    }

    void return401(HttpServletResponse resp) throws IOException {
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset:utf-8");
        ObjectMapper objectMapper = new ObjectMapper();
        Response response = new Response();
        response.setMsg("登录会话失效，请重新登录!");
        response.setCode(401);
        resp.setStatus(401);
        resp.getWriter().print(objectMapper.writeValueAsString(response));
    }
}
