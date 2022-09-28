package cn.battlehawk233.barbershop.layer.controller;

import cn.battlehawk233.barbershop.layer.entity.Admin;
import cn.battlehawk233.barbershop.layer.service.AdminService;
import cn.battlehawk233.barbershop.pojo.Response;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @GetMapping("/register")
    public Response<Object> register() {
        var admin = Admin
                .builder()
                .name("test")
                .phone("123456")
                .build();
        adminService.register(admin, "123456");
        return Response.OK_WITH_DATA(null);
    }
}
