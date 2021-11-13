package cn.battlehawk233.barberplatform.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Alias("User")
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
