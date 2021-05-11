package service;

import bean.BarberItem;
import exceptions.ServiceException;

import java.util.List;

public interface BarberItemService {
    void addItem(BarberItem barberItem) throws ServiceException;

    void deleteItem(int id) throws ServiceException;

    void updateItem(BarberItem barberItem) throws ServiceException;

    List<BarberItem> getItemList();
}
