package pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Admin {
	int adminid;
	String name;
	String passwd;
	String salt;
	String phone;
	String remark;
	Integer gender;
	Date reg_time;
	int last_order;
	String openid;
	boolean is_superadmin;
}
