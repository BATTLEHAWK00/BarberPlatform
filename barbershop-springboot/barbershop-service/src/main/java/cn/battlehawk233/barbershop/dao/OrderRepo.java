package cn.battlehawk233.barbershop.dao;

import cn.battlehawk233.barbershop.entity.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepo extends CrudRepository<Order, Integer> {
}
