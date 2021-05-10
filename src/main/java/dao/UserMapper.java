package dao;

import bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface UserMapper {
    User getUserByID(int id);

    User getUserByUsername(String username);

    User getUserByPhone(String phone);

    List<User> getUserList();

    void registerUser(User user);

    void updatePasswd(@Param("id") int id, @Param("passwd") String passwd, @Param("salt") String salt);

    void updateRemark(@Param("id") int id, @Param("remark") String remark);

    void updatePhone(@Param("id") int id, @Param("phone") String phone);

    void updateOpenID(@Param("id") int id, @Param("openid") String openid);

    void updateGender(@Param("id") int id, @Param("gender") int gender);

    void updateLastConsume(@Param("id") int id, @Param("time") Date time);

    void deleteUser(int id);

    void updateBirthDate(@Param("id") int id, @Param("birthdate") Date birthdate);
}
