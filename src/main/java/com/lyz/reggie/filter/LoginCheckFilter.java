package com.lyz.reggie.filter;

import com.alibaba.fastjson.JSON;
import com.lyz.reggie.common.BaseContext;
import com.lyz.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebFilter(filterName = "loginCheckFilter",urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    private static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    public boolean check(String requestURI,String[] strings){
        for (String str :
                strings) {
            boolean match = PATH_MATCHER.match(str, requestURI);
            if (match == true){
                return true;
            }
        }
        return false;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String requestURI = request.getRequestURI();
        String[] strings = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**",
                "/common/**",
                "/user/login",
                "/user/sendMsg"
        };

        boolean check = check(requestURI, strings);
        if(check == true){
            log.info("本次请求{}不需要拦截",requestURI);
            filterChain.doFilter(request,response);
            return;
        }

        if (request.getSession().getAttribute("employee") != null){
            Long employee = (Long) request.getSession().getAttribute("employee");

            BaseContext.setCurrentId(employee);

            filterChain.doFilter(request,response);
            return;
        };

        if (request.getSession().getAttribute("user") != null){
            Long user = (Long) request.getSession().getAttribute("user");

            BaseContext.setCurrentId(user);

            filterChain.doFilter(request,response);
            return;
        };

        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        log.info("拦截的路径是：{}",request.getRequestURI());
        return;
    }
}
