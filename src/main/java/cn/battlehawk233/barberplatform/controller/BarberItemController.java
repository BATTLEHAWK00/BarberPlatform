package cn.battlehawk233.barberplatform.controller;

import cn.battlehawk233.barberplatform.bean.Response;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.battlehawk233.barberplatform.pojo.BarberItem;
import cn.battlehawk233.barberplatform.service.BarberItemService;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/barberitem")
public class BarberItemController {
    private final BarberItemService barberItemService;

    @RequestMapping("/list")
    public List<BarberItem> getItemList() {
        return barberItemService.getItemList();
    }

    @RequestMapping(value = "/", method = {RequestMethod.POST})
    public boolean addItem(
            @RequestParam("name") String name,
            @RequestParam("price") BigDecimal price,
            @RequestParam(value = "img", required = false) String img,
            @RequestParam(value = "remark", required = false) String remark
    ) {
        BarberItem barberItem = new BarberItem();
        barberItem.setName(name);
        barberItem.setPrice(price);
        barberItem.setImg(img);
        barberItem.setRemark(remark);
        barberItemService.addItem(barberItem);
        return true;
    }

    @RequestMapping(value = "/id/{id}", method = {RequestMethod.DELETE})
    public boolean deleteItem(
            @PathVariable("id") int id
    ) {
        barberItemService.deleteItem(id);
        return true;
    }
}
