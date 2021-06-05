package service.impl;

import pojo.Admin;
import pojo.Session;
import dao.AdminMapper;
import dao.SessionMapper;
import exceptions.ServiceException;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.SessionService;
import util.SecurityUtil;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class SessionServiceImpl implements SessionService {
    public static final int SESSION_TIMEOUT = 60;
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
        CleanSession();
        return session.getAccess_token();
    }

    @Override
    public void Logout(String accessToken) {
        if (sessionMapper.getSessionByAccessToken(accessToken) == null) {
            return;
        }
        sessionMapper.deleteSession(accessToken);
    }

    @Transactional
    @Override
    public void CleanSession() {
        List<Session> sessionList = sessionMapper.getSessionList();
        for (Session session : sessionList) {
            if (ChronoUnit.HOURS.between(
                    session.getLogin_time().toInstant(),
                    session.getLast_access().toInstant())
                    > SESSION_TIMEOUT
            ) {
                System.out.println(session.getAccess_token());
                sessionMapper.deleteSession(session.getAccess_token());
            }
        }
    }

    @Override
    public boolean validateToken(String token) {
        if (sessionMapper.getSessionByAccessToken(token) == null)
            return false;
        Instant lastAccess = sessionMapper.getSessionByAccessToken(token).getLast_access().toInstant();
        Instant now_time = Instant.now();
        if (TimeUnit.MINUTES.toChronoUnit().between(lastAccess, now_time) > SESSION_TIMEOUT) {
            sessionMapper.deleteSession(token);
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void updateLastAccessTime(String accessToken) {
        sessionMapper.updateLastAccess(accessToken);
    }
}
