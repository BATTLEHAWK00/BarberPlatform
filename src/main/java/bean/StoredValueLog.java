package bean;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ToString
public class StoredValueLog {
    int rechargeid;
    int userid;
    BigDecimal recharge_amount;
    Date recharge_time;
    int type;
    String remark;
}
