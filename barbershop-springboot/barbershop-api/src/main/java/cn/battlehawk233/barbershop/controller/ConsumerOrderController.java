package cn.battlehawk233.barbershop.controller;

import cn.battlehawk233.barbershop.IJsonResponse;
import cn.battlehawk233.barbershop.JsonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "/consumeOrder", description = "消费订单相关")
@RequiredArgsConstructor
@RestController
@RequestMapping("/consumerOrder")
public class ConsumerOrderController {
    // TODO: 2022/10/10
    @Operation(summary = "创建订单")
    @PostMapping("/create")
    public IJsonResponse createOrder() {
        return JsonResponse.OK();
    }

    // TODO: 2022/10/10  
    @Operation(summary = "修改订单")
    @PostMapping("/update/{orderId}")
    public IJsonResponse updateOrder(@PathVariable Integer orderId) {
        return JsonResponse.OK();
    }

    // TODO: 2022/10/10  
    @Operation(summary = "关闭订单")
    @PostMapping("/close/{orderId}")
    public IJsonResponse closeOrder(@PathVariable Integer orderId) {
        return JsonResponse.OK();
    }

    // TODO: 2022/10/10 
    @Operation(summary = "根据ID获取订单信息")
    @GetMapping("/byId/{orderId}")
    public IJsonResponse getOrderById(@PathVariable String orderId) {
        return JsonResponse.OK();
    }

    // TODO: 2022/10/10
    @Operation(summary = "添加订单项目")
    @PostMapping("/item/add")
    public IJsonResponse addItems(){
        return JsonResponse.OK();
    }

    // TODO: 2022/10/10
    @Operation(summary = "删除订单项目")
    @PostMapping("/item/remove")
    public IJsonResponse removeItems(){
        return JsonResponse.OK();
    }
}
