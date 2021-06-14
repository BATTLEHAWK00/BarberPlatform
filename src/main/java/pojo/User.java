package pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@ToString
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User implements Serializable {
    int userId;
    String username;
    String passwd;
    String salt;
    String phone;
    Integer gender;
    Date birthDate;
    String remark;
    BigDecimal balance;
    String openId;
    Date lastConsumeTime;
    Date regTime;
}
