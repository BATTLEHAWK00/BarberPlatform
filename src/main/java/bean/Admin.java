package bean;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class Admin {
    int adminid;
    String name;
    String passwd;
    String salt;
    String phone;
    String remark;
    Date reg_time;
    int last_order;
    String openid;
}
