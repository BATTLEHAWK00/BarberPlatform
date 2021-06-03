package service;

import bean.Order;
import bean.OrderItem;
import exceptions.ServiceException;

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
