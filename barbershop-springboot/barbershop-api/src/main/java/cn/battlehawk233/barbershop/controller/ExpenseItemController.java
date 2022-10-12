package cn.battlehawk233.barbershop.controller;

import cn.battlehawk233.barbershop.IJsonResponse;
import cn.battlehawk233.barbershop.JsonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "/expenseItem", description = "经营支出项目相关")
@RestController
@RequiredArgsConstructor
@RequestMapping("/expenseItem")
public class ExpenseItemController {

    // TODO: 2022/10/10
    @Operation(summary = "创建经营支出项目")
    @PostMapping("/create")
    public IJsonResponse createExpenseItem() {
        return JsonResponse.OK();
    }

    // TODO: 2022/10/10
    @Operation(summary = "修改经营支出项目")
    @PostMapping("/update")
    public IJsonResponse updateExpenseItem() {
        return JsonResponse.OK();
    }

    // TODO: 2022/10/10
    @Operation(summary = "删除经营支出项目")
    @PostMapping("/delete/{itemId}")
    public IJsonResponse deleteExpenseItem(@PathVariable Integer itemId) {
        return JsonResponse.OK();
    }

    // TODO: 2022/10/11  
    @Operation(summary = "模糊查询经营支出项目")
    @GetMapping("/fuzzySearch")
    public IJsonResponse fuzzySearchExpenseItem(@RequestParam String query){
        return JsonResponse.OK();
    }

    // TODO: 2022/10/11
    @Operation(summary = "根据ID查询经营支出项目")
    @GetMapping("/byId/{itemId}")
    public IJsonResponse getExpenseItemById(@PathVariable String itemId){
        return JsonResponse.OK();
    }
}
