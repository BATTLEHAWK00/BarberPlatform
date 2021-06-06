package service;

import pojo.Session;

import java.util.List;

public interface SessionService {
    int SESSION_TIMEOUT = 30;
	int SESSION_MAX = 3;

    String Login(String username, String passwd);

    void Logout(String accessToken);

    boolean validateToken(String token);

    void updateLastAccessTime(String accessToken);

    List<Session> getSessionList();

    void deleteSession(String token);

    Session getSessionByToken(String token);
}
