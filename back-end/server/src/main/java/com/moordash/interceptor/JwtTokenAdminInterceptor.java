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
 * JWT token interceptor for admin
 */
@Component
@Slf4j
public class JwtTokenAdminInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * Authenticate the token in the request header
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // check if the current intercepted is a method of the Controller
        if (!(handler instanceof HandlerMethod)) {
            // if the current intercepted is not a method of the Controller, then let it pass
            return true;
        }

        // 1. Get the token from the request header
        String token = request.getHeader(jwtProperties.getAdminTokenName());

        // 2. Verify the token
        try {
            log.info("JWT verification:{}", token);
            Claims claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(), token);
            Long empId = Long.valueOf(claims.get(JwtClaimsConstant.EMP_ID).toString());
            log.info("Current employee ID: ", empId);

            // save the current employee id to the ThreadLocal
            BaseContext.setCurrentId(empId);

            // 3. If the token is verified, then let it pass
            return true;
        } catch (Exception ex) {
            // 4. If the token is not verified, then return 401 status code
            response.setStatus(401);
            return false;
        }
    }


}
