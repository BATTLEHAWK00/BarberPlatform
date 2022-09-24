package cn.battlehawk233.barbershop.dao;

import cn.battlehawk233.barbershop.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
