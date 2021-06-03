package service;

import pojo.User;

import java.util.Date;
import java.util.List;

public interface UserService {
    void registerUser(User user);

    void updatePasswd(int id, String passwd);

    void updateRemark(int id, String remark);

    void updateBirthDate(int id, Date birthdate);

    void updatePhone(int id, String phone);

    List<User> getUserList();

    void deleteUser(int id);

    boolean verifyPasswd(int id, String passwd);
}
