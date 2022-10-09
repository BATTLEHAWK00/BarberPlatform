package cn.battlehawk233.barbershop.dao;

import cn.battlehawk233.barbershop.entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepo extends CrudRepository<Customer, Integer> {
}
