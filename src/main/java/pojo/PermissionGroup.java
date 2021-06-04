package pojo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PermissionGroup {
    int group_id;
    String name;
    String description;
}
