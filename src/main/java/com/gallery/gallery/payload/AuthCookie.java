package com.gallery.gallery.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthCookie {
    String cookie;
    String username;
}
