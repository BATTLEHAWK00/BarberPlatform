package dao;

import bean.Order;
import bean.OrderItem;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderMapper {
    List<Order> getOrderList();

    List<Order> getOrderListByUser(int id);

    List<OrderItem> getOrderItemList(int id);

    void createOrder(Order order);

    void updateOrderState(@Param("id") int id, @Param("state") int state);

    void addOrderItem(OrderItem orderItem);

    void deleteOrderItem(@Param("orderid") int orderid, @Param("itemid") int itemid);

    OrderItem getOrderItem(@Param("orderid") int orderid, @Param("itemid") int itemid);

    Order getOrderByOrderID(int id);
}
