package cn.battlehawk233.barberplatform.pojo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class userInfo {
    String[] roles;
    String[] ability;
    String username;
}
