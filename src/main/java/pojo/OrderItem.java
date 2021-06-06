package pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderItem {
    int orderid;
    int itemid;
    int sponsorid;
    String itemname;
    String sponsor;
    int amount;
    String remark;
    Date add_time;
}
