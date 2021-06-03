package pojo;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@ToString
public class Order {
    int oid;
    int sponsorid;
    int ownerid;
    String sponsor;
    String username;
    int state;
    List<OrderItem> orderItemList;
    Date pay_time;
    int pay_method;
    BigDecimal price;
    BigDecimal discount;
    BigDecimal actual_payment;
    Date create_time;
    String remark;
}
