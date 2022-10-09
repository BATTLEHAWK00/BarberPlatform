package cn.battlehawk233.barbershop.layer.dao;

import cn.battlehawk233.barbershop.layer.entity.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepo extends CrudRepository<Order, Integer> {
}
