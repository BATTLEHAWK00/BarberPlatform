package cn.battlehawk233.barberplatform.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.battlehawk233.barberplatform.pojo.Order;
import cn.battlehawk233.barberplatform.pojo.OrderItem;
import cn.battlehawk233.barberplatform.service.OrderService;

import java.util.List;

@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    @RequestMapping(value = "/create", method = {RequestMethod.GET, RequestMethod.POST})
    public boolean createOrder(
            @RequestParam("sponsorid") int sponsorid,
            @RequestParam("ownerid") int ownerid,
            @RequestParam(value = "remark", required = false) String remark
    ) {
        Order order = new Order();
        order.setSponsorId(sponsorid);
        order.setOwnerId(ownerid);
        order.setRemark(remark);
        orderService.createOrder(order);
        return true;
    }

    @RequestMapping(value = "/id/{oid}/item", method = {RequestMethod.POST})
    public boolean addItem(@PathVariable("oid") int oid,
                            @RequestParam("itemid") int itemid,
                            @RequestParam("amount") int amount,
                            @RequestParam("sponsorid") int sponsorid,
                            @RequestParam(value = "remark", required = false) String remark
    ) {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderId(oid);
        orderItem.setItemId(itemid);
        orderItem.setSponsorId(sponsorid);
        orderItem.setAmount(amount);
        orderItem.setRemark(remark);
        orderService.addItem(orderItem);
        return true;
    }

    @RequestMapping(value = "/id/{oid}/item/{id}", method = {RequestMethod.DELETE})
    public boolean delelteItem(@PathVariable("oid") int oid,
                                @PathVariable("id") int id
    ) {
        orderService.deleteItem(oid, id);
        return true;
    }

    @RequestMapping(value = "/id/{oid}/item/{id}", method = {RequestMethod.PUT})
    public boolean updateItemAmount(@PathVariable("oid") int oid,
                                     @PathVariable("id") int id,
                                     @RequestParam("amount") int amount
    ) {
        orderService.updateItemAmount(oid, id, amount);
        return true;
    }

    @RequestMapping(value = "/id/{oid}", method = {RequestMethod.GET})
    public Order getOrderByID(@PathVariable("oid") int oid) {
        return orderService.getOrderByID(oid);
    }

    @RequestMapping(value = "/list/uid/{uid}", method = {RequestMethod.GET})
    public List<Order> getOrderListByUID(@PathVariable("uid") int uid) {
        return orderService.getOrderListByUser(uid);
    }


    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    public List<Order> getOrderList() {
        return orderService.getOrderList();
    }

    @RequestMapping(value = "/lastorder", method = {RequestMethod.GET})
    public Order getLastOrder() {
        return orderService.getOrderByID(orderService.getLastOrder());
    }
}
