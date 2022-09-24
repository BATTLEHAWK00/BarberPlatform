package cn.battlehawk233.barbershop.dao;

import cn.battlehawk233.barbershop.entity.Admin;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface AdminRepository extends PagingAndSortingRepository<Admin, Integer> {
    Optional<Admin> findByName(String name);
}
