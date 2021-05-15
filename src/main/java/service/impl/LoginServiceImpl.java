package service.impl;

import bean.Admin;
import dao.AdminMapper;
import exceptions.ServiceException;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.LoginService;
import util.SecurityUtil;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    @Setter
    private AdminMapper adminMapper;

    @Override
    public String Login(String username, String passwd) throws ServiceException {
        Admin admin = adminMapper.getAdminByName(username);
        if (admin == null) {
            throw new ServiceException("用户不存在!", 400);
        }
        String passwd_md5 = SecurityUtil.getInstance().getSaltMD5(passwd, admin.getSalt());
        if (adminMapper.getAdminByToken(username, passwd_md5) == null) {
            throw new ServiceException("用户名或密码错误!", 400);
        }
        return "admin-accessToken";
    }

    @Override
    public void Logout() {

    }
}
