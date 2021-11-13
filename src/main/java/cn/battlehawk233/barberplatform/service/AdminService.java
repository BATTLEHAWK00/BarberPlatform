package cn.battlehawk233.barberplatform.service;

import cn.battlehawk233.barberplatform.pojo.Admin;

import java.util.List;


public interface AdminService {
	Admin getAdminByToken(String username, String passwd);

	Admin getAdminByID(int adminid);

	void createAdmin(Admin admin);

	List<Admin> getAdminList();

	void updatePhone(int id, String phone);

	void updatePasswd(int id, String passwd);

	void updateOpenID(int id, String openid);

	void updateRemark(int id, String remark);

	void updateLastOrder(int id, int orderid);

	void updateGender(int id, Integer gender);

	void updateName(int id, String name);

	void updateAdmin(Admin admin);

}
