package cn.battlehawk233.barbershop.controller;

import cn.battlehawk233.barbershop.IJsonResponse;
import cn.battlehawk233.barbershop.entity.Admin;
import cn.battlehawk233.barbershop.service.AdminService;
import cn.battlehawk233.barbershop.JsonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "/admin", description = "管理员相关")
@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @Operation(summary = "管理员注册")
    @GetMapping("/register")
    public JsonResponse<Object> register() {
        var admin = Admin
                .builder()
                .name("test")
                .phone("123456")
                .build();
        adminService.register(admin, "123456");
        return JsonResponse.OK_WITH_DATA(null);
    }

    // TODO: 2022/10/10
    @Operation(summary = "模糊搜索管理员，返回列表")
    @GetMapping("/fuzzySearch")
    public IJsonResponse fuzzySearchAdmins(@RequestParam String query) {
        return JsonResponse.OK_WITH_DATA(null);
    }

    // TODO: 2022/10/10
    @Operation(summary = "修改管理员信息")
    @PostMapping("/update/{adminId}")
    public IJsonResponse updateAdmin(@PathVariable String adminId) {
        return JsonResponse.OK();
    }
}
