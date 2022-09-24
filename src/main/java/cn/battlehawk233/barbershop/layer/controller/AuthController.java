package cn.battlehawk233.barbershop.layer.controller;

import cn.battlehawk233.barbershop.layer.dao.AdminRepo;
import cn.battlehawk233.barbershop.layer.entity.Admin;
import cn.battlehawk233.barbershop.layer.service.AdminService;
import cn.battlehawk233.barbershop.pojo.Response;
import cn.battlehawk233.barbershop.layer.service.AuthService;
import cn.battlehawk233.barbershop.layer.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

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
}