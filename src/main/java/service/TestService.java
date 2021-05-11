package service;

import bean.Order;
import dao.OrderMapper;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {
    @Autowired
    @Setter
    private OrderMapper orderMapper;

    public List<Order> Test() {
        System.out.println(orderMapper);
        var list = orderMapper.getOrderList();
        return list;
    }
}
