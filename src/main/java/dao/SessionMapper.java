package dao;

import pojo.Session;

import java.util.List;

public interface SessionMapper {
    Session getSessionByAccessToken(String accesstoken);

    void createSession(Session session);

    void deleteSession(String accesstoken);

    List<Session> getSessionList();

    void updateLastAccess(String accesstoken);
    
}
