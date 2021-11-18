package cn.battlehawk233.barberplatform.dao;

import cn.battlehawk233.barberplatform.pojo.Token;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TokenMapper {
    Token getTokenById(@Param("accessToken") String accessToken);

    void createToken(Token token);

    void deleteToken(@Param("accessToken") String accessToken);

    List<Token> getTokenList();

    void updateLastAccess(@Param("accessToken") String accessToken);

    void updateIp(@Param("accessToken") String accessToken, @Param("accessIp") String accessIp);

    void updateUa(@Param("accessToken") String accessToken, @Param("updateUa") String updateUa);
}
