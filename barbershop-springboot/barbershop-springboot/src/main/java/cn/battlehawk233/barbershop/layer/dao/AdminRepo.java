package cn.battlehawk233.barbershop.layer.dao;

import cn.battlehawk233.barbershop.layer.entity.Admin;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface AdminRepo extends PagingAndSortingRepository<Admin, Integer> {
    Optional<Admin> findByName(String name);

    List<Admin> findByNameStartsWith(String name);

    List<Admin> findByIdStartsWith(Integer id);

    List<Admin> findByPhoneStartsWith(String phone);
}
