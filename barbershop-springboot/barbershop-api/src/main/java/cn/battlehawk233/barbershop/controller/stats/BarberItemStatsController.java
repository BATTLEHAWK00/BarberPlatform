package cn.battlehawk233.barbershop.controller.stats;

import cn.battlehawk233.barbershop.IJsonResponse;
import cn.battlehawk233.barbershop.JsonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "/stats/barberItem", description = "消费项目统计相关")
@RestController
@RequestMapping("/stats/barberItem")
@RequiredArgsConstructor
public class BarberItemStatsController {
    // TODO: 2022/10/11
    @Operation(summary = "获取日报表")
    @GetMapping("/daily")
    public IJsonResponse getDailyReport() {
        return JsonResponse.OK();
    }
}
