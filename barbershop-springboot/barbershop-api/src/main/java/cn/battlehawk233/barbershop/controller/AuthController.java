package cn.battlehawk233.barbershop.controller;

import cn.battlehawk233.barbershop.IJsonResponse;
import cn.battlehawk233.barbershop.service.AuthService;
import cn.battlehawk233.barbershop.JsonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Tag(name = "/auth", description = "鉴权相关")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @Operation(summary = "登录接口")
    @GetMapping("/login")
    public IJsonResponse login(
            HttpServletRequest req,
            @RequestHeader("User-Agent") String ua,
            String username,
            String password
    ) {
        var ip = req.getRemoteAddr();
        return JsonResponse.OK_WITH_DATA(authService.login(username, password, ip, ua));
    }

    @Operation(summary = "注销接口")
    @PostMapping("/logout")
    public IJsonResponse logout() {
        return JsonResponse.OK();
    }

    @GetMapping("/test")
    public IJsonResponse test() {
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        return JsonResponse.OK_WITH_DATA("test");
    }
}
