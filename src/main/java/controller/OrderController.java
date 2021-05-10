package controller;

import bean.Order;
import bean.OrderItem;
import bean.Response;
import exceptions.ServiceException;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    @Setter
    private OrderService orderService;

    @RequestMapping(value = "/create", method = {RequestMethod.GET, RequestMethod.POST})
    public Response createOrder(
            @RequestParam("sponsorid") int sponsorid,
            @RequestParam("ownerid") int ownerid,
            @RequestParam(value = "remark", required = false) String remark
    ) throws ServiceException {
        Order order = new Order();
        order.setSponsorid(sponsorid);
        order.setOwnerid(ownerid);
        order.setRemark(remark);
        orderService.createOrder(order);
        return new Response();
    }

    @RequestMapping(value = "/id/{oid}/item/add", method = {RequestMethod.GET, RequestMethod.POST})
    public Response addItem(@PathVariable("oid") int oid,
                            @RequestParam("itemid") int itemid,
                            @RequestParam("amount") int amount,
                            @RequestParam("sponsorid") int sponsorid,
                            @RequestParam(value = "remark", required = false) String remark
    ) throws ServiceException {
        OrderItem orderItem=new OrderItem();
        orderItem.setOrderid(oid);
        orderItem.setItemid(itemid);
        orderItem.setSponsor(sponsorid);
        orderItem.setAmount(amount);
        orderItem.setRemark(remark);
        orderService.addItem(orderItem);
        return new Response();
    }
}
