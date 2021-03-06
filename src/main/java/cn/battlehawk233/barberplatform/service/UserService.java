package cn.battlehawk233.barberplatform.service;

import cn.battlehawk233.barberplatform.pojo.User;

import java.util.Date;
import java.util.List;

public interface UserService {
	void registerUser(User user);

	void updatePasswd(int id, String passwd);

	void updateRemark(int id, String remark);

	void updateBirthDate(int id, Date birthdate);

	void updatePhone(int id, String phone);

	void updateName(int id, String name);

	void updateGender(int id, Integer gender);

	void updateUser(User user);

	List<User> getUserList();

	void deleteUser(int id);

	boolean verifyPasswd(int id, String passwd);
}
