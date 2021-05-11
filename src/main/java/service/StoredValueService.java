package service;

import bean.StoredValueLog;
import dao.StoredValueMapper;
import exceptions.ServiceException;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

public interface StoredValueService {
    void writeLog(StoredValueLog storedValueLog) throws ServiceException;

    List<StoredValueLog> getLogList() throws ServiceException;

    List<StoredValueLog> getLogListByUser(int uid) throws ServiceException;

    void recharge(int uid, BigDecimal amount, int type, String remark) throws ServiceException;

    void costValue(int uid, BigDecimal amount, String remark) throws ServiceException;
}
