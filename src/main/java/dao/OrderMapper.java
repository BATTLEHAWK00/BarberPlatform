package dao;

import bean.Order;
import bean.OrderItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {
    List<Order> getOrderList();

    List<Order> getOrderListByUser(int id);

    List<OrderItem> getOrderItemList(int id);

    void createOrder(Order order);

    void updateOrderState(@Param("id") int id, @Param("state") int state);
}
