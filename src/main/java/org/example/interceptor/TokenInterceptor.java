package org.example.interceptor;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.utils.JwtUtils;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取请求路径
        String requestURI = request.getRequestURI();

        //若请求包含/login，放行通过
        if (requestURI.contains("/login")) {
            log.info("登陆请求，放行");
            return true;
        }

        //获取请求头中的token
        String token = request.getHeader("token");

        if (token == null || token.isEmpty()) {
            log.info("令牌为空，响应401");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        //校验token
        try {
            Claims claims = JwtUtils.parseToken(token);
            Number id = (Number) claims.get("id");
            request.setAttribute("operateEmpId", id.intValue());
        } catch (Exception e) {
            log.info("令牌非法，响应401");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        //校验通过，放行
        log.info("校验通过，放行");
        return true;
    }

}
