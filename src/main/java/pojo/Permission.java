package pojo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Permission {
    int permission_id;
    String name;
    String description;
}
