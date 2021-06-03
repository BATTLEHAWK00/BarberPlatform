package service;

import pojo.BarberItem;

import java.util.List;

public interface BarberItemService {
    void addItem(BarberItem barberItem);

    void deleteItem(int id);

    void updateItem(BarberItem barberItem);

    List<BarberItem> getItemList();
}
