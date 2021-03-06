package cn.battlehawk233.barberplatform.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Alias("BarberItem")
@Data
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BarberItem implements Serializable {
    int itemid;
    String name;
    BigDecimal price;
    Date add_time;
    String img;
    String remark;
}
