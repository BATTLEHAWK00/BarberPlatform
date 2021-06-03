package controller;

import pojo.Order;
import pojo.OrderItem;
import bean.Response;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.OrderService;

@CrossOrigin(origins = "*", maxAge = 3600)
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
    ) {
        Order order = new Order();
        order.setSponsorid(sponsorid);
        order.setOwnerid(ownerid);
        order.setRemark(remark);
        orderService.createOrder(order);
        return new Response();
    }

    @RequestMapping(value = "/id/{oid}/item", method = {RequestMethod.POST})
    public Response addItem(@PathVariable("oid") int oid,
                            @RequestParam("itemid") int itemid,
                            @RequestParam("amount") int amount,
                            @RequestParam("sponsorid") int sponsorid,
                            @RequestParam(value = "remark", required = false) String remark
    ) {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderid(oid);
        orderItem.setItemid(itemid);
        orderItem.setSponsorid(sponsorid);
        orderItem.setAmount(amount);
        orderItem.setRemark(remark);
        orderService.addItem(orderItem);
        return new Response();
    }

    @RequestMapping(value = "/id/{oid}/item/{id}", method = {RequestMethod.DELETE})
    public Response delelteItem(@PathVariable("oid") int oid,
                                @PathVariable("id") int id
    ) {
        orderService.deleteItem(oid, id);
        return new Response();
    }

    @RequestMapping(value = "/id/{oid}/item/{id}", method = {RequestMethod.PUT})
    public Response updateItemAmount(@PathVariable("oid") int oid,
                                     @PathVariable("id") int id,
                                     @RequestParam("amount") int amount
    ) {
        orderService.updateItemAmount(oid, id, amount);
        return new Response();
    }

    @RequestMapping(value = "/id/{oid}", method = {RequestMethod.GET})
    public Response getOrderByID(@PathVariable("oid") int oid) {
        Response response = new Response();
        response.setData(orderService.getOrderByID(oid));
        return response;
    }

    @RequestMapping(value = "/list/uid/{uid}", method = {RequestMethod.GET})
    public Response getOrderListByUID(@PathVariable("uid") int uid) {
        Response response = new Response();
        response.setData(orderService.getOrderListByUser(uid));
        return response;
    }


    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    public Response getOrderList() {
        Response response = new Response();
        response.setData(orderService.getOrderList());
        return response;
    }

    @RequestMapping(value = "/lastorder", method = {RequestMethod.GET})
    public Response getLastOrder() {
        Response response = new Response();
        response.setData(orderService.getOrderByID(orderService.getLastOrder()));
        return response;
    }
}
