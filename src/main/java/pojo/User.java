package pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@ToString
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    int userid;
    String username;
    String passwd;
    String salt;
    String phone;
    Integer gender;
    Date birthdate;
    String remark;
    BigDecimal balance;
    String openid;
    Date lastconsume_time;
    Date reg_time;
}
