package cn.battlehawk233.barbershop.auth.provider;

import cn.battlehawk233.barbershop.auth.token.JWTAuthToken;
import cn.hutool.jwt.JWTUtil;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
public class JWTAuthProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        int adminId = (int) authentication.getPrincipal();
        String token = (String) authentication.getCredentials();
        Optional.of(JWTUtil.verify(token, "1234".getBytes()))
                .orElseThrow(() -> new BadCredentialsException("身份无效，请重新登录"));
        Collection<GrantedAuthority> authorities = List.of(
                new SimpleGrantedAuthority("loggedIn")
        );
        return new JWTAuthToken(token, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(JWTAuthToken.class);
    }
}
