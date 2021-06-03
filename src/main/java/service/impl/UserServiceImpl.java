package service.impl;

import pojo.User;
import dao.UserMapper;
import exceptions.ServiceException;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.UserService;
import util.SecurityUtil;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Setter
    @Autowired
    private UserMapper userMapper;

    @Override
    public void registerUser(User user) throws ServiceException {
        user.setSalt(SecurityUtil.getInstance().genUUID8());
        user.setPasswd(SecurityUtil.getInstance().getSaltMD5(user.getPasswd(), user.getSalt()));
        if (userMapper.getUserByPhone(user.getPhone()) != null)
            throw new ServiceException("手机号已被注册!", 400);
        userMapper.registerUser(user);
    }

    public void updatePasswd(int id, String passwd) throws ServiceException {
        User user = userMapper.getUserByID(id);
        if (user == null) {
            throw new ServiceException("用户不存在!", 400);
        } else if (user.getPasswd().equals(SecurityUtil.getInstance().getSaltMD5(passwd, user.getSalt()))) {
            throw new ServiceException("新密码和原密码相同!", 400);
        }
        String salt = SecurityUtil.getInstance().genUUID8();
        String passwd_md5 = SecurityUtil.getInstance().getSaltMD5(passwd, salt);
        userMapper.updatePasswd(id, passwd_md5, salt);
    }

    @Override
    public void updateRemark(int id, String remark) throws ServiceException {
        User user = userMapper.getUserByID(id);
        if (user == null) {
            throw new ServiceException("用户不存在!", 400);
        }
        userMapper.updateRemark(id, remark);
    }

    @Override
    public void updateBirthDate(int id, Date birthdate) throws ServiceException {
        User user = userMapper.getUserByID(id);
        if (user == null) {
            throw new ServiceException("用户不存在!", 400);
        } else if (user.getBirthdate() != null) {
            throw new ServiceException("生日不可再更改!", 400);
        }
        userMapper.updateBirthDate(id, birthdate);
    }

    @Override
    public void updatePhone(int id, String phone) throws ServiceException {
        User user = userMapper.getUserByID(id);
        if (user == null) {
            throw new ServiceException("用户不存在!", 400);
        }
        userMapper.updatePhone(id, phone);
    }

    @Override
    public List<User> getUserList() throws ServiceException {
        List<User> userList = userMapper.getUserList();
        return userList;
    }

    @Override
    public void deleteUser(int id) throws ServiceException {
        if (userMapper.getUserByID(id) == null) {
            throw new ServiceException("用户不存在!", 400);
        }
        userMapper.deleteUser(id);
    }

    @Override
    public boolean verifyPasswd(int id, String passwd) {
        if (userMapper.getUserByID(id) == null) {
            throw new ServiceException("用户不存在!", 400);
        }
        Map<String, Object> mp = userMapper.getUserPasswd(id);
        String passwd_md5 = SecurityUtil.getInstance().getSaltMD5(passwd, (String) mp.get("salt"));
        return passwd_md5.equals(mp.get("passwd"));
    }

}
