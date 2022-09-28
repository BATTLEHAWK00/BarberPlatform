package cn.battlehawk233.barbershop.auth.token;

import cn.hutool.jwt.JWTUtil;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.StringUtils;

import java.util.Collection;

public class JWTAuthToken extends AbstractAuthenticationToken {
    private int adminId;
    private String token;

    public JWTAuthToken(String token, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.adminId = StringUtils.hasLength(token) ? (int) JWTUtil.parseToken(token).getPayload().getClaim("adminId") : -1;
        this.token = token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return adminId;
    }
}
