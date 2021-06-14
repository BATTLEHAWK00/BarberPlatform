package pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
