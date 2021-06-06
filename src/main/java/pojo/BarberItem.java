package pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BarberItem {
    int itemid;
    String name;
    BigDecimal price;
    Date add_time;
    String img;
    String remark;
}
