package com.rat.squad.storage.filters;

import com.rat.squad.storage.provider.JwtTokenProvider;
import com.rat.squad.storage.provider.TokenProvider;
import jdk.nashorn.internal.parser.Token;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@Component
@Order(1)
@Slf4j
@RequiredArgsConstructor
public class AuthenticationFilter implements Filter {

    /**
     * Добавлена слабая связность с использованием ДЕКОРАТОРА tokenProvider
     * TokenProvider используется для проверки token'a, который всегда идет с запросами в header'e
     * В данном случае есть только одна реализация tokenProvidera это JwtTokenProvider
     * Он и будет подставлен в это место DI от Spring'a
     */
    private final TokenProvider tokenProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String authorization = req.getHeader("Authorization");
        Enumeration<String> headerNames = req.getHeaderNames();
        while(headerNames.hasMoreElements()) {
            String headerName = (String)headerNames.nextElement();
            log.info("header name " + headerName);
            log.info("value " + req.getHeader(headerName));
        }
        log.info("authorization token with {} is {}",authorization,tokenProvider.validateToken(authorization));
        if (!tokenProvider.validateToken(authorization)) {
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
        chain.doFilter(request, response);
    }
}