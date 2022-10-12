package cn.battlehawk233.barbershop.controller.logs;

import cn.battlehawk233.barbershop.IJsonResponse;
import cn.battlehawk233.barbershop.JsonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "/logs/moneyFlow", description = "金钱流日志相关")
@RestController
@RequestMapping("/logs/moneyFLow")
@RequiredArgsConstructor
public class MoneyFlowLogController {
    // TODO: 2022/10/11  
    @Operation(summary = "获取日志")
    @GetMapping("/list")
    public IJsonResponse getLogs() {
        return JsonResponse.OK();
    }
}
