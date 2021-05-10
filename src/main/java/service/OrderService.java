package service;

import bean.Order;
import bean.OrderItem;
import exceptions.ServiceException;

public interface OrderService {
    void createOrder(Order order) throws ServiceException;

    void addItem(OrderItem item) throws ServiceException;
    void deleteItem(int orderid,int itemid) throws ServiceException;
}
