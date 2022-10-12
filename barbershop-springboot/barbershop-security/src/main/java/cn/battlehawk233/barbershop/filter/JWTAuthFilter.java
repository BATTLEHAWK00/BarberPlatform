package cn.battlehawk233.barbershop.filter;

import cn.battlehawk233.barbershop.auth.token.JWTAuthToken;
import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@AllArgsConstructor
public class JWTAuthFilter extends OncePerRequestFilter {
    private final AuthenticationManager authManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Access-Token");
        if (StrUtil.isBlankOrUndefined(token)) {
            filterChain.doFilter(request, response);
            return;
        }
        var authToken = new JWTAuthToken(token, null);
        SecurityContextHolder.getContext().setAuthentication(authManager.authenticate(authToken));
        filterChain.doFilter(request, response);
    }
}
