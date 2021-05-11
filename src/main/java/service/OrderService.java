package service;

import bean.Order;
import bean.OrderItem;
import exceptions.ServiceException;

import java.util.List;

public interface OrderService {
    void createOrder(Order order) throws ServiceException;

    void addItem(OrderItem item) throws ServiceException;

    void deleteItem(int orderid, int itemid) throws ServiceException;

    Order getOrderByID(int id) throws ServiceException;

    List<Order> getOrderList();

    List<Order> getOrderListByUser(int id) throws ServiceException;

    void updateItemAmount(int oid, int id, int amount) throws ServiceException;
}
