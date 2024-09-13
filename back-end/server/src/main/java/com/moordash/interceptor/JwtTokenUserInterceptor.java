package com.moordash.interceptor;

import com.moordash.constant.JwtClaimsConstant;
import com.moordash.context.BaseContext;
import com.moordash.properties.JwtProperties;
import com.moordash.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * JWT token authentication interceptor
 */
@Component
@Slf4j
public class JwtTokenUserInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * Authenticate JWT token
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // check if the intercepted is a controller method
        if (!(handler instanceof HandlerMethod)) {
            // the intercepted is not a dynamic method, release directly
            return true;
        }

        // 1. Get the token from the request header
        String token = request.getHeader(jwtProperties.getUserTokenName());

        // 2. Authenticate the token
        try {
            log.info("JWT authenticate: {}", token);
            Claims claims = JwtUtil.parseJWT(jwtProperties.getUserSecretKey(), token);
            Long userId = Long.valueOf(claims.get(JwtClaimsConstant.USER_ID).toString());
            log.info("Current user ID: ", userId);
            BaseContext.setCurrentId(userId);
            // 3. Authentication passed, release
            return true;
        } catch (Exception ex) {
            // 4. Authentication failed, response 401 status code
            response.setStatus(401);
            return false;
        }
    }
}