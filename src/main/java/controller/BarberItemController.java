package controller;

import bean.BarberItem;
import bean.Response;
import exceptions.ServiceException;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.BarberItemService;

import java.math.BigDecimal;

@RestController
@RequestMapping("/barberitem")
public class BarberItemController {
    @Autowired
    @Setter
    private BarberItemService barberItemService;

    @RequestMapping("/list")
    public Response getItemList() {
        Response response = new Response();
        response.setData(barberItemService.getItemList());
        return response;
    }

    @RequestMapping(value = "/", method = {RequestMethod.POST})
    public Response addItem(
            @RequestParam("name") String name,
            @RequestParam("price") BigDecimal price,
            @RequestParam(value = "img", required = false) String img,
            @RequestParam(value = "remark", required = false) String remark
    ) throws ServiceException {
        Response response = new Response();
        BarberItem barberItem = new BarberItem();
        barberItem.setName(name);
        barberItem.setPrice(price);
        barberItem.setImg(img);
        barberItem.setRemark(remark);
        barberItemService.addItem(barberItem);
        return response;
    }

    @RequestMapping(value = "/id/{id}", method = {RequestMethod.DELETE})
    public Response deleteItem(
            @PathVariable("id") int id
    ) throws ServiceException {
        Response response = new Response();
        barberItemService.deleteItem(id);
        return response;
    }
}
