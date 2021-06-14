package dao;

import org.apache.ibatis.annotations.Param;
import pojo.Admin;

import java.util.List;

public interface AdminMapper {
	Admin getAdminByID(int id);

	Admin getAdminByName(String name);

	Admin getAdminByPhone(String phone);

	List<Admin> getAdminList();

	Admin getAdminByToken(@Param("name") String username, @Param("passwd") String passwd);

	void registerAdmin(Admin admin);

	void updatePhone(@Param("id") int id, @Param("phone") String phone);

	void updatePasswd(@Param("id") int id, @Param("passwd") String passwd, @Param("salt") String salt);

	void updateOpenID(@Param("id") int id, @Param("openId") String openid);

	void updateRemark(@Param("id") int id, @Param("remark") String remark);

	void updateLastOrder(@Param("id") int id, @Param("orderId") int orderid);

	void updateGender(@Param("id") int id, @Param("gender") Integer gender);

	void updateName(@Param("id") int id, @Param("name") String name);

	void deleteAdmin(int id);


}
