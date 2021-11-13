package cn.battlehawk233.barberplatform.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.Date;

@Alias("Admin")
@Data
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Admin implements Serializable {
	int adminId;
	String name;
	String passwd;
	String salt;
	String phone;
	String remark;
	Integer gender;
	Date regTime;
	int lastOrderID;
	Order lastOrder;
	String openId;
	boolean isSuperAdmin;
}
