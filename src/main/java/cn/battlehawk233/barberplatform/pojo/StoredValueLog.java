package cn.battlehawk233.barberplatform.pojo;

import lombok.Data;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.math.BigDecimal;
import java.util.Date;

@Alias("StoredValueLog")
@Data
@ToString
public class StoredValueLog {
    int rechargeid;
    int userid;
    String username;
    BigDecimal recharge_amount;
    Date recharge_time;
    int type;
    String remark;
}
