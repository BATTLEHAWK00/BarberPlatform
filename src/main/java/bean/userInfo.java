package bean;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class userInfo {
    String[] roles;
    String[] ability;
    String username;
}
