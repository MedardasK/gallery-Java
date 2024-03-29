package com.gallery.gallery.service.implementations;

import com.gallery.gallery.config.TokenProvider;
import com.gallery.gallery.entity.User;
import com.gallery.gallery.payload.AuthCookie;
import com.gallery.gallery.payload.AuthToken;
import com.gallery.gallery.service.IAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements IAuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;

    public AuthToken loginUser(User loginUser){
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        return new AuthToken(token);
    }

    public AuthToken refreshToken(AuthCookie authCookie) {
        User user = new User();
        user.setUsername(jwtTokenUtil.getUsernameFromToken(authCookie.getUsername()));
        if (jwtTokenUtil.validateToken(authCookie.getCookie(), (UserDetails) user)) {
            Authentication authentication = null;
            final String token = jwtTokenUtil.generateToken(authentication);
            return new AuthToken(token);
        } else {
            return null;
        }
    }

}
