package cn.battlehawk233.barbershop.dao;

import cn.battlehawk233.barbershop.entity.BarberItem;
import org.springframework.data.repository.CrudRepository;

public interface BarberItemRepo extends CrudRepository<BarberItem, Integer> {
}
