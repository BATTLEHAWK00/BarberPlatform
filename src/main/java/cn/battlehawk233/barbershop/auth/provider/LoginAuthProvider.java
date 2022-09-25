package cn.battlehawk233.barbershop.auth.provider;

import cn.battlehawk233.barbershop.auth.token.JWTAuthToken;
import cn.battlehawk233.barbershop.layer.dao.AdminRepo;
import cn.battlehawk233.barbershop.layer.entity.Admin;
import cn.hutool.jwt.JWTUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@AllArgsConstructor
public class LoginAuthProvider implements AuthenticationProvider {
    private final AdminRepo adminRepo;
    private final PasswordEncoder passwordEncoder;

    private String getJwt(Admin admin) {
        Map<String, Object> payload = Map.ofEntries(
                Map.entry("adminId", admin.getId()),
                Map.entry("expireAt", System.currentTimeMillis() + 1000 * 60)
        );
        return JWTUtil.createToken(payload, "1234".getBytes());
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = (String) authentication.getPrincipal();
        String passwd = (String) authentication.getCredentials();
        var admin = adminRepo.findByName(username).orElseThrow(() -> new UsernameNotFoundException("账号不存在"));
        Optional.of(passwordEncoder.matches(passwd + admin.getSalt(), admin.getPasswd()))
                .orElseThrow(() -> new BadCredentialsException("密码错误"));
        var jwtToken = getJwt(admin);

        return new JWTAuthToken(jwtToken, null);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
