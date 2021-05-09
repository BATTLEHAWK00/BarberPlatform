package dao;

import bean.BarberItem;

import java.util.List;

public interface BarberItemMapper {
    List<BarberItem> getBarberItemList();
    BarberItem getBarberItemByID(int id);
    void addItem(BarberItem item);
    void updateItem(BarberItem item);
    void deleteItem(int id);
}
