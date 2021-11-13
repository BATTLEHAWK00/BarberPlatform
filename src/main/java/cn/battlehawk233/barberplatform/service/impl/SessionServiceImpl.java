package cn.battlehawk233.barberplatform.service.impl;

import cn.battlehawk233.barberplatform.dao.AdminMapper;
import cn.battlehawk233.barberplatform.dao.SessionMapper;
import cn.battlehawk233.barberplatform.exceptions.ServiceException;
import cn.battlehawk233.barberplatform.pojo.Token;
import cn.battlehawk233.barberplatform.service.SessionService;
import cn.battlehawk233.barberplatform.util.SecurityUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.battlehawk233.barberplatform.pojo.Admin;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service("SessionService")
@AllArgsConstructor
public class SessionServiceImpl implements SessionService {
	private final AdminMapper adminMapper;
	private final SessionMapper sessionMapper;

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
		if (sessionMapper.getSessionCountByAdminID(admin.getAdminId()) >= SESSION_MAX) {
			Token token = sessionMapper.getLastAccessSessionByAdminID(admin.getAdminId());
			sessionMapper.deleteSession(token.getAccess_token());
		}
		Token token = new Token();
		token.setAccess_token(SecurityUtil.getInstance().genUUID32());
		token.setAdminid(admin.getAdminId());
		sessionMapper.createSession(token);
		return token.getAccess_token();
	}

	@Override
	public void Logout(String accessToken) {
		if (sessionMapper.getSessionByAccessToken(accessToken) == null) {
			return;
		}
		sessionMapper.deleteSession(accessToken);
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

	@Override
	public List<Token> getSessionList() {
		return sessionMapper.getSessionList();
	}

	@Override
	public void deleteSession(String token) {
		sessionMapper.deleteSession(token);
	}

	@Override
	public Token getSessionByToken(String token) {
		return sessionMapper.getSessionByAccessToken(token);
	}
}
