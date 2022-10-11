package cn.battlehawk233.barbershop.controller.stats;

import cn.battlehawk233.barbershop.IJsonResponse;
import cn.battlehawk233.barbershop.JsonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "/stats/moneyFlow", description = "金钱流统计相关")
@RestController
@RequiredArgsConstructor
@RequestMapping("/stats/moneyFlow")
public class MoneyFlowStatsController {
    // TODO: 2022/10/11  
    @Operation(summary = "获取金钱流日报表")
    @GetMapping("/daily")
    public IJsonResponse getDailyReport() {
        return JsonResponse.OK();
    }

    // TODO: 2022/10/11  
    @Operation(summary = "获取金钱流月报表")
    @GetMapping("/monthly")
    public IJsonResponse getMonthlyReport() {
        return JsonResponse.OK();
    }

    // TODO: 2022/10/11  
    @Operation(summary = "获取金钱流年报表")
    @GetMapping("/yearly")
    public IJsonResponse getYearlyReport() {
        return JsonResponse.OK();
    }
}
