package cn.battlehawk233.barberplatform.dao;

import cn.battlehawk233.barberplatform.pojo.Token;

import java.util.List;

public interface SessionMapper {
    Token getSessionByAccessToken(String accesstoken);

    void createSession(Token token);

    void deleteSession(String accesstoken);

    List<Token> getSessionList();

    void updateLastAccess(String accesstoken);

    int getSessionCountByAdminID(int adminid);

    List<Token> getSessionListByAdminID(int adminid);

    Token getLastAccessSessionByAdminID(int adminid);
}
