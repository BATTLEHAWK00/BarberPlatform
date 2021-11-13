package cn.battlehawk233.barberplatform.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Alias("Order")
@Data
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Order implements Serializable {
    int oid;
    int sponsorId;
    int ownerId;
    Admin sponsor;
    User user;
    int state;
    List<OrderItem> orderItemList;
    Date payTime;
    int payMethod;
    BigDecimal price;
    BigDecimal discount;
    BigDecimal actualPayment;
    Date createTime;
    String remark;
}
