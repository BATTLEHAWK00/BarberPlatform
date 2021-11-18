package cn.battlehawk233.barberplatform.service.impl;

import cn.battlehawk233.barberplatform.dao.AdminMapper;
import cn.battlehawk233.barberplatform.dao.TokenMapper;
import cn.battlehawk233.barberplatform.dao.SettingMapper;
import cn.battlehawk233.barberplatform.exceptions.ServiceException;
import cn.battlehawk233.barberplatform.pojo.Token;
import cn.battlehawk233.barberplatform.service.TokenService;
import cn.battlehawk233.barberplatform.util.SecurityUtil;
import cn.hutool.core.util.IdUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service("SessionService")
@AllArgsConstructor
public class TokenServiceImpl implements TokenService {
    private final AdminMapper adminMapper;
    private final TokenMapper tokenMapper;
    private final SettingMapper settingMapper;

    @Transactional
    @Override
    public String Login(HttpServletRequest req, String username, String password) {
        String realUserName = settingMapper.getValue("login.username");
        String realPassword = settingMapper.getValue("login.password");
        String salt = settingMapper.getValue("login.salt");
        String digest = SecurityUtil.getInstance().getDigestWithSalt(password, salt);
        if (!realUserName.equals(username) || !realPassword.equals(digest)) {
            throw new ServiceException("用户名或密码错误!", 400);
        }
//		if (sessionMapper.getSessionCountByAdminID(admin.getAdminId()) >= SESSION_MAX) {
//			Token token = sessionMapper.getLastAccessSessionByAdminID(admin.getAdminId());
//			sessionMapper.deleteSession(token.getAccess_token());
//		}
        Token token = new Token();
        token.setAccessToken(IdUtil.simpleUUID());
        token.setAccessIp(req.getRemoteAddr());
        token.setLoginIp(req.getRemoteAddr());
        token.setCreateHost(req.getRemoteHost());
        token.setCreateUa(req.getHeader("User-Agent"));
        token.setUpdateUa(req.getHeader("User-Agent"));
        tokenMapper.createToken(token);
        return token.getAccessToken();
    }

    @Override
    public void Logout(String accessToken) {
        if (tokenMapper.getTokenById(accessToken) == null) {
            return;
        }
        tokenMapper.deleteToken(accessToken);
    }

    @Override
    public boolean validateToken(String accessToken) {
        Token token = tokenMapper.getTokenById(accessToken);
        if (token == null) {
            return false;
        }
        Instant lastAccess = token.getLastAccess().toInstant();
        Instant now_time = Instant.now();
        if (TimeUnit.MINUTES.toChronoUnit().between(lastAccess, now_time) > SESSION_TIMEOUT) {
            tokenMapper.deleteToken(accessToken);
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void updateLastAccessTime(String accessToken) {
        tokenMapper.updateLastAccess(accessToken);
    }

    @Override
    public List<Token> getSessionList() {
        return tokenMapper.getTokenList();
    }

    @Override
    public void deleteSession(String token) {
        tokenMapper.deleteToken(token);
    }

    @Override
    public Token getSessionByToken(String token) {
        return tokenMapper.getTokenById(token);
    }
}
