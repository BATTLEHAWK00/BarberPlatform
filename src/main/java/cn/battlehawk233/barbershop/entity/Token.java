package cn.battlehawk233.barbershop.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

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
