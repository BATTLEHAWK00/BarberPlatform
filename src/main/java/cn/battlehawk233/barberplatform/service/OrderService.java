package cn.battlehawk233.barberplatform.service;

import cn.battlehawk233.barberplatform.pojo.Order;
import cn.battlehawk233.barberplatform.pojo.OrderItem;

import java.util.List;

public interface OrderService {
    void createOrder(Order order);

    void addItem(OrderItem item);

    void deleteItem(int orderid, int itemid);

    Order getOrderByID(int id);

    List<Order> getOrderList();

    List<Order> getOrderListByUser(int id);

    void updateItemAmount(int oid, int id, int amount);

    int getLastOrder();
}
