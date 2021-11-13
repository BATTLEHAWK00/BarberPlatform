package cn.battlehawk233.barberplatform.pojo;

import lombok.Data;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.Date;

@Alias("Token")
@Data
@ToString
public class Token implements Serializable {
    String access_token;
    int adminid;
    Date login_time;
    Date last_access;
}
