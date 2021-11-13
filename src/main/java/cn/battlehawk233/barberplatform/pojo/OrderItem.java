package cn.battlehawk233.barberplatform.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.Date;

@Alias("OrderItem")
@Data
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderItem implements Serializable {
    int orderId;
    int itemId;
    int sponsorId;
    String itemName;
    int amount;
    String remark;
    Date addTime;
}
