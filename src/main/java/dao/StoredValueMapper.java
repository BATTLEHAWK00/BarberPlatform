package dao;

import bean.StoredValueLog;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface StoredValueMapper {
    void updateValue(@Param("id") int id, @Param("value")BigDecimal value);
    void writeRechargeLog(StoredValueLog log);
    List<StoredValueLog> getRechargeLogList();
    List<StoredValueLog> getRechargeLogListByUser(int userid);
}
