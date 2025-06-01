package com.example.schedule_app_advanced.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

/**
 * 인증 필터 설정 클래스 - 로그인 세션 기반 인증 처리
 */
@Configuration
public class AuthFilterConfig {

    @Bean
    public FilterRegistrationBean<Filter> authFilter() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new AuthFilter());
        registrationBean.addUrlPatterns("/*"); // 모든 요청에 대해 필터 적용
        registrationBean.setOrder(1); // 필터 우선순위

        return registrationBean;
    }

    /**
     * 커스텀 인증 필터 정의
     */
    public static class AuthFilter implements Filter {

        @Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
                throws IOException, ServletException {

            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse) servletResponse;

            String uri = request.getRequestURI();

            // 로그인 및 회원가입 요청은 필터 제외
            if (uri.equals("/users") && request.getMethod().equals("POST")) {
                filterChain.doFilter(request, response);
                return;
            }

            if (uri.equals("/users/login")) {
                filterChain.doFilter(request, response);
                return;
            }

            // 세션에 유저 정보 없으면 인증 실패
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("user") == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            filterChain.doFilter(request, response); // 인증 성공
        }
    }
}
