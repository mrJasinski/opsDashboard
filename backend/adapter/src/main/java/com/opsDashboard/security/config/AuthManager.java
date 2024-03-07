package com.opsDashboard.security.config;

import com.opsDashboard.user.dto.RoleDTO;
import com.opsDashboard.user.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
class AuthManager implements AuthenticationManager
{
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    AuthManager(final UserService userService, final PasswordEncoder passwordEncoder)
    {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException
    {
        var username = authentication.getName();
        var pwd = authentication.getCredentials().toString();

        var user = this.userService.getUserByEmailAsDto(username);

        if (!this.passwordEncoder.matches(pwd, user.getPassword()))
            throw new BadCredentialsException("Invalid password!");

        return new UsernamePasswordAuthenticationToken(username, pwd, getGrantedAuthorities(user.getRole()));
    }

    private List<GrantedAuthority> getGrantedAuthorities(RoleDTO role)
    {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        grantedAuthorities.add(new SimpleGrantedAuthority(role.getType()));

        return grantedAuthorities;
    }
}
