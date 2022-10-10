package cn.battlehawk233.barbershop.controller.log;

import cn.battlehawk233.barbershop.IJsonResponse;
import cn.battlehawk233.barbershop.JsonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "/logs/login", description = "登录日志相关")
@RequestMapping("/logs/login")
@RestController
@RequiredArgsConstructor
public class LoginLogController {
    @Operation(summary = "获取登录日志，根据时间倒序排序")
    @GetMapping("/list")
    public IJsonResponse getLoginLogs() {
        return JsonResponse.OK_WITH_DATA(null);
    }
}
