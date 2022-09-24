package cn.battlehawk233.barbershop.layer.dao;

import cn.battlehawk233.barbershop.entity.MoneyFlowLog;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.List;

@NoRepositoryBean
public interface LogBaseRepo<T, ID> extends Repository<T, ID> {
    List<T> findAll();

    <S extends T> S save(S entity);
}
