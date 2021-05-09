package bean;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class OrderItem {
    int orderid;
    int itemid;
    int sponsor;
    Date add_time;
}
