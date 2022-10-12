package cn.battlehawk233.barbershop.dao;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.List;

@NoRepositoryBean
public interface LogBaseRepo<T, ID> extends Repository<T, ID> {
    List<T> findAll();

    <S extends T> S save(S entity);
}
