package cn.battlehawk233.barbershop.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderNotFoundException;
import org.springframework.security.authentication.event.AuthenticationFailureProviderNotFoundEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.util.ArrayList;
import java.util.Collection;

public class AuthManager implements AuthenticationManager {
    private final Collection<AuthenticationProvider> providers;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        for (var provider : providers) {
            if (provider.supports(authentication.getClass())) {
                return provider.authenticate(authentication);
            }
        }
        throw new ProviderNotFoundException(String.format("No such provider for %s", authentication.getClass().getClass()));
    }

    public AuthManager() {
        this.providers = new ArrayList<>();
    }

    public AuthManager(Collection<AuthenticationProvider> providers) {
        this.providers = providers;
    }
}
