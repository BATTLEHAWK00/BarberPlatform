package cn.battlehawk233.barberplatform.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import cn.battlehawk233.barberplatform.pojo.Order;
import cn.battlehawk233.barberplatform.pojo.OrderItem;

import java.util.List;

@Mapper
public interface OrderMapper {
	List<Order> getOrderList();

	List<Order> getOrderListByUser(int id);

	List<OrderItem> getOrderItemList(int id);

	void createOrder(Order order);

	void updateOrderState(@Param("id") int id, @Param("state") int state);

	void updateItemAmount(@Param("oid") int oid, @Param("itemId") int itemid, @Param("amount") int amount);

	void addOrderItem(OrderItem orderItem);

	void deleteOrderItem(@Param("orderId") int orderid, @Param("itemId") int itemid);

	int getLastOrderID();

	OrderItem getOrderItem(@Param("orderId") int orderid, @Param("itemId") int itemid);

	Order getOrderByOrderID(int id);
}
