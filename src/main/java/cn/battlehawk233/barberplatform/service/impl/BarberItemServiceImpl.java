package cn.battlehawk233.barberplatform.service.impl;

import cn.battlehawk233.barberplatform.dao.BarberItemMapper;
import cn.battlehawk233.barberplatform.exceptions.ServiceException;
import cn.battlehawk233.barberplatform.service.BarberItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import cn.battlehawk233.barberplatform.pojo.BarberItem;

import java.util.List;

@Service("BarberItemService")
@AllArgsConstructor
public class BarberItemServiceImpl implements BarberItemService {
    private final BarberItemMapper barberItemMapper;

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
