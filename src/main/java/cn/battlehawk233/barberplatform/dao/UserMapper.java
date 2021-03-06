package cn.battlehawk233.barberplatform.dao;

import cn.battlehawk233.barberplatform.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface UserMapper {
	User getUserByID(int id);

	User getUserByUsername(String username);

	User getUserByPhone(String phone);

	Map<String, Object> getUserPasswd(int id);

	List<User> getUserList();

	void registerUser(User user);

	void updatePasswd(@Param("id") int id, @Param("passwd") String passwd, @Param("salt") String salt);

	void updateRemark(@Param("id") int id, @Param("remark") String remark);

	void updatePhone(@Param("id") int id, @Param("phone") String phone);

	void updateOpenID(@Param("id") int id, @Param("openId") String openid);

	void updateGender(@Param("id") int id, @Param("gender") int gender);

	void updateLastConsume(@Param("id") int id, @Param("time") Date time);

	void updateName(@Param("id") int id, @Param("name") String name);

	void updateGender(@Param("id") int id, @Param("gender") Integer gender);

	void deleteUser(int id);

	void updateBirthDate(@Param("id") int id, @Param("birthDate") Date birthdate);
}
