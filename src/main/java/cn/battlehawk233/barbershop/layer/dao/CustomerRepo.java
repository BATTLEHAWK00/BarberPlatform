package cn.battlehawk233.barbershop.layer.dao;

import cn.battlehawk233.barbershop.layer.entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepo extends CrudRepository<Customer, Integer> {
}
