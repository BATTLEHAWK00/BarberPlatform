package service;

import bean.Admin;
import bean.Order;
import bean.OrderItem;
import bean.User;
import dao.AdminMapper;
import dao.OrderMapper;
import dao.UserMapper;
import exceptions.ServiceException;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    @Setter
    private OrderMapper orderMapper;

    @Autowired
    @Setter
    private UserMapper userMapper;

    @Autowired
    @Setter
    private AdminMapper adminMapper;

    @Override
    public void createOrder(Order order) throws ServiceException {
        User user = userMapper.getUserByID(order.getOwnerid());
        Admin admin = adminMapper.getAdminByID(order.getSponsorid());
        if (user == null) {
            throw new ServiceException("用户不存在!", 400);
        } else if (admin == null) {
            throw new ServiceException("管理员不存在!", 400);
        }
        orderMapper.createOrder(order);
        userMapper.updateLastConsume(order.getOwnerid(), new Date());
        adminMapper.updateLastOrder(order.getSponsorid(), order.getOrderid());
    }

    @Override
    public void addItem(OrderItem item) throws ServiceException {
        if (orderMapper.getOrderByOrderID(item.getOrderid()) == null) {
            throw new ServiceException("订单不存在!", 400);
        } else if (adminMapper.getAdminByID(item.getSponsor()) == null) {
            throw new ServiceException("管理员不存在!", 400);
        } else if (orderMapper.getOrderItem(item.getOrderid(), item.getItemid()) != null) {
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
}
