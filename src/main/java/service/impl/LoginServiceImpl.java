package service.impl;

import bean.Admin;
import bean.Session;
import dao.AdminMapper;
import dao.SessionMapper;
import exceptions.ServiceException;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestHeader;
import service.LoginService;
import util.SecurityUtil;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    @Setter
    private AdminMapper adminMapper;

    @Autowired
    @Setter
    private SessionMapper sessionMapper;

    @Transactional
    @Override
    public String Login(String username, String passwd) {
        Admin admin = adminMapper.getAdminByName(username);
        if (admin == null) {
            throw new ServiceException("用户不存在!", 400);
        }
        String passwd_md5 = SecurityUtil.getInstance().getSaltMD5(passwd, admin.getSalt());
        if (adminMapper.getAdminByToken(username, passwd_md5) == null) {
            throw new ServiceException("用户名或密码错误!", 400);
        }
        Session session = new Session();
        session.setAccess_token(SecurityUtil.getInstance().genUUID32());
        session.setAdminid(admin.getAdminid());
        sessionMapper.createSession(session);
        return session.getAccess_token();
    }

    @Override
    public void Logout(String accessToken) {
        if (sessionMapper.getSessionByAccessToken(accessToken) == null) {
            return;
        }
        sessionMapper.deleteSession(accessToken);
    }
}
