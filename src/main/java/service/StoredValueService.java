package service;

import bean.StoredValueLog;

import java.math.BigDecimal;
import java.util.List;

public interface StoredValueService {
    void writeLog(StoredValueLog storedValueLog);

    List<StoredValueLog> getLogList();

    List<StoredValueLog> getLogListByUser(int uid);

    void recharge(int uid, BigDecimal amount, int type, String remark);

    void costValue(int uid, BigDecimal amount, String remark);
}
