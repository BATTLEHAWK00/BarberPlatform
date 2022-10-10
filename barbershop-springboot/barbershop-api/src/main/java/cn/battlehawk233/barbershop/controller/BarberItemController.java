package cn.battlehawk233.barbershop.controller;

import cn.battlehawk233.barbershop.IJsonResponse;
import cn.battlehawk233.barbershop.JsonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// TODO: 2022/10/10
@Tag(name = "/barberItem", description = "消费项目相关")
@RequestMapping("/barberItem")
@RequiredArgsConstructor
@RestController
public class BarberItemController {
    // TODO: 2022/10/10  
    @Operation(summary = "添加消费项目")
    @PostMapping("/add")
    public IJsonResponse addBarberItem() {
        return JsonResponse.OK();
    }

    // TODO: 2022/10/10
    @Operation(summary = "修改消费项目")
    @PostMapping("/update")
    public IJsonResponse updateBarberItem() {
        return JsonResponse.OK();
    }

    // TODO: 2022/10/10
    @Operation(summary = "模糊查询消费项目")
    @GetMapping("/fuzzySearch")
    public IJsonResponse fuzzySearchBarberItems(@RequestParam String query) {
        return JsonResponse.OK();
    }

    // TODO: 2022/10/10
    @Operation(summary = "根据ID查询消费项目")
    @GetMapping("/byId/{itemId}")
    public IJsonResponse getBarberItemById(@PathVariable Integer itemId) {
        return JsonResponse.OK();
    }

    // TODO: 2022/10/10
    @Operation(summary = "根据ID列表查询消费项目列表")
    @GetMapping("/byIdList")
    public IJsonResponse getBarberItemListByIds(@RequestParam List<Integer> idList) {
        return JsonResponse.OK();
    }

    // TODO: 2022/10/10
    @Operation(summary = "删除消费项目")
    @PostMapping("/delete/{itemId}")
    public IJsonResponse deleteItemById(@PathVariable Integer itemId) {
        return JsonResponse.OK();
    }
}
