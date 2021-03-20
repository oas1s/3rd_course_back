package com.rat.squad.storage.interceptors;

import com.rat.squad.storage.provider.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthorizationInterceptor implements HandlerInterceptor {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public boolean preHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       log.info("Authorization header: {}", request.getHeader("Authorization"));
       if (request.getHeader("Authorization") != null) {
          String token = request.getHeader("Authorization");
          log.info("Result of valid token {} is {}", token, jwtTokenProvider.validateToken(token));
          if (!jwtTokenProvider.validateToken(token)) {
             response.sendError(401);
          }
       } else {
          response.sendError(401);
       }
        return true;
    }

    @Override
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) throws Exception {

    }
}