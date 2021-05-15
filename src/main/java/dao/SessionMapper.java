package dao;

import bean.Session;

public interface SessionMapper {
    Session getSessionByAccessToken(String accesstoken);

    void createSession(Session session);

    void deleteSession(String accesstoken);
}
