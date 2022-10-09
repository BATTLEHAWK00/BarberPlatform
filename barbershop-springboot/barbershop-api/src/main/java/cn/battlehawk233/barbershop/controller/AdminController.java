package cn.battlehawk233.barbershop.controller;

import cn.battlehawk233.barbershop.entity.Admin;
import cn.battlehawk233.barbershop.service.AdminService;
import cn.battlehawk233.barbershop.Response;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @Tag(name = "注册", description = "注册接口")
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
