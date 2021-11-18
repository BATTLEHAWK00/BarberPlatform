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
    String accessToken;
    Date loginTime;
    Date lastAccess;
    String loginIp;
    String accessIp;
    String createUa;
    String updateUa;
    String createHost;
}
