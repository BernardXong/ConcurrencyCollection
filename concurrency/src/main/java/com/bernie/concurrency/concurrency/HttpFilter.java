package com.bernie.concurrency.concurrency;


import com.bernie.concurrency.concurrency.example.threadLocal.RequestHolder;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * HttpFilter
 *
 * @Description TODO
 * @Author Bernie【xiongyun100@163.com】
 * @Date 2020/2/26
 */
@Slf4j
public class HttpFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String attrUser = "user";

        HttpServletRequest request = (HttpServletRequest)servletRequest;
        RequestHolder.add(Thread.currentThread().getId());

        log.info("Do Filter,{},{}",Thread.currentThread().getId(),request.getServletPath());
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
