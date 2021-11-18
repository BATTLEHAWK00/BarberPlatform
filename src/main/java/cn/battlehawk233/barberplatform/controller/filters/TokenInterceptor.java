package cn.battlehawk233.barberplatform.controller.filters;

import cn.battlehawk233.barberplatform.bean.Response;
import cn.battlehawk233.barberplatform.dao.TokenMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import cn.battlehawk233.barberplatform.service.TokenService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AllArgsConstructor
@Component
public class TokenInterceptor implements HandlerInterceptor {
    private TokenService tokenService;
    private TokenMapper tokenMapper;

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        if ("OPTIONS".equalsIgnoreCase(req.getMethod())) {
            return true;
        }
        String token = req.getHeader("accessToken");
        if (token == null || !tokenService.validateToken(token)) {
            return401(resp);
            return false;
        }
        tokenService.updateLastAccessTime(token);
        tokenMapper.updateIp(token, req.getRemoteAddr());
        tokenMapper.updateUa(token, req.getRemoteUser());
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
