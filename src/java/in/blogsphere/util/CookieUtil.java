/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.blogsphere.util;

import javax.servlet.http.Cookie;

/**
 *
 * @author Prasant
 */
public class CookieUtil {

    public static String valueOf(String key, Cookie[] cookies) {
        if (cookies == null) {
            return null;
        }

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(key)) {
                return cookie.getValue();
            }
        }

        return null;
    }
    
    public static Cookie build(String key, String value, int age) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(age);
        cookie.setPath("/");
        
        return cookie;
    }
    
    public static Cookie build(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setPath("/");
        
        return cookie;
    }
}
