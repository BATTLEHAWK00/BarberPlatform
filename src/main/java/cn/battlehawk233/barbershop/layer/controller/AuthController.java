package cn.battlehawk233.barbershop.layer.controller;

import cn.battlehawk233.barbershop.layer.dao.AdminRepo;
import cn.battlehawk233.barbershop.entity.Admin;
import cn.battlehawk233.barbershop.layer.service.AdminService;
import cn.battlehawk233.barbershop.pojo.Response;
import cn.battlehawk233.barbershop.layer.service.AuthService;
import cn.battlehawk233.barbershop.layer.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final UserService userService;
    private final AdminService adminService;

    private final AdminRepo adminRepo;

    @GetMapping("/login")
    public Response<List<Admin>> login() {
        return Response.OK_WITH_DATA(adminRepo.findByIdStartsWith(1));
    }
}
