package cn.battlehawk233.barbershop.controller;

import cn.battlehawk233.barbershop.IJsonResponse;
import cn.battlehawk233.barbershop.JsonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "/customer", description = "顾客相关")
@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    // TODO: 2022/10/10
    @Operation(summary = "创建顾客，录入信息")
    @PostMapping("/create")
    public IJsonResponse createCustomer() {
        return JsonResponse.OK();
    }

    // TODO: 2022/10/10  
    @Operation(summary = "修改顾客信息")
    @PostMapping("/update/{customerId}")
    public IJsonResponse updateCustomer(@PathVariable Integer customerId) {
        return JsonResponse.OK();
    }

    // TODO: 2022/10/10  
    @Operation(summary = "根据ID查询顾客信息")
    @GetMapping("/byId/{customerId}")
    public IJsonResponse getCustomerById(@PathVariable Integer customerId) {
        return JsonResponse.OK_WITH_DATA(null);
    }

    // TODO: 2022/10/10  
    @Operation(summary = "模糊搜索顾客信息，返回列表")
    @GetMapping("/fuzzySearch")
    public IJsonResponse fuzzySearchCustomers() {
        return JsonResponse.OK_WITH_DATA(null);
    }

    // TODO: 2022/10/10  
    @Operation(summary = "删除顾客信息（慎用）")
    @PostMapping("/delete/{customerId}")
    public IJsonResponse deleteCustomerById(@PathVariable String customerId) {
        return JsonResponse.OK();
    }
}
