package service;

import pojo.Session;

import java.util.List;

public interface SessionService {
    String Login(String username, String passwd);

    void Logout(String accessToken);

    void CleanSession();

    boolean validateToken(String token);

    void updateLastAccessTime(String accessToken);

    List<Session> getSessionList();

    void deleteSession(String token);
}
