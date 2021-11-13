package cn.battlehawk233.barberplatform.dao;

import cn.battlehawk233.barberplatform.pojo.BarberItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface BarberItemMapper {
    List<BarberItem> getBarberItemList();
    BarberItem getBarberItemByID(int id);
    void addItem(BarberItem item);
    void updateItem(BarberItem item);
    void deleteItem(int id);
}
