package cn.battlehawk233.barberplatform.service;

import cn.battlehawk233.barberplatform.pojo.Token;

import java.util.List;

public interface SessionService {
    int SESSION_TIMEOUT = 30;
	int SESSION_MAX = 3;

    String Login(String username, String passwd);

    void Logout(String accessToken);

    boolean validateToken(String token);

    void updateLastAccessTime(String accessToken);

    List<Token> getSessionList();

    void deleteSession(String token);

    Token getSessionByToken(String token);
}
