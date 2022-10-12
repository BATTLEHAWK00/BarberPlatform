package cn.battlehawk233.barbershop.dao;

import cn.battlehawk233.barbershop.entity.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepo extends CrudRepository<Customer, Integer> {
    List<Customer> findCustomersByUsernameLike(String username);
    List<Customer> findCustomersByPhoneLike(String phone);
}
