package service;

import bean.BarberItem;
import exceptions.ServiceException;

import java.util.List;

public interface BarberItemService {
    void addItem(BarberItem barberItem);

    void deleteItem(int id);

    void updateItem(BarberItem barberItem);

    List<BarberItem> getItemList();
}
