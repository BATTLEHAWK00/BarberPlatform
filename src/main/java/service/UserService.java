package service;

import bean.User;
import exceptions.ServiceException;

import java.util.Date;
import java.util.List;

public interface UserService {
    void registerUser(User user) throws ServiceException;

    void updatePasswd(int id, String passwd) throws ServiceException;

    void updateRemark(int id, String remark) throws ServiceException;

    void updateBirthDate(int id, Date birthdate) throws ServiceException;

    void updatePhone(int id, String phone) throws ServiceException;

    List<User> getUserList() throws ServiceException;

    void deleteUser(int id) throws ServiceException;

    boolean verifyPasswd(int id,String passwd) throws ServiceException;
}
