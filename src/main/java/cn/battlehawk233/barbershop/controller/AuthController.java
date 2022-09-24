package cn.battlehawk233.barbershop.controller;

import cn.battlehawk233.barbershop.service.AdminService;
import cn.battlehawk233.barbershop.service.AuthService;
import cn.battlehawk233.barbershop.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final UserService userService;
    private final AdminService adminService;
    @GetMapping("/login")
    public void login(){
        adminService.test();
    }
}
