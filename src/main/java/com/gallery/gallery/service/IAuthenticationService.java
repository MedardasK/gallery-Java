package com.gallery.gallery.service;

import com.gallery.gallery.entity.User;
import com.gallery.gallery.payload.AuthCookie;
import com.gallery.gallery.payload.AuthToken;

public interface IAuthenticationService {

    AuthToken loginUser(User loginUser);

    AuthToken refreshToken(AuthCookie authCookie);
}
