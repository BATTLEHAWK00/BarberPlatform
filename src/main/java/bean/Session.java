package bean;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class Session {
    String access_token;
    int adminid;
    Date login_time;
    Date last_access;
}
