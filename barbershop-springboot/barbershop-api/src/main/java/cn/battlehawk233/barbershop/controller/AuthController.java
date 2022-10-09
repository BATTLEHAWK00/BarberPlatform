package cn.battlehawk233.barbershop.controller;

import cn.battlehawk233.barbershop.service.AuthService;
import cn.battlehawk233.barbershop.Response;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @Tag(name = "登录", description = "登录接口")
    @GetMapping("/login")
    public Response<String> login(
            HttpServletRequest req,
            @RequestHeader("User-Agent") String ua,
            String username,
            String password
    ) {
        var ip = req.getRemoteAddr();
        return Response.OK_WITH_DATA(authService.login(username, password, ip, ua));
    }

    @GetMapping("/test")
    public Response<String> test() {
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        return Response.OK_WITH_DATA("test");
    }
}
