package cn.battlehawk233.barberplatform.service.impl;

import cn.battlehawk233.barberplatform.dao.AdminMapper;
import cn.battlehawk233.barberplatform.dao.OrderMapper;
import cn.battlehawk233.barberplatform.dao.UserMapper;
import cn.battlehawk233.barberplatform.exceptions.ServiceException;
import cn.battlehawk233.barberplatform.pojo.User;
import cn.battlehawk233.barberplatform.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.battlehawk233.barberplatform.pojo.Admin;
import cn.battlehawk233.barberplatform.pojo.Order;
import cn.battlehawk233.barberplatform.pojo.OrderItem;

import java.util.Date;
import java.util.List;

@Service("OrderService")
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
	private final OrderMapper orderMapper;
	private final UserMapper userMapper;
	private final AdminMapper adminMapper;

	@Transactional
	@Override
	public void createOrder(Order order) throws ServiceException {
		User user = userMapper.getUserByID(order.getOwnerId());
		Admin admin = adminMapper.getAdminByID(order.getSponsorId());
		if (user == null) {
			throw new ServiceException("用户不存在!", 400);
		} else if (admin == null) {
			throw new ServiceException("管理员不存在!", 400);
		}
		orderMapper.createOrder(order);
		userMapper.updateLastConsume(order.getOwnerId(), new Date());
		adminMapper.updateLastOrder(order.getSponsorId(), order.getOid());
	}

	@Override
	public void addItem(OrderItem item) throws ServiceException {
		if (orderMapper.getOrderByOrderID(item.getOrderId()) == null) {
			throw new ServiceException("订单不存在!", 400);
		} else if (adminMapper.getAdminByID(item.getSponsorId()) == null) {
			throw new ServiceException("管理员不存在!", 400);
		} else if (orderMapper.getOrderItem(item.getOrderId(), item.getItemId()) != null) {
			throw new ServiceException("已经添加过该项目!", 400);
		}
		orderMapper.addOrderItem(item);
	}

	@Override
	public void deleteItem(int orderid, int itemid) throws ServiceException {
		if (orderMapper.getOrderByOrderID(orderid) == null) {
			throw new ServiceException("订单不存在!", 400);
		} else if (orderMapper.getOrderItem(orderid, itemid) == null) {
			throw new ServiceException("订单项目不存在!", 400);
		}
		orderMapper.deleteOrderItem(orderid, itemid);
	}

	@Override
	public Order getOrderByID(int id) throws ServiceException {
		Order order = orderMapper.getOrderByOrderID(id);
		if (order == null) {
			throw new ServiceException("订单不存在!", 400);
		}
		order.setOrderItemList(orderMapper.getOrderItemList(id));
		return order;
	}

	@Override
	public List<Order> getOrderList() {
		List<Order> orderList = orderMapper.getOrderList();
		for (Order i : orderList) {
			i.setOrderItemList(orderMapper.getOrderItemList(i.getOid()));
		}
		return orderList;
	}

	@Override
	public List<Order> getOrderListByUser(int id) throws ServiceException {
		if (userMapper.getUserByID(id) == null) {
			throw new ServiceException("用户不存在!", 400);
		}
		List<Order> orderList = orderMapper.getOrderListByUser(id);
		for (Order i : orderList) {
			i.setOrderItemList(orderMapper.getOrderItemList(i.getOid()));
		}
		return orderList;
	}

	@Override
	public void updateItemAmount(int oid, int id, int amount) throws ServiceException {
		if (orderMapper.getOrderByOrderID(oid) == null) {
			throw new ServiceException("订单不存在!", 400);
		} else if (orderMapper.getOrderItem(oid, id) == null) {
			throw new ServiceException("订单项目不存在!", 400);
		}
		orderMapper.updateItemAmount(oid, id, amount);
	}

	@Override
	public int getLastOrder() {
		return orderMapper.getLastOrderID();
	}

}
