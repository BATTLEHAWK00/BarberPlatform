package bean;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ToString
public class Order {
    int orderid;
    int sponsorid;
    int ownerid;
    int state;
    Date pay_time;
    int pay_method;
    BigDecimal price;
    BigDecimal discount;
    BigDecimal actual_payment;
    Date create_time;
    String remark;
}
