package service.impl;

import bean.BarberItem;
import dao.BarberItemMapper;
import exceptions.ServiceException;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.BarberItemService;

import java.util.List;

@Service
public class BarberItemServiceImpl implements BarberItemService {
    @Autowired
    @Setter
    private BarberItemMapper barberItemMapper;

    @Override
    public void addItem(BarberItem barberItem) {
        if (barberItem.getName().trim().isEmpty()) {
            throw new ServiceException("项目名不能为空!", 400);
        }
        barberItemMapper.addItem(barberItem);
    }

    @Override
    public void deleteItem(int id) {
        if (barberItemMapper.getBarberItemByID(id) == null) {
            throw new ServiceException("项目不存在!", 400);
        }
        barberItemMapper.deleteItem(id);
    }

    @Override
    public void updateItem(BarberItem barberItem) {

    }

    @Override
    public List<BarberItem> getItemList() {
        return barberItemMapper.getBarberItemList();
    }
}
